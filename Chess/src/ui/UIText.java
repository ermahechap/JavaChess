package ui;
import java.util.Scanner;
import java.util.ArrayList;
import data.*;
import businessLogic.Functional;
import java.io.File;
import java.util.Arrays;
import java.util.InputMismatchException;

public class UIText implements UI{
    private static String divisor = "-------------------";
    private static Scanner reader = new Scanner(System.in);
    
    private static String messages[]= new String[]{"Opcion no listada","No hay pieza en la posición inicial"
            ,"Se espera valor numerico","Ingrese una coordenada valida, es decir letra y número"
            ,"Coordenedas Incorrectas o fuera del limite","La dirección es incorrecta, debe ingresar solo la dirección de LA CARPETA"
            ,"Coordenadas iguales, intente otra vez","Movimiento no valido"};
    
    @Override
    public void onWinMessage(Player player){
        System.out.println(divisor);
        System.out.println("\tEl jugador " + player.getName() + " ha ganado esta partida");
        System.out.println(divisor);
    }
    
    @Override
    public void onTieMessage(Player[] player){
        System.out.println(divisor);
        System.out.println("\tlos jugadores " + player[0].getName() + "y" + 
                player[1].getName()+" han empatado");
        System.out.println(divisor);
    }
    
    @Override
    public void messageStalemate() {
        System.out.println(divisor);
        System.out.println("\tEmpate por rey ahogado");
        System.out.println(divisor);
    }

    @Override
    public void onInvalidMoveCheck(Player[] player, int turn) {
        System.out.println(divisor);
        System.out.println("El movimiento lo pone en jaque, intente otra vez!!");
        System.out.println(divisor);
    }
    
    @Override
    public void onCheck(Player[] player, int turn) {
        System.out.println(divisor);
        System.out.println("\tEl jugador " + player[turn].getName() + " esta en jaque" );
        System.out.println(divisor);
    }
    
    @Override
    public void checkMate(Player[] player, int turn) {
        System.out.println("\tJAQUE MATE");
        System.out.println("\tEl jugador " + player[turn].getName() + " ha perdido");
        System.out.println(divisor);
    }
    
    @Override
    public void onQuitGame(Player player) {
        System.out.println(divisor);
        System.out.println("El jugador "+ player.getName() + " se ha retirado del juego");
        System.out.println(divisor);
    }
    
    @Override
    public void onError(int msg){
        System.out.println(divisor);
        System.out.println("\tError!!");
        
        if(msg<0 || msg>=messages.length){
            System.out.println("\tNo hay mesaje en este índice");
        }else{
            System.out.println("\t"+messages[msg]);
        }
        System.out.println("\t Intente otra vez");
        System.out.println(divisor);
    }
    
    @Override
    public void messageDrawFifty(Player[] player) {
        System.out.println(divisor);
        System.out.println("Empate por 50 movimientos si comer o avanzar peones");
        System.out.println("\tJudador "+player[0].getName() + " ha empatado con " + player[1].getName());
        System.out.println(divisor);
    }
    
    @Override
    public void messageDrawKing(Player[] player){
        System.out.println(divisor);
        System.out.println("Empate por ahogar al rey");
        System.out.println("\tJudador "+player[0].getName() + " ha empatado con " + player[1].getName());
        System.out.println(divisor);
    }
    
    @Override
    public void printBoard(Board board){
        System.out.println("\n"+board.toString());
        System.out.println(divisor);
    }
    
    @Override
    public String readName(String col){
        System.out.println(divisor);
        System.out.println("Ingrese nombre del jugador "+ col);
        return reader.next();
    }

    @Override
    public Player[] readPlayers() {
        Player player[]={new Player(readName("Blancas"), true)
                ,new Player(readName("Negras"), false)};
        return player;
    }
    
    
    
    @Override
    public void welcome() {
        System.out.println(divisor);
        System.out.println("Bienvenido al juego:!!");
    }
    
    @Override
    public int menu(){
        System.out.println(divisor);
        String opt;
        int out;
        while(true){
            System.out.println("Seleccione una opcion");
            System.out.println("1. Nuevo Juego");
            System.out.println("2. Cargar Juego");
            System.out.println("3. Salir");
            try{
                opt=reader.next();
                out=Integer.parseInt(opt);
                if(out>=1 && out<=3){
                    break;
                }else{
                    onError(0);
                }
            }catch(NumberFormatException e){
                onError(2);
            }
        }
        return out;
    }
    
    @Override
    public void printCemetery(Player w, Player b) {
        System.out.println(divisor);
        System.out.println("Cementerio: " );
        System.out.print("Blancas->" );
        for (int i=0;i<w.getCemetery().size();i++) {
            System.out.print(w.getCemetery().get(i).getPieceSign());
        }
        System.out.print("\nNegras->" );
        for (int i=0;i<b.getCemetery().size();i++) {
            System.out.print(b.getCemetery().get(i).getPieceSign());
        }
        System.out.println("");
    }
    
    @Override
    public void whosePlayer(Player player){
        String playerColor= (player.isColor())?"White":"Black";
        System.out.println(divisor);
        System.out.println("Turno del jugador " + player.getName() + " - " + playerColor);
    }
    
    @Override
    public String coordinateRead(){
        String coord=new String();
        boolean flag=true; 
        do{
            coord= reader.next();
            if(coord.length()!=2){
                onError(3);
                continue;
            }
            coord=coord.toLowerCase();
            if(coord.charAt(0)>='a' && coord.charAt(0)<='h' && coord.charAt(1)>='1' && coord.charAt(1)<='8'){
                flag=false;
            }else{
                onError(4);
            }
        }while(flag);
        return coord;
    }
    
