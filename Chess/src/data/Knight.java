package data;
public class Knight extends Piece{    

    public Knight(char pieceSign, boolean moved) {
        super(pieceSign, moved);
        //here it goes movement matrix
        super.setDx(new int[]{-2,-1,1,2,2,1,-1,-2});//rows
        super.setDy(new int[]{1,2,2,1,-1,-2,-2,-1});//cols
    }
    
    @Override
    public boolean pieceCheck(Board board, int[]from,int[]to){
        int myDx[] =super.getDx();
        int myDy[] =super.getDy();
        for(int i=0;i<8;i++){
            if(from[0]+myDx[i]<0 || from[0]+myDx[i]>7)continue;
            if(from[0]+myDy[i]<0 || from[0]+myDy[i]>7)continue;
            
            if(from[0]+myDx[i]==to[0] && from[1]+myDy[i]==to[1]){
                char pieceFrom=board.getGameBoard()[from[0]][from[1]].getPiece().getPieceSign();
                char pieceTo=board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign();
                
                if(board.getGameBoard()[to[0]][to[1]].getPiece()==null)return true; // true if there is no piece in that box
                return !(Character.isLowerCase(pieceFrom) && Character.isLowerCase(pieceTo));// false if pieces are from the same player
            }
        }
        return false;
    }
}
