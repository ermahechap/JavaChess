package data;

import businessLogic.Functional;
import java.util.ArrayList;

public class Player {
    private String name;
    private boolean color;// if true, white
    private ArrayList<Piece> pieces=new ArrayList<>();
    private ArrayList<Piece> cemetery= new ArrayList<>();
    private ArrayList<String>history= new ArrayList<>();
    
    public Player(String name, boolean color) {
        this.name = name;
        this.color = color;
        createPieces();
    }
    
    private void createPieces(){
        //create pieces
        for(int i=0;i<8;i++)pieces.add(new Pawn(color));
        for(int i=0;i<2;i++)pieces.add(new Rook(color));
        for(int i=0;i<2;i++)pieces.add(new Knight(color));
        for(int i=0;i<2;i++)pieces.add(new Bishop(color));
        pieces.add(new King(color));
        pieces.add(new Queen(color));
    }
    
    public void addToHistory(int from[], int to[],Piece pieceFrom, Piece pieceTo, Piece promotionPiece){
        String toAdd= new String();
        if(pieceFrom.getPieceSign()!='p' && pieceFrom.getPieceSign()!='P')
            toAdd+=Character.toUpperCase(pieceFrom.getPieceSign());
        
        toAdd+=Functional.toCharCoordinate(from[1]);
        toAdd+=Integer.toString(8-from[0]);
        
        if(pieceTo==null)toAdd+="-";
        else toAdd+="x";
        
        toAdd+=Functional.toCharCoordinate(to[1]);
        toAdd+=Integer.toString(8-to[0]);
        
        if(promotionPiece!=null){
            toAdd+="=" + Character.toUpperCase(promotionPiece.getPieceSign());
        }
        history.add(toAdd);
    }
    
    public void addToHistory(String str){
        history.add(str);
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

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    
    
    
}
