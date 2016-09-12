package businessLogic;

import data.*;
import ui.UI;

public class Chess {

    public static void main(String[] args) {
        UI.welcome();
        gameLoop();
    }

    public static void gameLoop(){
        Player playerWhite = new Player(UI.readName("Blancas"), true);
        Player playerBlack  = new Player(UI.readName("Negras"), false);
        Board board = new Board(playerWhite, playerBlack);
        UI.printBoard(board);
        
        System.out.println("No se ha implementado el movimiento, pero ya esta definido lo necesario\n"
                + "para una partida");
    }
}
