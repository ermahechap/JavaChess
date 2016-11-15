package data;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable{
    private char pieceSign;//p=peon,t=torre,c=caballo.....a,r=reina,k=king(rey) - mayusc:Negras
    private boolean moved=false;//default, false
    private int dx[];
    private int dy[];
    private ArrayList<int[]> lastMovePath= new ArrayList<>();
    
    public Piece(char pieceSign) {
        this.pieceSign = pieceSign;
    }
    
    public abstract boolean pieceVerifyMove(Board board, int[]from,int[]to);
    public void addMovePath(int coord[]){
        lastMovePath.add(coord);
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

    public ArrayList<int[]> getLastMovePath() {
        return lastMovePath;
    }

    public void setLastMovePath(ArrayList<int[]> lastMovePath) {
        this.lastMovePath = lastMovePath;
    }
    
}
