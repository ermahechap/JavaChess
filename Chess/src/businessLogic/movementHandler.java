package businessLogic;

import java.util.ArrayList;
import data.Board;
import data.Player;

public class movementHandler {
    
    public static boolean isValidMove(Board board, ArrayList<ArrayList<Integer>> moveData){
        int from[]=functional.splitDataPair(moveData.get(0));//row,col
        int to[]=functional.splitDataPair(moveData.get(1));//row,col
        
        if(board.getGameBoard()[from[0]][from[1]].getPiece()==null){
            return false;
        }else{
            if(board.getGameBoard()[from[0]][from[1]].getPiece().pieceCheck(board,from,to)){
                return true;
            }
        }
        return false;
    }
    

    protected static Board performMove(Board board, Player[] player, ArrayList<ArrayList<Integer>> moveData) {
        int from[]=functional.splitDataPair(moveData.get(0));//row,col
        int to[]=functional.splitDataPair(moveData.get(1));//row,col
        return board;
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
