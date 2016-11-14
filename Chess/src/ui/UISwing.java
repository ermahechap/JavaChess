package ui;

import data.Bishop;
import data.Board;
import data.Knight;
import data.Piece;
import data.Player;
import data.Queen;
import data.Rook;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UISwing extends javax.swing.JFrame implements UI {

    private boolean menuBtnPressed = false;
    private int menuSelection = 1;

    private static String messages[] = new String[]{"Opcion no listada", "No hay pieza en la posición inicial"
            , "Se espera valor numerico", "Ingrese una coordenada valida, es decir letra y número"
            , "Coordenedas Incorrectas o fuera del limite", "La dirección es incorrecta, debe ingresar solo la dirección de LA CARPETA"
            , "Coordenadas iguales", "Movimiento no valido"};

    private GameInterface game;

    public UISwing() {
        try {
            initComponents();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
            ImageIcon icon = new ImageIcon("/resources/gameIcon.png");
            this.setIconImage(icon.getImage());
            this.pack();
            this.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UISwing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UISwing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UISwing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UISwing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonNewGame = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaChess");
        setResizable(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Title.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jButtonNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/horse.png"))); // NOI18N
        jButtonNewGame.setText("Nuevo Juego");
        jButtonNewGame.setAlignmentX(0.5F);
        jButtonNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewGameActionPerformed(evt);
            }
        });

        jButtonLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/folder.png"))); // NOI18N
        jButtonLoad.setText("Cargar Juego");
        jButtonLoad.setAlignmentX(0.5F);
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit.png"))); // NOI18N
        jButtonExit.setText("Salir");
        jButtonExit.setAlignmentX(0.5F);
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNewGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLoad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewGameActionPerformed
        menuBtnPressed = true;
        menuSelection = 1;
    }//GEN-LAST:event_jButtonNewGameActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        menuBtnPressed = true;
        menuSelection = 2;
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.setVisible(false);
        this.dispose();
        menuBtnPressed = true;
        menuSelection = 3;
    }//GEN-LAST:event_jButtonExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNewGame;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onWinMessage(Player player) {
        JOptionPane.showMessageDialog(game, "El jugador " + player.getName()
                + " ha ganado esta partida", "Win!!", JOptionPane.INFORMATION_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void onTieMessage(Player[] player) {
        JOptionPane.showMessageDialog(game, "Los jugadores " + player[0].getName() + "y"
                + player[1].getName() + " han empatado", "Tie!!", JOptionPane.INFORMATION_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void messageStalemate() {
        JOptionPane.showMessageDialog(game, "Empate por rey ahogado", "Stalemate!!", JOptionPane.INFORMATION_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void onInvalidMoveCheck(Player[] player, int turn) {
        JOptionPane.showMessageDialog(game, "El movimiento lo pone en jaque"
                + "\nIntente otra vez", "Invalid Move!!", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void onCheck(Player[] player, int turn) {
        game.repaint();
        JOptionPane.showMessageDialog(game, "El jugador " + player[turn].getName() + " esta en jaque", "Check!!", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void checkMate(Player[] player, int turn) {
        JOptionPane.showMessageDialog(game, "El jugador " + player[turn].getName() + " ha perdido", "Check Mate!!", JOptionPane.WARNING_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void onQuitGame(Player player) {
        JOptionPane.showMessageDialog(game, "El jugador " + player.getName() + " se ha retirado del juego", "Quit!!", JOptionPane.WARNING_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void onError(int msg) {
        String ms = new String();
        if (msg < 0 || msg >= messages.length) {
            ms = "No hay mesaje en este índice";
        } else {
            ms = messages[msg];
        }
        ms += "\nIntente otra vez";
        JOptionPane.showMessageDialog(game, ms, "Error!!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void messageDrawFifty(Player[] player) {
        JOptionPane.showMessageDialog(game, "Empate por 50 movimientos sin comer o avanzar peones", "Draw 50!!", JOptionPane.INFORMATION_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void messageDrawKing(Player[] player) {
        JOptionPane.showMessageDialog(game, "Empate por ahogar al rey"
                + "\nJudador " + player[0].getName() + " ha empatado con " + player[1].getName(), "Tie!!", JOptionPane.INFORMATION_MESSAGE);
        game.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void printBoard(Board board) {
        //nothing to do here :vvvv
    }

    @Override
    public String readName(String col) {
        //neither here :vvv
        return "blablabla";// :v
    }

    @Override
    public void welcome() {
        //or here :vvvv, so cool
    }

    @Override
    public int menu() {
        do {
            pause();
        } while (!menuBtnPressed);
        menuBtnPressed = false;
        return menuSelection;
    }

    @Override
    public void printCemetery(Player w, Player b) {
        game.updateCemetery(w, b);
    }

    @Override
    public void whosePlayer(Player player) {
        String name = player.getName();
        String playerColor = (player.isColor()) ? "White" : "Black";
        game.setCurrentPlayer(name, playerColor);
    }

    @Override
    public String coordinateRead() {
        //i dont this
        return "blabla";
    }

    @Override
    public ArrayList<ArrayList<Integer>> inputMove() {
        return ChessBoard.coordMoved;
    }

    @Override
    public Piece askPromotioPiece(boolean color) {
        Piece returnPiece;
        String opt;
        
        ArrayList<Piece> values=new ArrayList<>(Arrays.asList(new Queen(color),
            new Knight(color),new Rook(color),new Bishop(color)));
        String[] buttons = { "Queen", "Knight", "Rook", "Bishop" };
        
        int out = JOptionPane.showOptionDialog(game, "Seleccione la por la que desea hacer el cambio","Change",
        JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[2]);
        
        returnPiece= values.get(out);
        return returnPiece;
    }

    @Override
    public int movementOptions() {
        boolean moved = false, quit = false, save = false;
        int opt = 1;
        do {
            save = game.isSavePressed();
            quit = game.isQuitPressed();
            moved = game.isPieceMoved();
            pause();
        } while (!save && !quit && !moved);
        if (quit)opt = 4;
        if (moved)opt = 1;
        if (save)opt = 3;
        game.repaint();
        return opt;
    }

    @Override
    public void showPlayHist(Player[] player) {
        game.updateHistory(player);
    }

    @Override
    public boolean overWriteMessage(String fileName) {
        //do not need this one
        return true;
    }

    @Override
    public String saveGameRequest() {
        JFileChooser fileChoser = new JFileChooser(new File(System.getProperty("user.home")));
        fileChoser.addChoosableFileFilter(new FileNameExtensionFilter("File Chess", "chess"));
        fileChoser.addChoosableFileFilter(new FileNameExtensionFilter("File Object", "obj"));
        fileChoser.setAcceptAllFileFilterUsed(true);
        
        fileChoser.setMultiSelectionEnabled(false);
        fileChoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fileChoser.showSaveDialog(game);
        if (res == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChoser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            if(!fileChoser.getFileFilter().accept(selectedFile)){
                path+=(fileChoser.getFileFilter().getDescription().equals("File Chess"))?".chess":".obj";
            }
            return path;
        } else {
            return "";
        }
    }

    @Override
    public String loadGameRequest() {
        JFileChooser fileChoser = new JFileChooser(new File(System.getProperty("user.home")));
        fileChoser.setMultiSelectionEnabled(false);
        fileChoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fileChoser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            String path = fileChoser.getSelectedFile().getAbsolutePath();
            this.setVisible(false);
            return path;
        } else {
            this.setVisible(false);
            return "";
        }
        
    }

    @Override
    public void onSaveSuceed() {
        JOptionPane.showMessageDialog(game, "Se ha guardado la partida Exitosamente", "Save Success!!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onSaveFailure() {
        JOptionPane.showMessageDialog(game, "No se ha guardado la partida", "Save Failure!!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onLoadFailure() {
        JOptionPane.showMessageDialog(game, "No se puede cargar la partida, nueva partida iniciada", "Load Failure!!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onLoadSuceed() {
        JOptionPane.showMessageDialog(game, "Carga realizada correctamente", "Load Success!!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Player[] readPlayers() {
        this.setVisible(false);
        PlayerRegisterMenu register = new PlayerRegisterMenu();
        register.setLocationRelativeTo(this);
        do {
            pause();
        } while (!register.isBtnPressed());
        Player player[] = {new Player(register.getPlayerWhite(), true), new Player(register.getPlayerBlack(), false)};
        register.setVisible(false);
        register = null;
        return player;
    }

    @Override
    public void createBoardInterface() {
        game = new GameInterface();
        game.setVisible(true);
    }

    public void pause() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(UISwing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
