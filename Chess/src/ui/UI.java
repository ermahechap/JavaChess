package ui;
import java.util.Scanner;
import data.Board;
import data.Player;

public class UI {
    public static String stringInput(){
        Scanner scanner= new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static Player createPlayer(){
        Player player=new Player();
        
        return player;
    }
    
}
