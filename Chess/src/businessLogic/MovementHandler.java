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
    
    static boolean isKingFrom(Board board,ArrayList<ArrayList<Integer>> moveData) {
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        return (board.getGameBoard()[from[0]][from[1]].getPiece().getClass().toString().equals("class data.King"));
    }

    static boolean isRookTo(Board board,ArrayList<ArrayList<Integer>> moveData) {
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        return (board.getGameBoard()[to[0]][to[1]].getPiece().getClass().toString().equals("class data.Rook"));
    }
    
    static boolean canCastle(Board board, ArrayList<ArrayList<Integer>> moveData, byte turn) {
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        
        if(!(MovementHandler.isKingFrom(board,moveData) && MovementHandler.isRookTo(board,moveData))){
            return false;
        }
        
        Piece king =board.getGameBoard()[from[0]][from[1]].getPiece();
        Piece rook =board.getGameBoard()[to[0]][from[0]].getPiece();
        
        if(king.isMoved() || rook.isMoved())return false;
        
        int op=(from[1]>to[1])?-1:1;
        for(int i=0;i!=to[i];from[i]+=op){
            if(board.getGameBoard()[from[0]][i].getPiece()!=null)return false;
        }
        
        return true;
    }
    
    protected static boolean isCheck(){
        //put code here
        return false;
    }
    
    protected static boolean isCheckMate(){
        //put code here
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
        Piece originalPiece=toSet;
        
        //PawnPromotion
        char cmp=toSet.getPieceSign();
        int pos=(cmp=='p')?0:(cmp=='P')?7:-1;
        int who=(Character.isLowerCase(cmp))?0:1;
        
        if ((cmp=='p' || cmp=='P') && pos==to[0]){
            toSet.setMoved(false);
            player[who].getPieces().remove(toSet);

            toSet=UI.askPromotioPiece((cmp=='p')?true:false);
            player[who].getPieces().add(toSet);

            player[who].addToHistory(from, to, board.getGameBoard()[from[0]][from[1]].getPiece(),
                board.getGameBoard()[to[0]][to[1]].getPiece(), toSet);
        }else{
            // in case there is no promotion
            player[who].addToHistory(from, to, board.getGameBoard()[from[0]][from[1]].getPiece(),
                board.getGameBoard()[to[0]][to[1]].getPiece(), null);
        }
        
        
        toSet.setMoved(true);
        board.getGameBoard()[to[0]][to[1]].setPiece(toSet);//moves piece
        board.getGameBoard()[from[0]][from[1]].setPiece(null);//clears square from
        Object dataReturn[]= {board,player};
        
        return dataReturn;
    }

    protected static Object[] performCastling(Board board, Player[] player, ArrayList<ArrayList<Integer>> moveData) {
        //need to return object and players
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        
        Piece king = board.getGameBoard()[from[0]][from[1]].getPiece();
        Piece rook = board.getGameBoard()[to[0]][to[1]].getPiece();
        int who=(Character.isLowerCase(king.getPieceSign()))?0:1;
        
        board.getGameBoard()[from[0]][from[1]].setPiece(null);//clear unused pieces
        board.getGameBoard()[to[0]][to[1]].setPiece(null);
        
        king.setMoved(true);
        rook.setMoved(true);
        
        if(Math.abs(from[1]-to[1])==3){
            //short castling
            board.getGameBoard()[from[0]][6].setPiece(king);
            board.getGameBoard()[to[0]][5].setPiece(rook);
            player[who].addToHistory("0-0");
        }else{
            //long castling
            board.getGameBoard()[from[0]][2].setPiece(king);
            board.getGameBoard()[to[0]][3].setPiece(rook);
            player[who].addToHistory("0-0-0");
        }
        
        Object dataReturn[]= {board,player};
        
        return dataReturn;
    }
}
