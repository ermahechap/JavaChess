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
        Player player[]= new Player[2];
        player[0]=new Player(UI.readName("Blancas"), true);
        player[1]= new Player(UI.readName("Negras"), false);
        Board board = new Board(player[0], player[1]);
        
        boolean flag=true;
        int turn=0;
        
        do{
            UI.printCemetery(player[0],player[1]);
            UI.printBoard(board);
            ArrayList<ArrayList<Integer>> moveData = UI.inputMove(player[turn]);
            if(movementHandler.isValidMove(board, moveData)){//missing if it is check, checkmate conditions, PUT IT LATER
                board=movementHandler.performMove(board, player,moveData);
                if(turn==1)turn=0;//switch turn
                else turn=1;
            }else{
                UI.onInvalidMove();
            }
        }while (flag);
    }
    
}
