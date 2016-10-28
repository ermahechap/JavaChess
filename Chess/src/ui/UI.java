package ui;
import java.util.Scanner;
import java.util.ArrayList;
import data.*;
import businessLogic.Functional;
import java.io.File;
import java.util.Arrays;
import java.util.InputMismatchException;

public class UI {
    private static String divisor = "-------------------";
    private static Scanner reader = new Scanner(System.in);
    
    public static void onWinMessage(Player player){
        System.out.println(divisor);
        System.out.println("\tEl jugador " + player.getName() + " ha ganado esta partida");
        System.out.println(divisor);
    }
    
    public static void onTieMessage(Player[] player){
        System.out.println(divisor);
        System.out.println("\tlos jugadores " + player[0].getName() + "y" + 
                player[1].getName()+" han empatado");
        System.out.println(divisor);
    }
    
    
    public static void messageStalemate() {
        System.out.println(divisor);
        System.out.println("\tEmpate por rey ahogado");
        System.out.println(divisor);
    }

    
    public static void onInvalidMoveCheck(Player[] player, int turn) {
        System.out.println(divisor);
        System.out.println("El movimiento lo pone en jaque, intente otra vez!!");
        System.out.println(divisor);
    }

    public static void onCheck(Player[] player, int turn) {
        System.out.println(divisor);
        System.out.println("\tEl jugador " + player[turn].getName() + " esta en jaque" );
        System.out.println(divisor);
    }
    
    public static void checkMate(Player[] player, int turn) {
        System.out.println("\tJAQUE MATE");
        System.out.println("\tEl jugador " + player[turn].getName() + " ha perdido");
        System.out.println(divisor);
    }
    
    public static void onQuitGame(Player player) {
        System.out.println(divisor);
        System.out.println("El jugador "+ player.getName() + " se ha retirado del juego");
        System.out.println(divisor);
    }
    
    public static void onError(String errorMessage){
        System.out.println(divisor);
        System.out.println("\tError!!");
        System.out.println("\t"+errorMessage);
        System.out.println("\t Intente otra vez");
        System.out.println(divisor);
    }
    
    public static void messageDrawFifty(Player[] player) {
        System.out.println(divisor);
        System.out.println("Empate por 50 movimientos si comer o avanzar peones");
        System.out.println("\tJudador "+player[0].getName() + " ha empatado con " + player[1].getName());
        System.out.println(divisor);
    }
    
    public static void messageDrawKing(Player[] player){
        System.out.println(divisor);
        System.out.println("Empate por ahogar al rey");
        System.out.println("\tJudador "+player[0].getName() + " ha empatado con " + player[1].getName());
        System.out.println(divisor);
    }
    
    private static void onDuplicateCoordinate(){
        System.out.println(divisor);
        System.out.println("\tError!!, Cooerdenadas iguales, intente otra vez");
        System.out.println(divisor);
    }
    
    public static void onInvalidMove() {
        System.out.println(divisor);
        System.out.println("\tError!! Movimiento no valido, intente otra vez");
        System.out.println(divisor);
    }
    
    
    public static void printBoard(Board board){
        System.out.println("\n"+board.toString());
        System.out.println(divisor);
    }
    
    public static String readName(String col){
        System.out.println(divisor);
        System.out.println("Ingrese nombre del jugador "+ col);
        return reader.next();
    }

    public static void welcome() {
        System.out.println(divisor);
        System.out.println("Bienvenido al juego:!!");
    }
    
    public static int menu(){
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
                    onError("Opcion no valida");
                }
            }catch(NumberFormatException e){
                onError("Se espera valor numerico");
            }
        }
        return out;
    }
    
    public static void printCemetery(Player w, Player b) {
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
    
    
    public static void whosePlayer(Player player){
        String playerColor= (player.isColor())?"White":"Black";
        System.out.println(divisor);
        System.out.println("Turno del jugador " + player.getName() + " - " + playerColor);
    }
    

    public static String coordinateRead(){
        String coord=new String();
        boolean flag=true; 
        do{
            coord= reader.next();
            if(coord.length()!=2){
                onError("Ingrese una coordenada valida, es decir letra y número");
                continue;
            }
            coord=coord.toLowerCase();
            if(coord.charAt(0)>='a' && coord.charAt(0)<='h' && coord.charAt(1)>='1' && coord.charAt(1)<='8'){
                flag=false;
            }else{
                onError("Coordenedas Incorrectas o fuera del limite");
            }
        }while(flag);
        return coord;
    }
    
    public static ArrayList<ArrayList<Integer>> inputMove() {
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
                onDuplicateCoordinate();
                moveCoordinates.clear();
            }else{
                flag=false;
            }
        }while(flag);
        
        return moveCoordinates;
    }

    public static Piece askPromotioPiece(boolean color) {
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
                    onError("Opcion no listada");
                }
            }catch (NumberFormatException e){
                onError("Se espera valor numerico");
            }
        }
        return returnPiece;
    }

    public static int movementOptions() {
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
                    onError("Opcion no listada");
                }
            }catch(NumberFormatException e){
                onError("Se espera valor numerico");
            }
        }
        return out;
    }

    public static void showPlayHist(Player[] player) {
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
    
     private static boolean overWriteMessage(String fileName) {
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
                    onError("Opción no listada");
                }
            }catch(NumberFormatException e){
                UI.onError("Se espera un valor numerico");
            }
        }while(flag);
        return false;
    }

    public static String saveGameRequest(){
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
                onError("La dirección es incorrecta, debe ingresar solo la dirección de LA CARPETA");
            }
        }
        
        return path;
    }

    public static String loadGameRequest() {
        System.out.println(divisor);
        System.out.println("Ingrese la dirección del archivo (Ej: C:\\archivo.chess)");
        System.out.println("Nota: Puede ser otra extensión, siempre que el archivo sea compatible. ");
        String fileName=reader.next();
        return fileName;
    }

    public static void onSaveSuceed() {
        System.out.println(divisor);
        System.out.println("\t Se ha guardado la partida Exitosamente");
        System.out.println(divisor);
    }

    public static void onSaveFailure() {
        System.out.println(divisor);
        System.out.println("\t No se ha guardado la partida.");
        System.out.println(divisor);
    }

    public static void onLoadFailure() {
        System.out.println(divisor);
        System.out.println("\t No se puede cargar la partida, nueva partida iniciada");
        System.out.println(divisor);
    }
    
    public static void onLoadSuceed() {
        System.out.println(divisor);
        System.out.println("\t Carga realizada correctamente!");
        System.out.println(divisor);
    }

}
