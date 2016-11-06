package ui;

import data.Board;
import data.Piece;
import data.Player;
import java.util.ArrayList;

public interface UI {
    public void onWinMessage(Player player);
    public void onTieMessage(Player[] player);
    public void messageStalemate();
    public void onInvalidMoveCheck(Player[] player, int turn);
    public void onCheck(Player[] player, int turn);
    public void checkMate(Player[] player, int turn);
    public void onQuitGame(Player player);
    public void onError(int msg);
    public void messageDrawFifty(Player[] player);
    public void messageDrawKing(Player[] player);
    public void printBoard(Board board);
    public String readName(String col);
    public void welcome();
    public int menu();
    public void printCemetery(Player w, Player b);
    public void whosePlayer(Player player);
    public String coordinateRead();
    public ArrayList<ArrayList<Integer>> inputMove();
    public Piece askPromotioPiece(boolean color);
    public int movementOptions();
    public void showPlayHist(Player[] player);
    public boolean overWriteMessage(String fileName);
    public String saveGameRequest();
    public String loadGameRequest();
    public void onSaveSuceed();
    public void onSaveFailure();
    public void onLoadFailure();
    public void onLoadSuceed();

    public Player[] readPlayers();
}
