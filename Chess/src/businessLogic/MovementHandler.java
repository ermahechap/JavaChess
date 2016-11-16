package businessLogic;

import java.util.ArrayList;
import data.Board;
import data.Piece;
import data.Player;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.UI;
import ui.UIText;
import ui.UISwing;

public class MovementHandler {

    private static int countRep = 0;
    private static int pieceCheckCoord[] = {-1, -1};
    private static UI userUI = Chess.userUI;

    protected static int[] getKingXY(Board board, Player[] player, int whichPlayer) {
        
        Board copyBoard=(Board) Functional.deepCopy(board);
        int kingpos[] = new int[2];
        char cmp;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyBoard.getGameBoard()[i][j].getPiece() == null) {
                    continue;
                }
                if (copyBoard.getGameBoard()[i][j].getPiece().getClass().toString().equals("class data.King")) {
                    cmp = copyBoard.getGameBoard()[i][j].getPiece().getPieceSign();
                    int pos = (cmp == 'k') ? 0 : 1;
                    if (pos == whichPlayer) {
                        kingpos[0] = i;
                        kingpos[1] = j;
                        break;
                    }
                }
            }
        }
        return kingpos;
    }

    protected static boolean isFromEmpty(Board board, int from[]) {
        return board.getGameBoard()[from[0]][from[1]].getPiece() == null;
    }

    protected static boolean isValidMove(Board board, int from[], int to[], int whichPlayer) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Piece piece = copyBoard.getGameBoard()[from[0]][from[1]].getPiece();
        if (piece == null) {
            return false;
        }
        if (Character.isLowerCase(piece.getPieceSign()) && whichPlayer == 0) {
            return piece.pieceVerifyMove(copyBoard, from, to);
        }
        if (Character.isUpperCase(piece.getPieceSign()) && whichPlayer == 1) {
            return piece.pieceVerifyMove(copyBoard, from, to);
        }
        return false;
    }

    protected static boolean isValidMove(Board board, ArrayList<ArrayList<Integer>> moveData, int whichPlayer) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        
        int from[] = Functional.splitDataPair(moveData.get(0));//row,col
        int to[] = Functional.splitDataPair(moveData.get(1));//row,col
        return isValidMove(copyBoard, from, to, whichPlayer);
    }

    static boolean isKingFrom(Board board, ArrayList<ArrayList<Integer>> moveData) {
        int from[] = Functional.splitDataPair(moveData.get(0));//row,col
        if (board.getGameBoard()[from[0]][from[1]].getPiece() == null) {
            return false;
        }
        return (board.getGameBoard()[from[0]][from[1]].getPiece().getClass().toString().equals("class data.King"));
    }

    static boolean isRookTo(Board board, ArrayList<ArrayList<Integer>> moveData) {
        int to[] = Functional.splitDataPair(moveData.get(1));//row,col
        if (board.getGameBoard()[to[0]][to[1]].getPiece() == null) {
            return false;
        }
        return (board.getGameBoard()[to[0]][to[1]].getPiece().getClass().toString().equals("class data.Rook"));
    }

    static boolean canCastle(Board board, ArrayList<ArrayList<Integer>> moveData, int whichPlayer) {
        int from[] = Functional.splitDataPair(moveData.get(0));//row,col
        int to[] = Functional.splitDataPair(moveData.get(1));//row,col
        if (!(MovementHandler.isKingFrom(board, moveData) && MovementHandler.isRookTo(board, moveData))) {
            return false;
        }
        Piece king = board.getGameBoard()[from[0]][from[1]].getPiece();
        Piece rook = board.getGameBoard()[to[0]][from[0]].getPiece();

        if (king.isMoved() || rook.isMoved()) {
            return false;
        }

        if (from[1] > to[1]) {
            for (int i = from[1] - 1; i > to[1] + 1; i--) {
                if (board.getGameBoard()[from[0]][i].getPiece() != null) {
                    return false;
                }
            }
        } else {
            for (int i = from[1] + 1; i < to[1] - 1; i++) {
                if (board.getGameBoard()[from[0]][i].getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    // just coded 50move repetition, accepted as chess variation with only this rule and stalemate
    protected static boolean drawFifty() {
        //repetition rule
        return countRep >= 50;
    }

    protected static boolean isCheck(Board board, Player[] player, int whichPlayer) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        
        int kingpos[] = getKingXY(copyBoard, copyPlayer, whichPlayer);
        if (whichPlayer == 0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (copyBoard.getGameBoard()[i][j].getPiece() == null) {
                        continue;
                    }
                    if (i == kingpos[0] && j == kingpos[1]) {
                        continue;
                    }
                    if (Character.isUpperCase(copyBoard.getGameBoard()[i][j].getPiece().getPieceSign())) {//if turn 0(white), need to compare with black pieces(uppercase)
                        if (copyBoard.getGameBoard()[i][j].getPiece().pieceVerifyMove(copyBoard, new int[]{i, j}, kingpos)) {
                            pieceCheckCoord[0] = i;
                            pieceCheckCoord[1] = j;
                            return true;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (copyBoard.getGameBoard()[i][j].getPiece() == null) {
                        continue;
                    }
                    if (i == kingpos[0] && j == kingpos[1]) {
                        continue;
                    }
                    if (Character.isLowerCase(copyBoard.getGameBoard()[i][j].getPiece().getPieceSign())) {//if turn 1(black), need to compare with white pieces(lowercase)
                        if (copyBoard.getGameBoard()[i][j].getPiece().pieceVerifyMove(copyBoard, new int[]{i, j}, kingpos)) {
                            pieceCheckCoord[0] = i;
                            pieceCheckCoord[1] = j;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    protected static boolean isCheckRemovable(Board board, Player[] player, int whichPlayer) {
        //capture piece, block piece - OK
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        ArrayList<int[]> pathSearch = copyBoard.getGameBoard()[pieceCheckCoord[0]][pieceCheckCoord[1]].getPiece().getLastMovePath();
        int kingpos[] = getKingXY(copyBoard, copyPlayer, whichPlayer);
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i==kingpos[0] && j==kingpos[1]){
                    continue;
                }
                if (copyBoard.getGameBoard()[i][j].getPiece() == null) {
                    continue;
                }
                
                Piece prov=copyBoard.getGameBoard()[i][j].getPiece();
                if(Character.isLowerCase(prov.getPieceSign()) && whichPlayer==1)continue;
                if(Character.isUpperCase(prov.getPieceSign()) && whichPlayer==0)continue;
                int coord[] = {i, j};
                for (int k = 0; k < pathSearch.size(); k++) {
                    if (isValidMove(copyBoard, coord, pathSearch.get(k), whichPlayer)) {
                        Object st[] = performMove(copyBoard, copyPlayer, coord, pathSearch.get(k));//from ij to the piece is making check
                        Board provisionalBoard = (Board) st[0];
                        if (!isCheck(provisionalBoard, copyPlayer, whichPlayer)) {
                            return true;
                        }
                    }
                }
            }
        }

        //move king
        int mx[] = {-1, 0, 1, 0, -1, 1, 1, -1};//rows
        int my[] = {0, 1, 0, -1, 1, 1, -1, -1};//cols
        for (int i = 0; i < 8; i++) {
            int advance[] = {kingpos[0] + mx[i], kingpos[1] + my[i]};
            if (kingpos[0] + mx[i] < 0 || kingpos[0] + mx[i] > 7) {
                continue;
            }
            if (kingpos[1] + my[i] < 0 || kingpos[1] + my[i] > 7) {
                continue;
            }
            if (isValidMove(copyBoard, kingpos, advance, whichPlayer)) {
                Object st[] = performMove(copyBoard, copyPlayer, kingpos, advance);
                Board provisionalBoard = (Board) st[0];
                if (!isCheck(provisionalBoard, copyPlayer, whichPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static boolean isKingStalemate(Board board, Player[] player, int whichPlayer) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        //move king
        int mx[] = {-1, 0, 1, 0, -1, 1, 1, -1};//rows
        int my[] = {0, 1, 0, -1, 1, 1, -1, -1};//cols
        boolean stalemate = true;
        int kingpos[] = getKingXY(copyBoard, copyPlayer, whichPlayer);
        for (int i = 0; i < 8; i++) {
            int advance[] = {kingpos[0] + mx[i], kingpos[1] + my[i]};
            if (kingpos[0] + mx[i] < 0 || kingpos[0] + mx[i] > 7) {
                continue;
            }
            if (kingpos[1] + my[i] < 0 || kingpos[1] + my[i] > 7) {
                continue;
            }
            if (isValidMove(copyBoard, kingpos, advance, (whichPlayer + 1) % 2)) {
                Object st[] = performMove(copyBoard, copyPlayer, kingpos, advance);
                Board provisionalBoard = (Board) st[0];
                if (!isCheck(provisionalBoard, copyPlayer, whichPlayer)) {
                    stalemate = stalemate && false;
                }
            }
        }

        if (stalemate) { //checks if there is stalemate
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (copyBoard.getGameBoard()[i][j].getPiece() == null) {
                        continue;
                    }
                    if (Character.isLowerCase(copyBoard.getGameBoard()[i][j].getPiece().getPieceSign()) && whichPlayer == 0) {
                        if (pieceNotLocked(copyBoard, new int[]{i, j}, copyPlayer, whichPlayer)) {
                            return false;
                        }
                    } else if (Character.isUpperCase(copyBoard.getGameBoard()[i][j].getPiece().getPieceSign()) && whichPlayer == 1) {
                        if (pieceNotLocked(copyBoard, new int[]{i, j}, copyPlayer, whichPlayer)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    protected static boolean pieceNotLocked(Board board, int from[], Player[] player, int whichPlayer) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(copyBoard, from, new int[]{i, j}, whichPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static Object[] performMove(Board board, Player[] player, int from[], int to[]) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        //need to return object and players

        if (copyBoard.getGameBoard()[to[0]][to[1]].getPiece() != null) {// to add piece to cemetery
            Piece toDelete = copyBoard.getGameBoard()[to[0]][to[1]].getPiece();
            toDelete.setMoved(false);//in order to reboot it to initial state and perform .remove later
            if (Character.isLowerCase(toDelete.getPieceSign())) {
                copyPlayer[0].addPieceCemetery(toDelete);
                copyPlayer[0].getPieces().remove(toDelete);// only removes first occurrence of the piece
            } else {
                copyPlayer[1].addPieceCemetery(toDelete);
                copyPlayer[1].getPieces().remove(toDelete);// only removes first occurrence of the piece
            }
        }

        Piece toSet = copyBoard.getGameBoard()[from[0]][from[1]].getPiece();

        //PawnPromotion
        char cmp = toSet.getPieceSign();
        int pos = (cmp == 'p') ? 0 : (cmp == 'P') ? 7 : -1;
        int who = (Character.isLowerCase(cmp)) ? 0 : 1;

        if ((cmp == 'p' || cmp == 'P') && pos == to[0]) {
            toSet.setMoved(false);
            copyPlayer[who].getPieces().remove(toSet);

            toSet = userUI.askPromotioPiece((cmp == 'p') ? true : false);
            copyPlayer[who].getPieces().add(toSet);

            copyPlayer[who].addToHistory(from, to, copyBoard.getGameBoard()[from[0]][from[1]].getPiece(),
                    copyBoard.getGameBoard()[to[0]][to[1]].getPiece(), toSet);
        } else {
            // in case there is no promotion
            copyPlayer[who].addToHistory(from, to, copyBoard.getGameBoard()[from[0]][from[1]].getPiece(),
                    copyBoard.getGameBoard()[to[0]][to[1]].getPiece(), null);
        }

        if (cmp == 'p' || cmp == 'P' || copyBoard.getGameBoard()[to[0]][to[1]].getPiece() != null) {
            countRep = 0;//counter goes zero in case a pawn is moved or piece is captured
        } else {
            countRep++;
        }
        toSet.setMoved(true);
        copyBoard.getGameBoard()[to[0]][to[1]].setPiece(toSet);//moves piece
        copyBoard.getGameBoard()[from[0]][from[1]].setPiece(null);//clears square from
        Object dataReturn[] = {copyBoard, copyPlayer};

        return dataReturn;
    }

    protected static Object[] performMove(Board board, Player[] player, ArrayList<ArrayList<Integer>> moveData) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        //need to return object and players
        int from[] = Functional.splitDataPair(moveData.get(0));//row,col
        int to[] = Functional.splitDataPair(moveData.get(1));//row,col
        return performMove(copyBoard, copyPlayer, from, to);
    }

    protected static Object[] performCastling(Board board, Player[] player, ArrayList<ArrayList<Integer>> moveData) {
        Board copyBoard=(Board) Functional.deepCopy(board);
        Player[] copyPlayer= (Player[]) Functional.deepCopy(player);
        
        //need to return object and players
        int from[] = Functional.splitDataPair(moveData.get(0));//row,col
        int to[] = Functional.splitDataPair(moveData.get(1));//row,col

        Piece king = copyBoard.getGameBoard()[from[0]][from[1]].getPiece();
        Piece rook = copyBoard.getGameBoard()[to[0]][to[1]].getPiece();
        int who = (Character.isLowerCase(king.getPieceSign())) ? 0 : 1;

        copyBoard.getGameBoard()[from[0]][from[1]].setPiece(null);//clear unused pieces
        copyBoard.getGameBoard()[to[0]][to[1]].setPiece(null);

        king.setMoved(true);
        rook.setMoved(true);

        if (Math.abs(from[1] - to[1]) == 3) {
            //short castling
            copyBoard.getGameBoard()[from[0]][6].setPiece(king);
            copyBoard.getGameBoard()[to[0]][5].setPiece(rook);
            copyPlayer[who].addToHistory("0-0");
        } else {
            //long castling
            copyBoard.getGameBoard()[from[0]][2].setPiece(king);
            copyBoard.getGameBoard()[to[0]][3].setPiece(rook);
            copyPlayer[who].addToHistory("0-0-0");
        }

        Object dataReturn[] = {copyBoard, copyPlayer};
        countRep++;//counts as a fifty move rule
        return dataReturn;
    }

    public static int getCountRep() {
        return countRep;
    }

    public static void setCountRep(int aCountRep) {
        countRep = aCountRep;
    }

    public static int[] getPieceCheckCoord() {
        return pieceCheckCoord;
    }

    public static void setPieceCheckCoord(int[] aPieceCheckCoord) {
        pieceCheckCoord = aPieceCheckCoord;
    }

}
