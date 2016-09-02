package data;

public class Piece {
    private char pieceSign;//p=peon,t=torre,c=caballo.....a,r=reina,k=king(rey) - mayusc:Negras
    private boolean moved;

    
    public Piece(char pieceSign, boolean moved) {
        this.pieceSign = pieceSign;
        this.moved = moved;
    }
    
    
    
    public char getPieceSign() {
        return pieceSign;
    }

    public void setPieceSign(char pieceSign) {
        this.pieceSign = pieceSign;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
    
    
}
