package checkers;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainScreen.java
 *
 * Created on May 5, 2012, 4:54:18 PM
 */

/**
 *
 * @author Laura
 */
public class MainScreen extends javax.swing.JFrame {

	private GameUI game;
	
    /** Creates new form MainScreen */

    public MainScreen() {
        initComponents();
        setLocationRelativeTo(null);
        userChoice = -1;
        setSize(WIN_WIDTH, WIN_HEIGHT);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newgame_menu = new javax.swing.JPopupMenu();
        pvp = new javax.swing.JMenuItem();
        pvc = new javax.swing.JMenuItem();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        viewstats_button = new javax.swing.JButton();
        newgame_button = new javax.swing.JButton();
        loadgame_button = new javax.swing.JButton();
        flyingmoose_lbl = new javax.swing.JLabel();
        checkers_bg = new javax.swing.JLabel();

        newgame_menu.setBackground(null);
        newgame_menu.setBorder(null);

        pvp.setBackground(null);
        pvp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/pvp_ico.png"))); // NOI18N
        pvp.setBorder(null);
        pvp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pvp.setIconTextGap(0);
        pvp.setPreferredSize(new java.awt.Dimension(150, 25));
        pvp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pvpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pvpMouseExited(evt);
            }
        });
        pvp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvpActionPerformed(evt);
            }
        });
        newgame_menu.add(pvp);
        pvp.getAccessibleContext().setAccessibleName("pVp");

        pvc.setBackground(null);
        pvc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/pvc_ico.png"))); // NOI18N
        pvc.setBorder(null);
        pvc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pvc.setIconTextGap(0);
        pvc.setPreferredSize(new java.awt.Dimension(150, 25));
        pvc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvcMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pvcMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pvcMouseExited(evt);
            }
        });
        pvc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvcActionPerformed(evt);
            }
        });
        newgame_menu.add(pvc);
        pvc.getAccessibleContext().setAccessibleName("pVc");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flying Moose: Checkers");
        setIconImages(null);
        setName("mainscreen"); // NOI18N
        setResizable(false);

        viewstats_button.setBackground(null);
        viewstats_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/viewstats_ico.png"))); // NOI18N
        viewstats_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewstats_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewstats_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewstats_buttonMouseExited(evt);
            }
        });
        viewstats_button.setBounds(40, 340, 150, 50);
        jLayeredPane1.add(viewstats_button, javax.swing.JLayeredPane.DEFAULT_LAYER);

        newgame_button.setBackground(null);
        newgame_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/newgame_ico.png"))); // NOI18N
        newgame_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newgame_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newgame_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newgame_buttonMouseExited(evt);
            }
        });
        newgame_button.setBounds(40, 220, 150, 50);
        jLayeredPane1.add(newgame_button, javax.swing.JLayeredPane.DEFAULT_LAYER);

        loadgame_button.setBackground(null);
        loadgame_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/loadgame_ico.png"))); // NOI18N
        loadgame_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadgame_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loadgame_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loadgame_buttonMouseExited(evt);
            }
        });
        loadgame_button.setBounds(40, 280, 150, 50);
        jLayeredPane1.add(loadgame_button, javax.swing.JLayeredPane.DEFAULT_LAYER);

        flyingmoose_lbl.setFont(new java.awt.Font("Tahoma", 0, 48));
        flyingmoose_lbl.setForeground(new java.awt.Color(255, 255, 255));
        flyingmoose_lbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        flyingmoose_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/flyingmoose_1.png"))); // NOI18N
        flyingmoose_lbl.setBounds(90, -20, 580, 220);
        jLayeredPane1.add(flyingmoose_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        checkers_bg.setBackground(new java.awt.Color(0, 0, 0));
        checkers_bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkers_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/checkers_bg.png"))); // NOI18N
        checkers_bg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        checkers_bg.setAlignmentY(0.0F);
        checkers_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        checkers_bg.setInheritsPopupMenu(false);
        checkers_bg.setBounds(0, -10, 740, 550);
        jLayeredPane1.add(checkers_bg, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newgame_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newgame_buttonMouseEntered
        // TODO add your handling code here:
        newgame_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/newgame_ico_click.png")));
        newgame_menu.show(newgame_button, newgame_button.EAST + newgame_button.getWidth(), newgame_button.NORTH);

    }//GEN-LAST:event_newgame_buttonMouseEntered

    private void newgame_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newgame_buttonMouseClicked
        // TODO add your handling code here:
       newgame_buttonMouseEntered(evt);
    }//GEN-LAST:event_newgame_buttonMouseClicked

    private void pvpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvpActionPerformed
        // TODO add your handling code here:
    	game = new GameUI(this);
    	setVisible(false);
    }//GEN-LAST:event_pvpActionPerformed

    private void pvcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pvcActionPerformed

    private void newgame_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newgame_buttonMouseExited
        // TODO add your handling code here:
         newgame_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/newgame_ico.png")));
    }//GEN-LAST:event_newgame_buttonMouseExited

    private void loadgame_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadgame_buttonMouseEntered
        // TODO add your handling code here:
        loadgame_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/loadgame_ico_click.png")));
    }//GEN-LAST:event_loadgame_buttonMouseEntered

    private void loadgame_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadgame_buttonMouseExited
        // TODO add your handling code here:
        loadgame_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/loadgame_ico.png")));
    }//GEN-LAST:event_loadgame_buttonMouseExited

    private void viewstats_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewstats_buttonMouseEntered
        // TODO add your handling code here:
         viewstats_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/viewstats_ico_click.png")));
    }//GEN-LAST:event_viewstats_buttonMouseEntered

    private void viewstats_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewstats_buttonMouseExited
        // TODO add your handling code here:
         viewstats_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/viewstats_ico.png")));
    }//GEN-LAST:event_viewstats_buttonMouseExited

    private void pvpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvpMouseExited
        // TODO add your handling code here:
         pvp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/pvp_ico.png")));
    }//GEN-LAST:event_pvpMouseExited

    private void pvpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvpMouseEntered
        // TODO add your handling code here:
         pvp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/pvp_ico_click.png")));
    }//GEN-LAST:event_pvpMouseEntered

    private void pvcMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvcMouseEntered
        // TODO add your handling code here:
        pvc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/pvc_ico_click.png")));
    }//GEN-LAST:event_pvcMouseEntered

    private void pvcMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvcMouseExited
        // TODO add your handling code here:
         pvc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/pvc_ico.png")));
    }//GEN-LAST:event_pvcMouseExited

    private void loadgame_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadgame_buttonMouseClicked
        // TODO add your handling code here:
        userChoice = LOAD;
    }//GEN-LAST:event_loadgame_buttonMouseClicked

    private void pvpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvpMouseClicked
        // TODO add your handling code here:
        userChoice = NEW_PVP;
    }//GEN-LAST:event_pvpMouseClicked

    private void pvcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvcMouseClicked
        // TODO add your handling code here:
        userChoice = NEW_PVC;
    }//GEN-LAST:event_pvcMouseClicked

    private void viewstats_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewstats_buttonMouseClicked
        // TODO add your handling code here:
        StatsWindow stats = new StatsWindow();
        stats.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        stats.setVisible(true);

    }//GEN-LAST:event_viewstats_buttonMouseClicked

    public int getUserChoice()
    {
        return userChoice;
    }

    // make sure getUserChoice() was called first!
    public void closeMainScreen()
    {
        this.dispose();
    }
   
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
                
            }
        });
    }


    private final int WIN_HEIGHT = 570;
    private final int WIN_WIDTH = 740;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel checkers_bg;
    private javax.swing.JLabel flyingmoose_lbl;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton loadgame_button;
    private javax.swing.JButton newgame_button;
    private javax.swing.JPopupMenu newgame_menu;
    private javax.swing.JMenuItem pvc;
    private javax.swing.JMenuItem pvp;
    private javax.swing.JButton viewstats_button;
    // End of variables declaration//GEN-END:variables

    private int userChoice;
    private final int NEW_PVP = 0;
    private final int NEW_PVC = 1;
    private final int LOAD = 2;

}
