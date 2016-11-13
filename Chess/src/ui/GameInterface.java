package ui;

import data.*;
import data.Piece;
import data.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GameInterface extends javax.swing.JFrame {
    public GameInterface() {
        try {
            initComponents();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
            ImageIcon icon= new ImageIcon("/resources/gameIcon.png");
            this.setIconImage(icon.getImage());
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaHistory = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        Quit = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabelPlayerColor = new javax.swing.JLabel();
        jLabelPlayerName = new javax.swing.JLabel();
        jLabelCemeteryBlack = new javax.swing.JLabel();
        jLabelCemeteryWhite = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Jugadas"));

        jTextAreaHistory.setEditable(false);
        jTextAreaHistory.setColumns(20);
        jTextAreaHistory.setRows(5);
        jScrollPane1.setViewportView(jTextAreaHistory);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 13, -1, -1));

        Quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/error.png"))); // NOI18N
        Quit.setText("Retirarse");

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit.png"))); // NOI18N
        jButtonExit.setText("Salir");

        jLabelPlayerColor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabelPlayerColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPlayerColor.setText("White");
        jLabelPlayerColor.setAlignmentX(0.5F);

        jLabelPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPlayerName.setText("Player Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Quit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPlayerColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(jLabelPlayerName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPlayerColor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPlayerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Quit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 343, 265, -1));

        jLabelCemeteryBlack.setFont(new java.awt.Font("SansSerif", 0, 36)); // NOI18N
        jLabelCemeteryBlack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCemeteryBlack.setText("♟♟♟♟♟♟♟♟♞♞♝♝♜♜♛");
        jLabelCemeteryBlack.setToolTipText("");
        getContentPane().add(jLabelCemeteryBlack, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 586, -1));

        javax.swing.GroupLayout jPanelBoardContainerLayout = new javax.swing.GroupLayout(jPanelBoardContainer);
        jPanelBoardContainer.setLayout(jPanelBoardContainerLayout);
        jPanelBoardContainerLayout.setHorizontalGroup(
            jPanelBoardContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );
        jPanelBoardContainerLayout.setVerticalGroup(
            jPanelBoardContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelBoardContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 67, -1, 453));

        jLabelCemeteryWhite.setFont(new java.awt.Font("SansSerif", 0, 36)); // NOI18N
        jLabelCemeteryWhite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCemeteryWhite.setText("♙♙♙♙♙♙♙♙♘♘♗♗♖♖♕");
        getContentPane().add(jLabelCemeteryWhite, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 527, 566, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Quit;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JLabel jLabelCemeteryBlack;
    private javax.swing.JLabel jLabelCemeteryWhite;
    private javax.swing.JLabel jLabelPlayerColor;
    private javax.swing.JLabel jLabelPlayerName;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private ui.ChessBoard jPanelBoardContainer= new ui.ChessBoard();
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaHistory;
    // End of variables declaration//GEN-END:variables
    private String pieceSwap(char piece){
        String str=new String();
        
        char piecesLowerCase[]={'p','k','q','b','n','r'};
        char swapLowerCase[]={'♙','♔','♕','♗','♘','♖'};
        char swapUpperCase[]={'♟','♚','♛','♝','♞','♜'};
        if(Character.isLowerCase(piece)){
            for (int i = 0; i < 6; i++) {
                if(piece==piecesLowerCase[i])str+=swapLowerCase[i];
            }
        }else{
            char lowercased=Character.toLowerCase(piece);
            for (int i = 0; i < 6; i++) {
                if(lowercased==piecesLowerCase[i])str+=swapUpperCase[i];
            }
        }
        return str;
    }
    
    public void updateCemetery(Player w, Player b){
        ArrayList<Piece> wCemetery = w.getCemetery();
        ArrayList<Piece> bCemetery = b.getCemetery();
        String str=new String();
        for (int i = 0; i < wCemetery.size(); i++) {
            str+=pieceSwap(wCemetery.get(i).getPieceSign());
        }
        jLabelCemeteryWhite.setText(str);
        str="";
        for (int i = 0; i < bCemetery.size(); i++) {
            str+=pieceSwap(bCemetery.get(i).getPieceSign());
        }
        jLabelCemeteryBlack.setText(str);
    }

    public void updatePiecesPosition(Board board) {
        jPanelBoardContainer.setCurrrentBoard(board);
    }
    
}
