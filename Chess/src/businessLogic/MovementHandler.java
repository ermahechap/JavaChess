package businessLogic;

import java.util.ArrayList;
import data.Board;
import data.Piece;
import data.Player;
import ui.UI;

public class MovementHandler {
    
    protected static boolean isValidMove(Board board, ArrayList<ArrayList<Integer>> moveData,int whichPlayer){
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        
        if(board.getGameBoard()[from[0]][from[1]].getPiece()==null){
            return false;
        }else{
            Piece piece=board.getGameBoard()[from[0]][from[1]].getPiece();
            if(Character.isLowerCase(piece.getPieceSign()) && whichPlayer==0){
                return piece.pieceCheck(board,from,to);
            }
            if(Character.isUpperCase(piece.getPieceSign()) && whichPlayer==1){
                return piece.pieceCheck(board,from,to);
            }
        }
        return false;
    }
    

    protected static Object[] performMove(Board board, Player[] player, ArrayList<ArrayList<Integer>> moveData) {
        //need to return object and players
        
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        
        if(board.getGameBoard()[to[0]][to[1]].getPiece()!=null){// to add piece to cemetery
            Piece toDelete=board.getGameBoard()[to[0]][to[1]].getPiece();
            toDelete.setMoved(false);//in order to reboot it to initial state and perform .remove later
            if(Character.isLowerCase(toDelete.getPieceSign())){
                player[0].addPieceCemetery(toDelete);
                player[0].getPieces().remove(toDelete);// only removes first occurrence of the piece
            }else{
                player[1].addPieceCemetery(toDelete);
                player[1].getPieces().remove(toDelete);// only removes first occurrence of the piece
            }
        }
        
        Piece toSet=board.getGameBoard()[from[0]][from[1]].getPiece();
        toSet.setMoved(true);
        board.getGameBoard()[to[0]][to[1]].setPiece(toSet);//moves piece
        board.getGameBoard()[from[0]][from[1]].setPiece(null);//clears square
        
        Object dataReturn[]= {board,player};
        
        return dataReturn;
    }
    
    protected boolean isCheck(){
        //put code here
        return true;
    }
    
    protected boolean isCheckMate(){
        //put code here
        return true;
    }
}
