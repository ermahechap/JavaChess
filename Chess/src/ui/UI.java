package ui;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import data.Board;
import data.Piece;
import data.Player;

public class UI {
    private static String divisor = "-------------------";
    private static Scanner reader = new Scanner(System.in);
    
    public static void onError(){
        System.out.println("Error!!, intente otra vez");
    }
    
    private static void onDuplicateCoordinate(){
        System.out.println("Error!!, Cooerdenadas iguales, intente otra vez");
    }
    
    public static void printBoard(Board board){
        System.out.println(divisor);
        System.out.println(board.stringBoard());
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
        System.out.println("Blancas->" );
        for (int i=0;i<w.getCemetery().size();i++) {
            System.out.print(w.getCemetery().get(i).getPieceSign());
        }
        System.out.println("Negras->" );
        for (int i=0;i<b.getCemetery().size();i++) {
            System.out.print(b.getCemetery().get(i).getPieceSign());
        }
        System.out.println(divisor);
    }
    
    private static ArrayList<Integer> splitCoordinates(String str){
        ArrayList<Integer> coord= new ArrayList<>();
        //to set 0,0 as upper left corner
        coord.add(8-((int)str.charAt(1)-(int)'0'));//rows first
        coord.add(((int)str.charAt(0))-((int)'A'));//cols second
        return coord;
    }
    
    private static String coordinateRead(){
        String moveText=new String();
        do{
            moveText= reader.next();
            if(!(moveText.charAt(0)>='a' && moveText.charAt(0)<='h' && moveText.charAt(1)>='1' && moveText.charAt(1)<='8')){
                onError();
            }
        }while(moveText.charAt(0)>='a' && moveText.charAt(0)<='h' && moveText.charAt(1)>='1' && moveText.charAt(1)<='8');
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
            moveCoordinates.add(splitCoordinates(coordinateRead()));

            System.out.println("Ingrese Coordenadas de destino de la misma forma:");
            moveCoordinates.add(splitCoordinates(coordinateRead()));
            
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
