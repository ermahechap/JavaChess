package data;
public class King extends Piece {
    
    public King(char pieceSign, boolean moved) {
        super(pieceSign, moved);
        //move matrix
        super.setDx(new int[]{-1,0,1,0,-1,1,1,-1});//rows
        super.setDy(new int[]{0,1,0,-1,1,1,-1,-1});//cols
    }
    @Override
    public boolean pieceCheck(Board board, int[]from,int[]to){
        int myDx[] =super.getDx();
        int myDy[] =super.getDy();
        for(int i=0;i<8;i++){
            if(from[0]+myDx[i]<0 || from[0]+myDx[i]>7)continue;
            if(from[1]+myDy[i]<0 || from[1]+myDy[i]>7)continue;
            
            if(from[0]+myDx[i]==to[0] && from[1]+myDy[i]==to[1]){
                if(board.getGameBoard()[to[0]][to[1]].getPiece()==null)return true; // true if there is no piece in that box
                char pieceFrom=board.getGameBoard()[from[0]][from[1]].getPiece().getPieceSign();
                char pieceTo=board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign();
                
                return !(Character.isLowerCase(pieceFrom) && Character.isLowerCase(pieceTo));// false if pieces are from the same player
            }
        }
        return false;
    }
}
