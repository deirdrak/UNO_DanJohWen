/*
 * UNO_DanJohWenView.java
 */

package uno_danjohwen;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import org.jdesktop.application.Action;
//import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
//import org.jdesktop.application.TaskMonitor;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.Timer;
//import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * The application's main frame.
 */
public class UNO_DanJohWenView extends FrameView {
    protected ArrayList<Cartas> cards,Player1Cards,Player2Cards;
    protected JLabel CenterCard2;
    protected int turno, posicion;
    public UNO_DanJohWenView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
//        ResourceMap resourceMap = getResourceMap();
//        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
//        messageTimer = new Timer(messageTimeout, new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                statusMessageLabel.setText("");
//            }
//        });
//        messageTimer.setRepeats(false);
//        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
//        for (int i = 0; i < busyIcons.length; i++) {
//            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
//        }
//        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
//                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
//            }
//        });
//        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
//        statusAnimationLabel.setIcon(idleIcon);
//        progressBar.setVisible(false);
//
//        // connecting action tasks to status bar via TaskMonitor
//        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
//        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
//            public void propertyChange(java.beans.PropertyChangeEvent evt) {
//                String propertyName = evt.getPropertyName();
//                if ("started".equals(propertyName)) {
//                    if (!busyIconTimer.isRunning()) {
//                        statusAnimationLabel.setIcon(busyIcons[0]);
//                        busyIconIndex = 0;
//                        busyIconTimer.start();
//                    }
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(true);
//                } else if ("done".equals(propertyName)) {
//                    busyIconTimer.stop();
//                    statusAnimationLabel.setIcon(idleIcon);
//                    progressBar.setVisible(false);
//                    progressBar.setValue(0);
//                } else if ("message".equals(propertyName)) {
//                    String text = (String)(evt.getNewValue());
//                    statusMessageLabel.setText((text == null) ? "" : text);
//                    messageTimer.restart();
//                } else if ("progress".equals(propertyName)) {
//                    int value = (Integer)(evt.getNewValue());
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(false);
//                    progressBar.setValue(value);
//                }
//            }
//        });
        
        cards= ArmarArreglo();
        
        Collections.shuffle(cards);
        
        Player1Cards= ArregloP1(cards);
        Player2Cards= ArregloP2(cards);

        ImprimirCartas(Player1Cards);
       
        turno=0;
        jLabel1.setText("Turno Jugador 1");
        
        Cartas seleccionada=cards.get(0);
        cards.remove(0);
        JLabel CenterCard= new JLabel(new ImageIcon("00.png"));
        CenterCard2= new JLabel(new ImageIcon(seleccionada.Nombre+".png"));
        
        this.CenterPanel.add(CenterCard);
        this.SelectedCardPanel.add(CenterCard2);
        
