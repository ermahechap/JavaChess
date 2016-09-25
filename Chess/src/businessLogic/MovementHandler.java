package businessLogic;

import java.util.ArrayList;
import data.Board;
import data.Piece;
import data.Player;
import ui.UI;

public class MovementHandler {
    
    public static boolean isValidMove(Board board, ArrayList<ArrayList<Integer>> moveData,int whichPlayer){
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        
        if(board.getGameBoard()[from[0]][from[1]].getPiece()==null){
            System.out.println("From NULL");
            return false;
        }else{
            Piece piece=board.getGameBoard()[from[0]][from[1]].getPiece();
            System.out.println(Integer.toString(whichPlayer));
            System.out.println(Boolean.toString(Character.isLowerCase(piece.getPieceSign())));
            if(Character.isLowerCase(piece.getPieceSign()) && whichPlayer==0){
                return piece.pieceCheck(board,from,to);
            }else if(Character.isUpperCase(piece.getPieceSign()) && whichPlayer==1){
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
            if(Character.isLowerCase(board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign())){
                player[0].addPieceCemetery(board.getGameBoard()[to[0]][to[1]].getPiece());
                player[0].getPieces().remove(board.getGameBoard()[to[0]][to[1]].getPiece());// only removes first occurrence of the piece
            }else{
                player[1].addPieceCemetery(board.getGameBoard()[to[0]][to[1]].getPiece());
                player[1].getPieces().remove(board.getGameBoard()[to[0]][to[1]].getPiece());// only removes first occurrence of the piece
            }
        }
        
        board.getGameBoard()[to[0]][to[1]].setPiece(board.getGameBoard()[from[0]][from[1]].getPiece());//moves piece
        board.getGameBoard()[from[0]][from[1]].setPiece(null);//clears square
        
        Object dataReturn[]= {board,player};
        
        return dataReturn;
    }
    
    public boolean isCheck(){
        //put code here
        return true;
    }
    
    public boolean isCheckMate(){
        //put code here
        return true;
    }
}
