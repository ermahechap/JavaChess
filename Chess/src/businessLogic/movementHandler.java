package businessLogic;

import java.util.ArrayList;
import data.Board;

public class movementHandler {
    
    public static boolean isValidMove(Board board, ArrayList<ArrayList<Integer>> moveData){
        int from[]={moveData.get(0).get(0),moveData.get(0).get(1)};//row,col
        int to[]={moveData.get(1).get(0),moveData.get(1).get(1)};
        
        if(board.getGameBoard()[from[0]][from[1]].getPiece()==null){
            return false;
        }else{
            if(board.getGameBoard()[from[0]][from[1]].getPiece().pieceCheck(board,from,to)){
                return true;
            }
        }
        return false;
    }
    
    
    public static Board performMove(board){
        
    }
    
    
}
