/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HistorialPartidas.java
 *
 * Created on 12-12-2011, 12:18:38 PM
 */
package uno_danjohwen;

import javax.swing.DefaultListModel;

/**
 *
 * @author Wendy
 */
public class HistorialPartidas extends javax.swing.JFrame {
    private Historial hist=new Historial();
    private String[] historial;
    /** Creates new form HistorialPartidas */
    public HistorialPartidas() {
        initComponents();
        historial=hist.getHistorial();
        DefaultListModel modelo = new DefaultListModel();
        
        int x=0,cont=0;
        if(historial.length>10)
            x=10;
        else
            x=historial.length;
        
        for(int i=x-1;i>=0;i--){
            cont++;
            modelo.addElement(cont+". "+historial[i].toString());
        }
        ListaHistorial.setModel(modelo);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaHistorial = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        Atras = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(null);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        ListaHistorial.setName("ListaHistorial"); // NOI18N
        ListaHistorial.setOpaque(false);
        jScrollPane1.setViewportView(ListaHistorial);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 80, 380, 160);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(uno_danjohwen.UNO_DanJohWenApp.class).getContext().getResourceMap(HistorialPartidas.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(140, 30, 241, 29);

        Atras.setFont(resourceMap.getFont("Atras.font")); // NOI18N
        Atras.setText(resourceMap.getString("Atras.text")); // NOI18N
        Atras.setName("Atras"); // NOI18N
        Atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AtrasMouseClicked(evt);
            }
        });
        jPanel1.add(Atras);
        Atras.setBounds(60, 280, 65, 23);

        jLabel2.setIcon(resourceMap.getIcon("jLabel2.icon")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 535, 334);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void AtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtrasMouseClicked
// TODO add your handling code here:
    MenuJugadores MJ= new MenuJugadores();
    MJ.setVisible(true);
    this.dispose();
}//GEN-LAST:event_AtrasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HistorialPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistorialPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistorialPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistorialPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new HistorialPartidas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atras;
    private javax.swing.JList ListaHistorial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
