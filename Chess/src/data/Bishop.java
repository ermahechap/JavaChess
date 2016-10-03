package data;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(char pieceSign) {
        super(pieceSign);
        super.setDx(new int[]{-1,1,1,-1});//rows
        super.setDy(new int[]{1,1,-1,-1});//cols
    }
    
    public Bishop(boolean color){
        this((color)?'b':'B');
    }
    
    @Override
    public boolean pieceVerifyMove(Board board, int[]from,int[]to){
        int myDx[] =super.getDx();
        int myDy[] =super.getDy();
        
        double a=from[1]-to[1];
        double b=from[0]-to[0];
        if(b==0 || a==0)return false;
        double m=(a/b);
        if(Math.abs(m)==1){
            Piece sourceP=board.getGameBoard()[from[0]][from[1]].getPiece();
            if(a<=-1 && b>=1){
                return dfs(board, sourceP, from, to, myDx[0],myDy[0]);
            }else if(a<=-1 && b<=-1){
                return dfs(board, sourceP, from, to, myDx[1],myDy[1]);
            }else if(a>=1 && b<=-1){
                return dfs(board, sourceP, from, to, myDx[2],myDy[2]);
            }else if(a>=1 && b>=1){
                return dfs(board, sourceP, from, to, myDx[3],myDy[3]);
            }
        }
        return false;
    }
    
    private boolean dfs(Board board,Piece sourceP, int[]from,int[]to,int x,int y){  //not really a dfs, just a clever recursion
        super.setLastMovePath(new ArrayList<>());//clear path
        while(true){
            if(from[0]+x<0 || from[0]+x>7)break;
            if(from[1]+y<0 || from[1]+y>7)break;
            
            if(from[0]+x==to[0] && from[1]+y==to[1]){
                if(board.getGameBoard()[to[0]][to[1]].getPiece()==null)return true; // true if there is no piece in that box
                char pieceTo=board.getGameBoard()[to[0]][to[1]].getPiece().getPieceSign();
                
                if(Character.isLowerCase(sourceP.getPieceSign()) && Character.isUpperCase(pieceTo)){
                    return true; 
                }
                if(Character.isUpperCase(sourceP.getPieceSign()) && Character.isLowerCase(pieceTo)){
                    return true;
                }
                break;
            }
            
            if(board.getGameBoard()[from[0]+x][from[1]+y].getPiece()!=null)break;
            addMovePath(new int[]{from[0],from[1]});
            from[0]+=x;
            from[1]+=y;
            return dfs(board,sourceP,from,to,x,y);
        }
        return false;
    }
}
