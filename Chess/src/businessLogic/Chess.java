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
            ManagePlayerTurn.setTurn((byte)0);
            int readValue=UI.menu();
            switch (readValue) {
                case 1:
                    gameLoopLocal();
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
    
    public static void gameLoopLocal(){
        Player player[]= new Player[2];
        player[0]=new Player(UI.readName("Blancas"), true);
        player[1]= new Player(UI.readName("Negras"), false);
        Board board = new Board(player[0], player[1]);
        
        boolean flag=true;
        
        do{
            UI.printCemetery(player[0],player[1]);
            UI.printBoard(board);
            UI.whosePlayer(player[ManagePlayerTurn.getTurn()]);
            int opt=UI.movementOptions();
            if(opt==1){
                while(true){
                    ArrayList<ArrayList<Integer>> moveData = UI.inputMove();
                    if(MovementHandler.isValidMove(board, moveData,ManagePlayerTurn.getTurn())){//missing if it is check, checkmate conditions, PUT IT LATER
                        Object boardPlayer[]=MovementHandler.performMove(board, player,moveData);
                        board=(Board) boardPlayer[0];//note: casting is required, return type is object, need to be board
                        player=(Player[]) boardPlayer[1];
                        ManagePlayerTurn.changeTurn();
                        break;
                    }else if(MovementHandler.canCastle(board, moveData,ManagePlayerTurn.getTurn()) && !MovementHandler.isCheck()){//castling
                        Object boardPlayer[]=MovementHandler.performCastling(board, player,moveData);
                        board=(Board) boardPlayer[0];//note: casting is required, return type is object, need to be board
                        player=(Player[]) boardPlayer[1];
                        ManagePlayerTurn.changeTurn();
                        break;
                    }else{
                        UI.onInvalidMove();
                    }
                }
            }else if(opt==2){
                UI.showPlayHist(player);
            }else if (opt==3){
                UI.onQuitGame(player[ManagePlayerTurn.getTurn()]); // the player who quits, looses
                UI.onWinMessage(player[(ManagePlayerTurn.getTurn()+1)%2]); // the next player wins
                flag=false;
            }else{
                UI.onError();
            }
        }while (flag);
    }
    
}
