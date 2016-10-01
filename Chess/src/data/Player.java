package data;

import java.util.ArrayList;

public class Player {
    private String name;
    private boolean color;// if true, white
    private int wins=0;
    private int looses=0;
    private static int tie=0;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> cemetery;
    
    public Player(String name, boolean color) {
        this.name = name;
        this.color = color;
        this.cemetery= new ArrayList<>();
        createPieces();
    }
    
    private void createPieces(){
        pieces = new ArrayList<>();
        //create pieces
        for(int i=0;i<8;i++)pieces.add(new Pawn(color));
        for(int i=0;i<2;i++)pieces.add(new Rook(color));
        for(int i=0;i<2;i++)pieces.add(new Knight(color));
        for(int i=0;i<2;i++)pieces.add(new Bishop(color));
        pieces.add(new King(color));
        pieces.add(new Queen(color));
    }
    
    public static int getTie() {
        return tie;
    }

    public static void setTie(int aTie) {
        tie = aTie;
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
    
    public void addPieceCemetery(Piece cPiece){
        this.cemetery.add(cPiece);
    }
}
