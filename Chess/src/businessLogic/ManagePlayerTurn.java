package businessLogic;
import data.Piece;
import java.util.ArrayList;

public class ManagePlayerTurn {
    private static byte turn=0;
    
    public static void changeTurn(){
        if(turn==1)turn=0;//switch turn
        else turn=1;
    }

    public static byte getTurn() {
        return turn;
    }

    public static void setTurn(byte aTurn) {
        turn = aTurn;
    }
}
