package ui;
import java.util.Scanner;
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
    
    public static String inputMove(Player player) {
        String playerColor= (player.isColor())?"White":"Black";
        System.out.println(divisor);
        System.out.println("Turno del jugador " + player.getName() + " - " + playerColor);
        System.out.println("Ingrese un movimiento en notación algebraica:");
        System.out.println("1. Iniciar juego");
        System.out.println("2. Salir");
        return reader.nextLine();
    }
    
    
    
}
