package businessLogic;
import data.Piece;
import java.util.ArrayList;

public class ManagePlayerTurn {
    private static int turn=0;
    
    public static void changeTurn(){
        if(turn==1)turn=0;//switch turn
        else turn=1;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int aTurn) {
        turn = aTurn;
    }
}
