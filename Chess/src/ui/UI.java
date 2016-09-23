package ui;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import data.Board;
import data.Player;

public class UI {
    private static String divisor = "-------------------";
    private static Scanner reader = new Scanner(System.in);
    
    public static void onError(){
        System.out.println("Error!!, intente otra vez");
    }
    
    public static void printBoard(Board board){
        System.out.println(divisor);
        System.out.println(board.stringBoard());
    }
    
    public static String readName(String col){
        System.out.println(divisor);
        System.out.println("Ingrese nombre del jugador "+ col);
        return reader.nextLine();
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
    
    public static ArrayList<Integer> splitCoordinates(String str){
        ArrayList<Integer> coord= new ArrayList<>();
        //to set 0,0 as upper left corner
        coord.add(((int)str.charAt(0))-((int)'A'));
        coord.add(8-((int)str.charAt(1)));
        return coord;
    }
    
    public static ArrayList<ArrayList<Integer>> inputMove(Player player) {
        String playerColor= (player.isColor())?"White":"Black";
        String moveText=new String();
        
        ArrayList<ArrayList<Integer>> moveCoordinates= new ArrayList<>();
        
        System.out.println(divisor);
        System.out.println("Turno del jugador " + player.getName() + " - " + playerColor);
        System.out.println("Ingrese Coordenadas de la pieza, es decir letra y numero, por");
        System.out.println("ejemplo ->e4");
        moveText= reader.nextLine();
        moveCoordinates.add(splitCoordinates(moveText));
        
        System.out.println("Ingrese Coordenadas de destino de la misma forma:");
        moveText= reader.nextLine();
        moveCoordinates.add(splitCoordinates(moveText));
        
        return moveCoordinates;
    }
    
    
    
}
