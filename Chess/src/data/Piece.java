package data;

public abstract class Piece {
    private char pieceSign;//p=peon,t=torre,c=caballo.....a,r=reina,k=king(rey) - mayusc:Negras
    private boolean moved;
    private int dx[];
    private int dy[];

    
    public Piece(char pieceSign, boolean moved) {
        this.pieceSign = pieceSign;
        this.moved = moved;
    }
    
    public abstract boolean pieceCheck(Board board, int[]from,int[]to);
    
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

    public int[] getDx() {
        return dx;
    }

    public void setDx(int[] dx) {
        this.dx = dx;
    }

    public int[] getDy() {
        return dy;
    }

    public void setDy(int[] dy) {
        this.dy = dy;
    }
}
