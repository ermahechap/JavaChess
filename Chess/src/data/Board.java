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
    
    public Board(Player player){
        this();
    }
    
    
}
