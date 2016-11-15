package businessLogic;

import data.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ui.UI;
import ui.UISwing;
import ui.UIText;

public class Chess {
    public static UI userUI;
    public static void main(String[] args) {
        setUserUI(args);
        userUI.welcome();
        startGame();
    }
    
    private static void setUserUI(String[] args){
        if (args.length == 0) {
            userUI = new UISwing();
        } else if (args[0].equals("text")) {
            userUI = new UIText();
        } else {
            userUI = new UISwing();
        }
    }
    
    public static void startGame(){
        boolean flag=true;
        do{
            ManagePlayerTurn.setTurn(0);
            int readValue=userUI.menu();
            switch (readValue) {
                case 1://new game
                    gameLoop(1);
                    break;
                case 2://load game
                    gameLoop(2);
                    break;
                case 3://exit
                    flag=false;
                    break;
                default:
                    userUI.onError(0);
                    break;
            }
        }while(flag);
    }
    
    private static void saveGame(Board bo,Player player[]){
        String fileName=userUI.saveGameRequest();
        try {
            FileOutputStream fileOut= new FileOutputStream(fileName,false);
            ObjectOutputStream os = new ObjectOutputStream(fileOut);
            //writes players, current turn and board
            os.writeObject(player[0]);
            os.writeObject(player[1]);
            os.writeInt(ManagePlayerTurn.getTurn());
            os.writeObject(bo);
            os.close();
            userUI.onSaveSuceed();
        } catch (Exception ex) {
            userUI.onSaveFailure();
        }
    }
    
    private static void gameLoop(int sel){
        Player player[] = new Player[2];
        Board board;
        if(sel==1){
            player=userUI.readPlayers();
            board = new Board(player[0], player[1]);
        }else if(sel==2){
            String filePath=userUI.loadGameRequest();
            try {
                FileInputStream fileIn= new FileInputStream(filePath);
                ObjectInputStream os = new ObjectInputStream(fileIn);
                player[0]=(Player)os.readObject();
                player[1]=(Player)os.readObject();
                ManagePlayerTurn.setTurn(os.readInt());
                board=(Board)os.readObject();
                os.close();
                userUI.onLoadSuceed();
            } catch (Exception e) {
                userUI.onLoadFailure();
                player=userUI.readPlayers();//new game on default
                board = new Board(player[0], player[1]);
            }
        }else{
            return;
        }
        
        boolean flag=true;
        int opt;
        userUI.createBoardInterface();//Need it only in Swing
        do{
            userUI.printCemetery(player[0],player[1]);
            userUI.printBoard(board);
            userUI.whosePlayer(player[ManagePlayerTurn.getTurn()]);
            if(userUI instanceof UISwing)userUI.showPlayHist(player);
            //onCheck
            if(MovementHandler.isCheck(board,player,ManagePlayerTurn.getTurn())){
                if(!MovementHandler.isCheckRemovable(board,player,ManagePlayerTurn.getTurn())){
                    userUI.checkMate(player,ManagePlayerTurn.getTurn());
                    break;
                }
                userUI.onCheck(player,ManagePlayerTurn.getTurn());
            }
            
            //onDraw
            if(MovementHandler.drawFifty()){
                userUI.messageDrawFifty(player);
                break;
            }
            //saltemate draw
            if(MovementHandler.isKingStalemate(board, player, ManagePlayerTurn.getTurn())){
                userUI.messageStalemate();
                break;
            }
            
            opt=userUI.movementOptions();
            
            switch (opt) {
                case 1:
                    while(true){
                        ArrayList<ArrayList<Integer>> moveData = userUI.inputMove();
                        if(moveData.get(0).equals(moveData.get(1))){//correct some behaiviour when retirving from swing, UIText has a validation for this
                            break;
                        }
                        if(MovementHandler.isFromEmpty(board, Functional.splitDataPair(moveData.get(0)))){
                            userUI.onError(1);
                            break;
                        }
                        if(MovementHandler.isValidMove(board, moveData,ManagePlayerTurn.getTurn())){
                            Object boardPlayer[]=MovementHandler.performMove(board, player,moveData);
                            if(MovementHandler.isCheck((Board) boardPlayer[0],(Player[]) boardPlayer[1],ManagePlayerTurn.getTurn())){//in case the move put the king in check
                                userUI.onInvalidMoveCheck(player,ManagePlayerTurn.getTurn());
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
                            userUI.onError(7);
                            break;
                        }
                    }
                    break;
                case 2:
                    userUI.showPlayHist(player);
                    break;
                case 3:
                    saveGame(board,player);
                    break;
                case 4:
                    userUI.onQuitGame(player[ManagePlayerTurn.getTurn()]); // the player who quits, looses
                    userUI.onWinMessage(player[(ManagePlayerTurn.getTurn()+1)%2]); // the next player wins
                    flag=false;
                    break;
                default:
                    userUI.onError(0);
                    break;
            }
        }while (flag);
    }    
}
