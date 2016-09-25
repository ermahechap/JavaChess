package data;
public class Queen extends Piece{
    
    public Queen(char pieceSign) {
        super(pieceSign);
        //move matrix rook+bishop
        super.setDx(new int[]{-1,0,1,0,-1,1,1,-1});//rows
        super.setDy(new int[]{0,1,0,-1,1,1,-1,-1});//cols
    }
    
    @Override
    public boolean pieceCheck(Board board, int[]from,int[]to){
        int myDx[] =super.getDx();
        int myDy[] =super.getDy();
        
        double a=from[1]-to[1];
        double b=from[0]-to[0];
        double m=(a/b);
        
        //as a rook
        if(a==0 || b==0){
            Piece sourceP=board.getGameBoard()[from[0]][from[1]].getPiece();
            if(from[0]==to[0] && to[1]<from[1]){//same row, left
                return dfs(board,sourceP, from, to, myDx[3],myDy[3]);
                
            }else if(from[0]==to[0] && to[1]>from[1]){//same row, right
                return dfs(board,sourceP, from, to, myDx[1],myDy[1]);
                
            }else if(to[0]<from[0] && from[1]==to[1]){//same col, up
                return dfs(board,sourceP, from, to, myDx[0],myDy[0]);
                
            }else if(to[0]>from[0] && from[1]==to[1]){//same col, down
                return dfs(board,sourceP, from, to, myDx[2],myDy[2]);
                
            }
        }
        //as bishop
        if(Math.abs(m)==1){
            Piece sourceP=board.getGameBoard()[from[0]][from[1]].getPiece();
            if(a<=-1 && b>=1){
                return dfs(board, sourceP, from, to, myDx[4],myDy[4]);
            }else if(a<=-1 && b<=-1){
                return dfs(board, sourceP, from, to, myDx[5],myDy[5]);
            }else if(a>=1 && b<=-1){
                return dfs(board, sourceP, from, to, myDx[6],myDy[6]);
            }else if(a>=1 && b>=1){
                return dfs(board, sourceP, from, to, myDx[7],myDy[7]);
            }
        }
        return false;
    }
    
    
    private boolean dfs(Board board,Piece sourceP, int[]from,int[]to,int x,int y){  //not really a dfs, just a clever recursion
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
            
            from[0]+=x;
            from[1]+=y;
            return dfs(board,sourceP,from,to,x,y);//recursion 
        }
        return false;
    }  
}
