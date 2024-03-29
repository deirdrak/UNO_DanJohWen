/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaLogin.java
 *
 * Created on 11-06-2011, 01:48:15 AM
 */
package uno_danjohwen;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Wendy
 */
public class PantallaLogin extends javax.swing.JFrame {
    protected int Jugador=1;
    protected Jugadores[] jugadores=new Jugadores[2];
    private Jugadores j= new Jugadores();
    DefaultListModel modelo = null;
    private int pantalla;
    
    public PantallaLogin(){
      
    }
    /** Creates new form PantallaLogin */
    public PantallaLogin(int pantalla) {
        initComponents();
        this.pantalla=pantalla;
        modelo=new DefaultListModel();
        this.setLocationRelativeTo(null); 
        
        try{
            ArrayList<Jugadores> players=j.listarJugadores();
            
            for(Jugadores player:players){                
                modelo.add(player.Codigo-1, player.Nombre);
            }
            PlayersList.setModel(modelo);        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR: "+ e);
        }       
        
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
        jLabel1 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        lblJugador = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PlayersList = new javax.swing.JList();
        CrearJugador = new javax.swing.JButton();
        EliminarJugador = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(uno_danjohwen.UNO_DanJohWenApp.class).getContext().getResourceMap(PantallaLogin.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setMinimumSize(new java.awt.Dimension(535, 334));
        setName("Form"); // NOI18N

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(160, 30, 217, 29);

        btnEntrar.setFont(resourceMap.getFont("btnEntrar.font")); // NOI18N
        btnEntrar.setText(resourceMap.getString("btnEntrar.text")); // NOI18N
        btnEntrar.setName("btnEntrar"); // NOI18N
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnEntrar);
        btnEntrar.setBounds(60, 130, 87, 25);

        lblJugador.setFont(resourceMap.getFont("lblJugador.font")); // NOI18N
        lblJugador.setText(resourceMap.getString("lblJugador.text")); // NOI18N
        lblJugador.setName("lblJugador"); // NOI18N
        jPanel1.add(lblJugador);
        lblJugador.setBounds(210, 70, 86, 15);

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(70, 90, 69, 25);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        PlayersList.setBackground(resourceMap.getColor("PlayersList.background")); // NOI18N
        PlayersList.setFont(resourceMap.getFont("PlayersList.font")); // NOI18N
        PlayersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PlayersList.setName("PlayersList"); // NOI18N
        PlayersList.setOpaque(false);
        jScrollPane1.setViewportView(PlayersList);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(160, 90, 197, 200);

        CrearJugador.setFont(resourceMap.getFont("CrearJugador.font")); // NOI18N
        CrearJugador.setText(resourceMap.getString("CrearJugador.text")); // NOI18N
        CrearJugador.setName("CrearJugador"); // NOI18N
        CrearJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CrearJugadorMouseClicked(evt);
            }
        });
        jPanel1.add(CrearJugador);
        CrearJugador.setBounds(380, 90, 71, 25);

        EliminarJugador.setFont(resourceMap.getFont("EliminarJugador.font")); // NOI18N
        EliminarJugador.setText(resourceMap.getString("EliminarJugador.text")); // NOI18N
        EliminarJugador.setName("EliminarJugador"); // NOI18N
        EliminarJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarJugadorMouseClicked(evt);
            }
        });
        jPanel1.add(EliminarJugador);
        EliminarJugador.setBounds(370, 130, 87, 25);

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

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
// TODO add your handling code here:
    PantallaInicio p= new PantallaInicio();
    p.setVisible(true);
    this.dispose();
}//GEN-LAST:event_jButton2MouseClicked

private void btnEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseClicked
// TODO add your handling code here:
    
      int cod= PlayersList.getSelectedIndex();

      try{
          if(Jugador==1){
            jugadores[0]=j.ObtenerDatosJugador(cod+1);
            //modelo.removeElementAt(cod);            
            lblJugador.setText("Jugador No. 2");
            Jugador=2;
        }
        else{
            jugadores[1]=j.ObtenerDatosJugador(cod+1);
            if(jugadores[0].Codigo!=jugadores[1].Codigo){
                if(pantalla==0){//Nueva Partida
                    PantallaJuego p= new PantallaJuego(jugadores);
                    p.setVisible(true);
                    this.dispose();
                }
                else{
                    PartidasGuardadas p= new PartidasGuardadas(jugadores);
                    p.setVisible(true);
                    this.dispose();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"ERROR: Jugador ya ha sido seleccionado!");
            }
            
        }
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,"ERROR: "+ e);
      }
}//GEN-LAST:event_btnEntrarMouseClicked

private void CrearJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CrearJugadorMouseClicked
// TODO add your handling code here:
    PantallaJugadores PJ= new PantallaJugadores();
    PJ.setVisible(true);
    this.dispose();
}//GEN-LAST:event_CrearJugadorMouseClicked

private void EliminarJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarJugadorMouseClicked
// TODO add your handling code here:
    int cod= PlayersList.getSelectedIndex();
    try{
        j.EliminarJugador(cod);
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,"ERROR: "+ e);
    }
    
    
}//GEN-LAST:event_EliminarJugadorMouseClicked

//private Jugadores buscarJugador(String nom, String pass){
//   String linea,nombre,password;
//   char genero;
//   int cod,edad;
//   String path=System.getProperty("user.dir");
//   String dirArchivo=path+"\\Jugadores.uno";
//   File archivo= new File(dirArchivo);
//   if(archivo.exists()){
//       try{
//           
//          BufferedReader br= new BufferedReader(new FileReader(dirArchivo));
//          
//          while((linea= br.readLine())!=null) {
//              if(!linea.isEmpty()){
//                  String[]temp=linea.split(";");
//                  cod=Integer.valueOf(temp[0]);
//                  nombre=temp[1];
//                  edad=Integer.valueOf(temp[2]);
//                  genero=temp[3].charAt(0);
//                  password=temp[4];
//                  if(nombre.equals(nom)&&password.equals(pass)){
//                      Jugadores jugador=new Jugadores(cod,nombre,edad,genero);
//                      return jugador;
//                  }
//              }
//              else{
//                  break;
//              } 
//          }
//          return null;
//       
//       }
//        catch(Exception e){
//          JOptionPane.showMessageDialog(this, e); 
//       }
//       
//   }
//    return null;
//}
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
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PantallaLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrearJugador;
    private javax.swing.JButton EliminarJugador;
    private javax.swing.JList PlayersList;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJugador;
    // End of variables declaration//GEN-END:variables
}
