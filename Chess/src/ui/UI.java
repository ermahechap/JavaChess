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
    
}
