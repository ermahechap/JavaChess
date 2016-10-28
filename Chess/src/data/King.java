package data;

import java.io.Serializable;
import java.util.ArrayList;

public class King extends Piece implements Serializable{
    
    public King(char pieceSign) {
        super(pieceSign);
        //move matrix
        super.setDx(new int[]{-1,0,1,0,-1,1,1,-1});//rows
        super.setDy(new int[]{0,1,0,-1,1,1,-1,-1});//cols
    }
    public King(boolean color){
        this((color)?'k':'K');
    }
    @Override
    public boolean pieceVerifyMove(Board board, int[]from,int[]to){
        super.setLastMovePath(new ArrayList<>());//clear path
        int myDx[] =super.getDx();
        int myDy[] =super.getDy();
        for(int i=0;i<8;i++){
            if(from[0]+myDx[i]<0 || from[0]+myDx[i]>7)continue;
            if(from[1]+myDy[i]<0 || from[1]+myDy[i]>7)continue;
            
            if(from[0]+myDx[i]==to[0] && from[1]+myDy[i]==to[1]){
                if(board.getGameBoard()[to[0]][to[1]].getPiece()==null)return true; // true if there is no piece in that box
                char pieceFrom=board.getGameBoard()[from[0]][from[1]].getPiece().getPieceSign();
                char pieceTo=board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign();
                
                if(Character.isLowerCase(pieceFrom) && Character.isUpperCase(pieceTo)){
                   return true; 
                }
                if(Character.isUpperCase(pieceFrom) && Character.isLowerCase(pieceTo)){
                    return true;
                }
                return false;
            }
            addMovePath(new int[]{from[0],from[1]});
        }
        return false;
    }
}
