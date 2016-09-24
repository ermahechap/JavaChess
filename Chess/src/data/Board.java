package data;

import java.util.ArrayList;

public class Board {
    private Square[][] gameBoard;
    
    private Board(){
        gameBoard= new Square[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                char box=((i+j)%2==0)?'#':'*';
                gameBoard[i][j]=new Square(i,j,box);
            }
        }
        
    }
    public Board(Player playerW, Player playerB){
        this();
        ArrayList<Piece> whitePieces = playerW.getPieces();
        ArrayList<Piece> blackPieces = playerB.getPieces();
        int[] pos={0,0,1,2};
        for(int i=0;i<16;i++){
            String whose=whitePieces.get(i).getClass().toString();
            int r=7,r2=0,c;
            if(whose.equals("class data.Pawn")){
                r=6;
                r2=1;
                c=pos[0]++;
            }else if(whose.equals("class data.Rook")){
                c=pos[1]; pos[1]+=7;
            }else if(whose.equals("class data.Knight")){
                c=pos[2];pos[2]+=5;
            }else if(whose.equals("class data.Bishop")){
                c=pos[3];pos[3]+=3;
            }else if(whose.equals("class data.Queen")){
                c=3;
            }else{//king
                c=4;
            }
            gameBoard[r][c].setPiece(whitePieces.get(i));
            gameBoard[r2][c].setPiece(blackPieces.get(i));
        }
    }

    public Square[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Square[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String stringBoard() {
        String rows = new String();
        for(int i=0;i<8;i++){
            rows+=Integer.toString(8-i)+"    ";
            for(int j=0;j<8;j++){
                if(gameBoard[i][j].getPiece()!=null){
                    rows+=gameBoard[i][j].getPiece().getPieceSign()+" ";
                }else{
                    rows+=gameBoard[i][j].getValue()+" ";
                }
            }
            rows+="\n";
        }
        rows+="\n     A B C D E F G H";
        return rows;
    }
    
    
    
    
}
