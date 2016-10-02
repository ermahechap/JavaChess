package businessLogic;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.util.ArrayList;
import data.Board;
import data.Piece;
import data.Player;
import ui.UI;

public class MovementHandler {
    public static int countRep=0;
    
    protected static boolean isValidMove(Board board, ArrayList<ArrayList<Integer>> moveData,int whichPlayer){
        int from[]=Functional.splitDataPair(moveData.get(0));//row,col
        int to[]=Functional.splitDataPair(moveData.get(1));//row,col
        
        if(board.getGameBoard()[from[0]][from[1]].getPiece()==null){
            return false;
        }else{
            Piece piece=board.getGameBoard()[from[0]][from[1]].getPiece();
            if(Character.isLowerCase(piece.getPieceSign()) && whichPlayer==0){
                return piece.pieceCheckMove(board,from,to);
            }
            if(Character.isUpperCase(piece.getPieceSign()) && whichPlayer==1){
                return piece.pieceCheckMove(board,from,to);
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
    
    // just coded 50move repetition, accepted as chess variation with only this rule and stalemate
    protected static boolean drawFifty(){
        if(countRep==50){//repetition rule
            return true;
        }
        return false;
    }
    

    protected static boolean isCheck(Board board, Player[] player, byte turn){
        int kingpos[]=new int[2];
        char cmp;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board.getGameBoard()[i][j].getPiece().getClass().toString().equals("class data.King")){
                    cmp=board.getGameBoard()[i][j].getPiece().getPieceSign();
                    int pos=(cmp=='k')?0:1;
                    if(pos==turn)kingpos[0]=i;kingpos[1]=j;
                }
            }
        }
        
        if(turn==0){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(Character.isUpperCase(board.getGameBoard()[i][j].getPiece().getPieceSign())){//if turn 0(white), need to compare with black pieces(uppercase)
                        if(board.getGameBoard()[i][j].getPiece().pieceCheckMove(board, new int[]{i,j}, kingpos))return true;
                    }
                }
            }
        }else{
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(Character.isLowerCase(board.getGameBoard()[i][j].getPiece().getPieceSign())){//if turn 1(black), need to compare with white pieces(lowercase)
                        if(board.getGameBoard()[i][j].getPiece().pieceCheckMove(board, new int[]{i,j}, kingpos))return true;
                    }
                }
            }
        }
        return false;
    }
    
    protected static boolean isCheckRemovable(Board board, Player[] player, byte turn){
        boolean flag=true;
        //move king
        
        
        //capture piece
        
        
        //block piece
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
        
        if(cmp=='p' ||cmp=='P' || board.getGameBoard()[to[0]][to[1]].getPiece()!=null)countRep=0;//counter goes zero in case a pawn is moved or piece is captured
        else countRep++;
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
        countRep++;//counts as a fifty move rule
        return dataReturn;
    }
}
