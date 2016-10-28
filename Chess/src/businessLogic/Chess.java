package businessLogic;

import data.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ui.UI;

public class Chess {
    private static Player player[]= new Player[2];
    private static Board board;
    
    public static void main(String[] args) {
        UI.welcome();
        startGame();
    }

    public static void startGame(){
        boolean flag=true;
        do{
            ManagePlayerTurn.setTurn(0);
            int readValue=UI.menu();
            switch (readValue) {
                case 1://new game
                    newGame();
                    gameLoop();
                    break;
                case 2://load game
                    loadGame();
                    gameLoop();
                    break;
                case 3://exit
                    flag=false;
                    break;
                default:
                    UI.onError("Opcion no listada");
                    break;
            }
        }while(flag);
    }
    
    private static void newGame(){
        player[0]=new Player(UI.readName("Blancas"), true);
        player[1]= new Player(UI.readName("Negras"), false);
        board = new Board(player[0], player[1]);
    }
    private static void loadGame(){
        String filePath=UI.loadGameRequest();
        try {
            FileInputStream fileIn= new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(fileIn);
            player[0]=(Player)os.readObject();
            player[1]=(Player)os.readObject();
            ManagePlayerTurn.setTurn(os.readInt());
            board=(Board)os.readObject();
            os.close();
            UI.onLoadSuceed();
        } catch (Exception e) {
            UI.onLoadFailure();
            newGame();
        }
    }
    
    private static void saveGame(){
        String fileName=UI.saveGameRequest();
        try {
            FileOutputStream fileOut= new FileOutputStream(fileName,false);
            ObjectOutputStream os = new ObjectOutputStream(fileOut);
            //writes players, current turn and board
            os.writeObject(player[0]);
            os.writeObject(player[1]);
            os.writeInt(ManagePlayerTurn.getTurn());
            os.writeObject(board);
            os.close();
            UI.onSaveSuceed();
        } catch (Exception ex) {
            UI.onSaveFailure();
        }
    }
    
    private static void gameLoop(){
        boolean flag=true;
        
        do{
            UI.printCemetery(player[0],player[1]);
            UI.printBoard(board);
            UI.whosePlayer(player[ManagePlayerTurn.getTurn()]);
            
            //onCheck
            if(MovementHandler.isCheck(board,player,ManagePlayerTurn.getTurn())){
                if(!MovementHandler.isCheckRemovable(board,player,ManagePlayerTurn.getTurn())){
                    UI.checkMate(player,ManagePlayerTurn.getTurn());
                    break;
                }
                UI.onCheck(player,ManagePlayerTurn.getTurn());
            }
            
            //onDraw
            if(MovementHandler.drawFifty()){
                UI.messageDrawFifty(player);
                break;
            }
            //saltemate draw
            if(MovementHandler.isKingStalemate(board, player, ManagePlayerTurn.getTurn())){
                UI.messageStalemate();
                break;
            }
            int opt=UI.movementOptions();
            
            switch (opt) {
                case 1:
                    while(true){
                        ArrayList<ArrayList<Integer>> moveData = UI.inputMove();
                        if(MovementHandler.isFromEmpty(board, Functional.splitDataPair(moveData.get(0)))){
                            UI.onError("No hay pieza en la posición inicial");
                            break;
                        }
                        if(MovementHandler.isValidMove(board, moveData,ManagePlayerTurn.getTurn())){
                            Object boardPlayer[]=MovementHandler.performMove(board, player,moveData);
                            if(MovementHandler.isCheck((Board) boardPlayer[0],(Player[]) boardPlayer[1],ManagePlayerTurn.getTurn())){//in case the move put the king in check
                                UI.onInvalidMoveCheck(player,ManagePlayerTurn.getTurn());
                                break;
                            }
                            //if king is not in check, then we proceed to assign the genrated board to the current board.
                            MovementHandler.setPieceCheckCoord(new int[]{-1,-1});
                            board=(Board) boardPlayer[0];//note: casting is required, return type is object, need to be board
                            player=(Player[]) boardPlayer[1];
                            ManagePlayerTurn.changeTurn();
                            break;
                        }else if(MovementHandler.canCastle(board, moveData,ManagePlayerTurn.getTurn()) && !MovementHandler.isCheck(board,player,ManagePlayerTurn.getTurn())){//castling
                            Object boardPlayer[]=MovementHandler.performCastling(board, player,moveData);
                            board=(Board) boardPlayer[0];//note: casting is required, return type is object, need to be board
                            player=(Player[]) boardPlayer[1];
                            ManagePlayerTurn.changeTurn();
                            break;
                        }else{
                            UI.onInvalidMove();
                        }
                    }   break;
                case 2:
                    UI.showPlayHist(player);
                    break;
                case 3:
                    saveGame();
                    break;
                case 4:
                    UI.onQuitGame(player[ManagePlayerTurn.getTurn()]); // the player who quits, looses
                    UI.onWinMessage(player[(ManagePlayerTurn.getTurn()+1)%2]); // the next player wins
                    flag=false;
                    break;
                default:
                    UI.onError("Opción no listada, intente nuevamente");
                    break;
            }
        }while (flag);
    }    
    
}
