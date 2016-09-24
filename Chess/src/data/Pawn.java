package data;

public class Pawn extends Piece{
    
    public Pawn(char pieceSign, boolean moved) {
        super(pieceSign, moved);
        //note, pawn has an extra move when it eats pieces, matrix also has to change according to its color
        
        if(Character.isLowerCase(pieceSign)){
            super.setDx(new int[]{-1,-2});//blancas
        }else{
            super.setDx(new int[]{1,2});//negras
        }
        super.setDy(new int[]{0,0,1,-1});//last two - eat action
    }
    @Override
    public boolean pieceCheck(Board board, int[]from,int[]to){
        if(super.isMoved()==true){//if pawn already moved, just allowed to perform a movement
            if(Character.isLowerCase(super.getPieceSign())){
                super.setDx(new int[]{-1});//blancas
            }else{
                super.setDx(new int[]{1});//negras
            }
        }
        int myDx[] =super.getDx();
        int myDy[] =super.getDy();
        
        for(int i=0;i<myDx.length;i++){
            for(int j=0;j<myDy.length;j++){
                if(from[0]+myDx[i]<0 || from[0]+myDx[i]>7)continue;
                if(from[0]+myDy[j]<0 || from[0]+myDy[j]>7)continue;
                
                if(from[0]+myDx[i]==to[0] && from[1]+myDy[j]==to[1] && j<2){// only standar moves
                    char pieceFrom=board.getGameBoard()[from[0]][from[1]].getPiece().getPieceSign();
                    char pieceTo=board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign();

                     if(board.getGameBoard()[to[0]][to[1]].getPiece()==null)return true; // true if there is no piece in that box

//                    if(Character.isLowerCase(pieceFrom) && Character.isLowerCase(pieceTo)) return false;// this happens only, because piece can moe forward if there is a piece blocking path
//                    else return false;
                    return false; //do the same as the code commented above
                }
                
                if(from[0]+myDx[i]==to[0] && from[1]+myDy[j]==to[1] && j>=2){// capture move
                    char pieceFrom=board.getGameBoard()[from[0]][from[1]].getPiece().getPieceSign();
                    char pieceTo=board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign();
                    return !(Character.isLowerCase(pieceFrom) && Character.isLowerCase(pieceTo));//false if there is a piece of the same color, cant be captured
                }
            }
        }
        return false;
    }
}