        SelectedCardPanel.revalidate();
        CardsPanel.revalidate();
        mainPanel.revalidate();
     
    }

    public static ArrayList ArmarArreglo(){
        ArrayList cartas=new ArrayList();
        for(int color=0;color<5;color++){
            if(color!=4){
                for(int num=0;num<10;num++){
                    cartas.add(new CartasNumericas(color,num));
                }
                for(int num=1;num<10;num++){
                    cartas.add(new CartasNumericas(color,num));
                }
                for(int i=0;i<=2;i++){
                    for(int e=0;e<2;e++){
                        cartas.add(new CartasEspeciales(color,i));
                    }
                }
            }
            else{
                for(int e=0;e<4;e++){
                    for(int i=3;i<=4;i++){
                       cartas.add(new CartasEspeciales(color,i));
                    }
                }
               
            }
        }
        return cartas;
    }
    
    public static ArrayList ArregloP1(ArrayList<Cartas> cards){
        ArrayList<Cartas> Player1=new ArrayList();
        for (int i=0;i<7;i++){
            Cartas card=cards.get(0);
            Player1.add(card);
            cards.remove(card);
        }
        return Player1;
    }
    
    public static ArrayList ArregloP2(ArrayList<Cartas> cards){
        ArrayList<Cartas> Player2=new ArrayList();
        for (int i=0;i<7;i++){
            Cartas card=cards.get(0);
            Player2.add(card);
            cards.remove(card);
        }
        return Player2;
    }
    
    private void ImprimirCartas(ArrayList<Cartas> PlayerCards){
      posicion=0;
      for(Cartas c:PlayerCards){
            String nom=String.valueOf(posicion);
            JLabel carta=new JLabel(new ImageIcon(c.Nombre+".png"));
            carta.setName(nom);
            carta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelMouseClicked(evt);
            }
        });
            this.CardsPanel.add(carta);
            posicion++;
        }
    }
  
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = UNO_DanJohWenApp.getApplication().getMainFrame();
            aboutBox = new UNO_DanJohWenAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        UNO_DanJohWenApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        CenterPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CardsPanel = new javax.swing.JPanel();
        SelectedCardPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        PlayerTurnPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(uno_danjohwen.UNO_DanJohWenApp.class).getContext().getResourceMap(UNO_DanJohWenView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        mainPanel.setName("mainPanel"); // NOI18N

        CenterPanel.setName("CenterPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        CardsPanel.setBackground(resourceMap.getColor("CardsPanel.background")); // NOI18N
        CardsPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        CardsPanel.setAlignmentX(7.5F);
        CardsPanel.setAlignmentY(10.0F);
        CardsPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CardsPanel.setName("CardsPanel"); // NOI18N
        CardsPanel.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(CardsPanel);

        SelectedCardPanel.setName("SelectedCardPanel"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        PlayerTurnPanel.setBackground(resourceMap.getColor("PlayerTurnPanel.background")); // NOI18N
        PlayerTurnPanel.setName("PlayerTurnPanel"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout PlayerTurnPanelLayout = new javax.swing.GroupLayout(PlayerTurnPanel);
        PlayerTurnPanel.setLayout(PlayerTurnPanelLayout);
        PlayerTurnPanelLayout.setHorizontalGroup(
            PlayerTurnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerTurnPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        PlayerTurnPanelLayout.setVerticalGroup(
            PlayerTurnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerTurnPanelLayout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(PlayerTurnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(CenterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SelectedCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(27, 27, 27))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(57, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(SelectedCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton2))
                            .addComponent(CenterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(PlayerTurnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(uno_danjohwen.UNO_DanJohWenApp.class).getContext().getActionMap(UNO_DanJohWenView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
// TODO add your handling code here:
    Cartas card=cards.get(0);
//   if(turno==0){
//       Player1Cards.add(card);
//       cards.remove(card);
//       JLabel carta=new JLabel(new ImageIcon(card.Nombre+".png"));
//       carta.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                LabelMouseClicked(evt);
//            }
//        });
//       this.CardsPanel.add(carta);
//   }
//   else{
//       Player2Cards.add(card);
//       cards.remove(card);
//       JLabel carta=new JLabel(new ImageIcon(card.Nombre+".png"));
//       carta.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                LabelMouseClicked(evt);
//            }
//        });
//       this.CardsPanel.add(carta);
//   }
//   CardsPanel.revalidate();
}//GEN-LAST:event_jButton2MouseClicked

private void LabelMouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
   //JOptionPane.showMessageDialog(null, "Haz clickeado la etiqueta no. !!!");
    Object etiqueta=evt.getSource();
    String nom=((JLabel)etiqueta).getName();
    String imageName;
    int pos=Integer.valueOf(nom);
     
    if(turno==0){
          imageName=Player1Cards.get(pos).Nombre;
          CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
          Player1Cards.remove(pos);
          //CardsPanel.remove(((JLabel)etiqueta));
          CardsPanel.removeAll();
          SelectedCardPanel.removeAll();
          SelectedCardPanel.add(CenterCard2);
          //CardsPanel.repaint();
          ImprimirCartas(Player2Cards);
          jLabel1.setText("Turno Jugador 2");
          turno=1;
    }
    else{
        imageName=Player2Cards.get(pos).Nombre;
        CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
        Player2Cards.remove(pos);
          //CardsPanel.remove(((JLabel)etiqueta));
        CardsPanel.removeAll();
        SelectedCardPanel.removeAll();
        SelectedCardPanel.add(CenterCard2);
          //CardsPanel.repaint();
        ImprimirCartas(Player1Cards);
        turno=0;
        jLabel1.setText("Turno Jugador 1");
    }
     
     SelectedCardPanel.revalidate();
     CardsPanel.revalidate();
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardsPanel;
    private javax.swing.JPanel CenterPanel;
    private javax.swing.JPanel PlayerTurnPanel;
    private javax.swing.JPanel SelectedCardPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables


//    private final Timer messageTimer;
//    private final Timer busyIconTimer;
//    private final Icon idleIcon;
//    private final Icon[] busyIcons = new Icon[15];
//    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
