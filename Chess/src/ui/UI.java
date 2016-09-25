package ui;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import data.Board;
import data.Piece;
import data.Player;
import businessLogic.Functional;

public class UI {
    private static String divisor = "-------------------";
    private static Scanner reader = new Scanner(System.in);
    
    public static void onError(){
        System.out.println(divisor);
        System.out.println("\tError!!, intente otra vez");
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
        System.out.println("\n"+board.stringBoard());
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
    
    public static int menu() {
        System.out.println(divisor);
        System.out.println("Seleccione una opcion");
        System.out.println("1. Iniciar juego");
        System.out.println("2. Salir");
        return reader.nextInt();
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

    private static String coordinateRead(){
        String moveText=new String();
        boolean flag=true; 
        do{
            moveText= reader.next();
            if(moveText.charAt(0)>='a' && moveText.charAt(0)<='h' && moveText.charAt(1)>='1' && moveText.charAt(1)<='8'){
                flag=false;
            }else{
                onError();
            }
        }while(flag);
        return moveText;
    }
    
    public static ArrayList<ArrayList<Integer>> inputMove(Player player) {
        String playerColor= (player.isColor())?"White":"Black";
        boolean flag=true;
        ArrayList<ArrayList<Integer>> moveCoordinates= new ArrayList<>();
        
        do{
            System.out.println(divisor);
            System.out.println("Turno del jugador " + player.getName() + " - " + playerColor);
            System.out.println("Ingrese Coordenadas de la pieza, es decir letra y numero, por");
            System.out.println("ejemplo ->e4");
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

}
