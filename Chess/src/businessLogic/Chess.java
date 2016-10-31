package businessLogic;

import data.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ui.UIText;

public class Chess {
    private static Player player[]= new Player[2];
    private static Board board;
    
    public static void main(String[] args) {
        UIText.welcome();
        startGame();
    }

    public static void startGame(){
        boolean flag=true;
        do{
            ManagePlayerTurn.setTurn(0);
            int readValue=UIText.menu();
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
                    UIText.onError(0);
                    break;
            }
        }while(flag);
    }
    
    private static void newGame(){
        player[0]=new Player(UIText.readName("Blancas"), true);
        player[1]= new Player(UIText.readName("Negras"), false);
        board = new Board(player[0], player[1]);
    }
    private static void loadGame(){
        String filePath=UIText.loadGameRequest();
        try {
            FileInputStream fileIn= new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(fileIn);
            player[0]=(Player)os.readObject();
            player[1]=(Player)os.readObject();
            ManagePlayerTurn.setTurn(os.readInt());
            board=(Board)os.readObject();
            os.close();
            UIText.onLoadSuceed();
        } catch (Exception e) {
            UIText.onLoadFailure();
            newGame();
        }
    }
    
    private static void saveGame(){
        String fileName=UIText.saveGameRequest();
        try {
            FileOutputStream fileOut= new FileOutputStream(fileName,false);
            ObjectOutputStream os = new ObjectOutputStream(fileOut);
            //writes players, current turn and board
            os.writeObject(player[0]);
            os.writeObject(player[1]);
            os.writeInt(ManagePlayerTurn.getTurn());
            os.writeObject(board);
            os.close();
            UIText.onSaveSuceed();
        } catch (Exception ex) {
            UIText.onSaveFailure();
        }
    }
    
    private static void gameLoop(){
        boolean flag=true;
        
        do{
            UIText.printCemetery(player[0],player[1]);
            UIText.printBoard(board);
            UIText.whosePlayer(player[ManagePlayerTurn.getTurn()]);
            
            //onCheck
            if(MovementHandler.isCheck(board,player,ManagePlayerTurn.getTurn())){
                if(!MovementHandler.isCheckRemovable(board,player,ManagePlayerTurn.getTurn())){
                    UIText.checkMate(player,ManagePlayerTurn.getTurn());
                    break;
                }
                UIText.onCheck(player,ManagePlayerTurn.getTurn());
            }
            
            //onDraw
            if(MovementHandler.drawFifty()){
                UIText.messageDrawFifty(player);
                break;
            }
            //saltemate draw
            if(MovementHandler.isKingStalemate(board, player, ManagePlayerTurn.getTurn())){
                UIText.messageStalemate();
                break;
            }
            int opt=UIText.movementOptions();
            
            switch (opt) {
                case 1:
                    while(true){
                        ArrayList<ArrayList<Integer>> moveData = UIText.inputMove();
                        if(MovementHandler.isFromEmpty(board, Functional.splitDataPair(moveData.get(0)))){
                            UIText.onError(1);
                            break;
                        }
                        if(MovementHandler.isValidMove(board, moveData,ManagePlayerTurn.getTurn())){
                            Object boardPlayer[]=MovementHandler.performMove(board, player,moveData);
                            if(MovementHandler.isCheck((Board) boardPlayer[0],(Player[]) boardPlayer[1],ManagePlayerTurn.getTurn())){//in case the move put the king in check
                                UIText.onInvalidMoveCheck(player,ManagePlayerTurn.getTurn());
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
                            UIText.onInvalidMove();
                        }
                    }   break;
                case 2:
                    UIText.showPlayHist(player);
                    break;
                case 3:
                    saveGame();
                    break;
                case 4:
                    UIText.onQuitGame(player[ManagePlayerTurn.getTurn()]); // the player who quits, looses
                    UIText.onWinMessage(player[(ManagePlayerTurn.getTurn()+1)%2]); // the next player wins
                    flag=false;
                    break;
                default:
                    UIText.onError(0);
                    break;
            }
        }while (flag);
    }    
    
}
