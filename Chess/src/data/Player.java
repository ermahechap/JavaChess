package data;

import java.util.ArrayList;

public class Player {
    
    private String name;
    private boolean colour;// if true, white
    private int wins;
    private int looses;
    private static int tie;
    private ArrayList<Piece> pieces;
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
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
    
    public static int getTie() {
        return tie;
    }

    public static void setTie(int aTie) {
        tie = aTie;
    }
    
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
    
}
