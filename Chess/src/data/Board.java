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
        for(int i=0;i<8;i++){//peones-pawns
            gameBoard[6][i].setPiece(whitePieces.get(i));
            gameBoard[1][i].setPiece(blackPieces.get(i));
        }
        //rooks - torres
        gameBoard[7][0].setPiece(whitePieces.get(8));
        gameBoard[7][7].setPiece(whitePieces.get(9));
        gameBoard[0][0].setPiece(blackPieces.get(8));
        gameBoard[0][7].setPiece(blackPieces.get(9));
        //caballos - knight
        gameBoard[7][1].setPiece(whitePieces.get(10));
        gameBoard[7][6].setPiece(whitePieces.get(11));
        gameBoard[0][1].setPiece(blackPieces.get(10));
        gameBoard[0][6].setPiece(blackPieces.get(11));
        //alfiles - bishops
        gameBoard[7][2].setPiece(whitePieces.get(12));
        gameBoard[7][5].setPiece(whitePieces.get(13));
        gameBoard[0][2].setPiece(blackPieces.get(12));
        gameBoard[0][5].setPiece(blackPieces.get(13));
        //King
        gameBoard[7][4].setPiece(whitePieces.get(14));
        gameBoard[0][4].setPiece(blackPieces.get(14));
        //Queen
        gameBoard[7][3].setPiece(whitePieces.get(15));
        gameBoard[0][3].setPiece(blackPieces.get(15));
        
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
