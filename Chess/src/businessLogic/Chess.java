package businessLogic;

import data.*;
import java.util.ArrayList;
import ui.UI;

public class Chess {

    public static void main(String[] args) {
        UI.welcome();
        startGame();
    }

    public static void startGame(){
        boolean flag=true;
        do{
            int readValue=UI.menu();
            switch (readValue) {
                case 1:
                    gameLoop();
                    break;
                case 2:
                    flag=false;
                    break;
                default:
                    UI.onError();
                    break;
            }
        }while(flag);
    }
    
    public static void gameLoop(){
        Player playerWhite = new Player(UI.readName("Blancas"), true);
        Player playerBlack  = new Player(UI.readName("Negras"), false);
        
        boolean flag=true;
        boolean turn=true;
        
        do{
            ArrayList<ArrayList<Integer>> moveData = UI.inputMove(player);
            
            Board board = new Board(playerWhite, playerBlack);
            UI.printBoard(board);
        }while (flag);
    }
    
}
