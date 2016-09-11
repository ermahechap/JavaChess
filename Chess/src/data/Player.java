package data;

import java.util.ArrayList;

public class Player {

    public static int getTie() {
        return tie;
    }

    public static void setTie(int aTie) {
        tie = aTie;
    }
    
    private String name;
    private boolean color;// if true, white
    private int wins;
    private int looses;
    private static int tie;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> cemetery;

    public Player(String name, boolean color) {
        this.name = name;
        this.color = color;
        
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLooses() {
        return looses;
    }

    public void setLooses(int looses) {
        this.looses = looses;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public ArrayList<Piece> getCemetery() {
        return cemetery;
    }

    public void setCemetery(ArrayList<Piece> cemetery) {
        this.cemetery = cemetery;
    }
    
}