    @Override
    public ArrayList<ArrayList<Integer>> inputMove() {
        boolean flag=true;
        ArrayList<ArrayList<Integer>> moveCoordinates= new ArrayList<>();
        
        do{
            System.out.println(divisor);
            System.out.println("Ingrese Coordenadas de la pieza, es decir letra y numero, por ejemplo ->e4");
            System.out.println("Nota: Para hacer enroque de coordenada del rey y de la torre");
            moveCoordinates.add(Functional.splitCoordinatesString(coordinateRead()));
            System.out.println("Ingrese Coordenadas de destino de la misma forma:");
            moveCoordinates.add(Functional.splitCoordinatesString(coordinateRead()));
            
            if(moveCoordinates.get(0).equals(moveCoordinates.get(1))){//to avoid same coordinates input
                onError(6);
                moveCoordinates.clear();
            }else{
                flag=false;
            }
        }while(flag);
        
        return moveCoordinates;
    }
    
    @Override
    public Piece askPromotioPiece(boolean color) {
        Piece returnPiece;
        String opt;
        int out;
        ArrayList<Piece> values=new ArrayList<>(Arrays.asList(new Queen(color),
            new Knight(color),new Rook(color),new Bishop(color)));
        while(true){
            System.out.println(divisor);
            System.out.println("Seleccione pieza que quiere cambiar:");
            System.out.println("1. Queen");
            System.out.println("2. Knight");
            System.out.println("3. Rook");
            System.out.println("4. Bishop");
            try{
                opt=reader.next();
                out=Integer.parseInt(opt);
                if(out>=1 && out<=4){
                    returnPiece= values.get(out-1);
                    break;
                }else{
                    onError(0);
                }
            }catch (NumberFormatException e){
                onError(2);
            }
        }
        return returnPiece;
    }
    
    @Override
    public int movementOptions() {
        int out;
        String opt;
        while(true){
            System.out.println(divisor);
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Realizar movimiento");
            System.out.println("2. Mostrar historial de jugadas (notacion LAN)");
            System.out.println("3. Guardar juego");
            System.out.println("4. Retirarse");
            try{
                opt=reader.next();
                out=Integer.parseInt(opt);
                if(out>=1 && out<=4){
                    break;
                }else{
                    onError(0);
                }
            }catch(NumberFormatException e){
                onError(2);
            }
        }
        return out;
    }
    
    @Override
    public void showPlayHist(Player[] player) {
        int it=0;
        System.out.println(divisor);
        System.out.println("Historial de Jugadas:");
        System.out.println("   Blancas \tNegras");
        while (true){
            System.out.print(it+". ");
            if(it<player[0].getHistory().size())System.out.print(player[0].getHistory().get(it)+"\t");
            else System.out.print("        "+"\t");
            
            if(it<player[1].getHistory().size())System.out.print(player[1].getHistory().get(it));
            else System.out.print("        ");
            System.out.println("");
            if(it>=player[0].getHistory().size() && it>=player[0].getHistory().size()){
                break;
            }
            it++;
        }
    }
    
    @Override
    public boolean overWriteMessage(String fileName) {
        String opt;
        int out;
        System.out.println("\tVa a sobreescribir el archivo"+ fileName+ ",desea hacerlo?");
        System.out.println("1. Si");
        System.out.println("2. No");
        boolean flag=true;
        do{
            try{
                opt=reader.next();
                out=Integer.parseInt(opt);
                if(out==1){
                    return true;
                }else if(out==2){
                    return false;
                }else{
                    onError(0);
                }
            }catch(NumberFormatException e){
                onError(2);
            }
        }while(flag);
        return false;
    }
    
    @Override
    public String saveGameRequest(){
        String path = new String();
        while(true){
            System.out.println(divisor);
            System.out.println("Ingrese dirección de la carpeta en donde se va a guardar el archivo:");
            path=reader.next();
            if(path.charAt(path.length()-1)!='\\')path+="\\";
            
            File dir = new File(path);
            
            if (dir.exists() && dir.isDirectory()) {
                System.out.println("Ingrese nombre del archivo: ");
                path+=reader.next()+".chess";
                dir=new File(path);
                if(dir.exists()){
                    if(overWriteMessage(path))break;
                }else{
                    break;
                }
            }else{
                onError(5);
            }
        }
        
        return path;
    }
    
    @Override
    public String loadGameRequest() {
        System.out.println(divisor);
        System.out.println("Ingrese la dirección del archivo (Ej: C:\\archivo.chess)");
        System.out.println("Nota: Puede ser otra extensión, siempre que el archivo sea compatible. ");
        String fileName=reader.next();
        return fileName;
    }
    
    @Override
    public void onSaveSuceed() {
        System.out.println(divisor);
        System.out.println("\t Se ha guardado la partida Exitosamente");
        System.out.println(divisor);
    }
    
    @Override
    public void onSaveFailure() {
        System.out.println(divisor);
        System.out.println("\t No se ha guardado la partida.");
        System.out.println(divisor);
    }
    
    @Override
    public void onLoadFailure() {
        System.out.println(divisor);
        System.out.println("\t No se puede cargar la partida, nueva partida iniciada");
        System.out.println(divisor);
    }
    
    @Override
    public void onLoadSuceed() {
        System.out.println(divisor);
        System.out.println("\t Carga realizada correctamente!");
        System.out.println(divisor);
    }

}
