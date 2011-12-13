/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaJuego.java
 *
 * Created on 11-01-2011, 11:31:07 PM
 */
package uno_danjohwen;

/**
 *
 * @author Wendy
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;
import javax.swing.ImageIcon;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PantallaJuego extends javax.swing.JFrame {
    
    protected static ArrayList<Cartas> cards,Player1Cards,Player2Cards;
    protected static JLabel CenterCard2;
    protected static int turno, posicion;
    protected static Jugadores[] jugadores= new Jugadores[2];
    protected String CenterCardChangeColor="",resultado="",nomArchivo="";
    private int PuntosJ1=0,PuntosJ2=0;
    private RandomAccessFile rcodPartidas=null;
    private File fl=null;
    private Timer timer=null;
    private boolean castigo=true;
    private Jugadores j= new Jugadores();
    private Historial h=new Historial();
    /** Creates new form PantallaJuego */
    
    public PantallaJuego(){
        
    }
    
    public PantallaJuego(ArrayList CartasJ1, ArrayList CartasJ2, ArrayList CartasCentrales, Jugadores[] players,
            String CartaCentro, int turno, int PJ1,int PJ2,String nombreArch){
        initComponents();
        
        this.setPreferredSize(new Dimension(875,689));
        this.setMaximumSize(new Dimension(875,689));
        this.setMinimumSize(new Dimension(875,689));
        this.setLocationRelativeTo(null);
        
        timer = new Timer (3000, new ActionListener () 
        {                     
            public void actionPerformed(ActionEvent e) 
            { 
                UNO.setVisible(false);
            } 
        }); 
        
        PuntosJ1=PJ1;
        PuntosJ2=PJ2;
        jugadores=players;
        nomArchivo=nombreArch;
        this.turno=turno;
        fl=new File("Partidas\\"+nomArchivo);
        ColorChooserPanel.setVisible(false);
        UNO.setVisible(false);
        
        //this.setExtendedState(this.MAXIMIZED_BOTH);    
        
        Player1Cards=CartasJ1;
        Player2Cards=CartasJ2;
        cards=CartasCentrales;
        
        if(turno==0){
            ImprimirCartas(Player1Cards);
            jLabel1.setText("Turno de "+jugadores[0].Nombre);
            jLabel3.setText("Puntos: "+PuntosJ1);
        }            
        else{
            ImprimirCartas(Player2Cards);
            jLabel1.setText("Turno de "+jugadores[1].Nombre);
            jLabel3.setText("Puntos: "+PuntosJ2);
        }
            
        
        JLabel CenterCard= new JLabel(new ImageIcon("00.png"));
        
        
        CenterCard2= new JLabel(new ImageIcon(CartaCentro+".png"));
        CenterCard2.setName(CartaCentro);

        CenterCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CenterCardsClicked(evt);
            }
        });

        CenterPanel.add(CenterCard);
        SelectedCardPanel.add(CenterCard2);

        SelectedCardPanel.revalidate();
        CardsPanel.revalidate();
        mainPanel.revalidate();
               
        
    }
    
    public PantallaJuego(Jugadores[] Players) {
        initComponents();
        
        timer = new Timer (3000, new ActionListener () 
        {                     
            public void actionPerformed(ActionEvent e) 
            { 
                UNO.setVisible(false);// Aquí el código que queramos ejecutar.
            } 
        }); 
        
        this.setPreferredSize(new Dimension(875,689));
        this.setMaximumSize(new Dimension(875,689));
        this.setMinimumSize(new Dimension(875,689));
        this.setLocationRelativeTo(null);
     
        try {
            inicializarCodPartidas();
            fl=new File("Partidas");
            if(!fl.exists()){
                fl.mkdir();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex);
        }
        
        ColorChooserPanel.setVisible(false);
        UNO.setVisible(false);
        //this.setExtendedState(this.MAXIMIZED_BOTH);       
        
        jugadores=Players;
        cards= ArmarArreglo();
        
        Collections.shuffle(cards);
       
        Player1Cards= ArregloP1(cards);
        Player2Cards= ArregloP2(cards);

        ImprimirCartas(Player1Cards);
       
        turno=0;
        jLabel1.setText("Turno de "+jugadores[0].Nombre);
        jLabel3.setText("Puntos: "+PuntosJ1);
        
        JLabel CenterCard= new JLabel(new ImageIcon("00.png"));
        
        for(Cartas c:cards){
            if(c instanceof CartasNumericas){
                Cartas seleccionada=c;
                cards.remove(c);
                CenterCard2= new JLabel(new ImageIcon(seleccionada.Nombre+".png"));
                CenterCard2.setName(seleccionada.Nombre);

                CenterCard.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        CenterCardsClicked(evt);
                    }
                });

                CenterPanel.add(CenterCard);
                SelectedCardPanel.add(CenterCard2);

                SelectedCardPanel.revalidate();
                CardsPanel.revalidate();
                mainPanel.revalidate();
                break;
            }
        }

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                LabelMouseExited(evt);
            }
        });
            this.CardsPanel.add(carta);
            posicion++;
        }
    }
    
    private void CenterCardsClicked(MouseEvent evt) {      
      if(cards.size()>0){
          Cartas card=cards.get(0);
          cards.remove(0);
         if(turno==0){
           Player1Cards.add(card);
           CardsPanel.removeAll();
           ImprimirCartas(Player2Cards);
           jLabel1.setText("Turno de "+jugadores[1].Nombre);
           jLabel3.setText("Puntos: "+PuntosJ2);
           turno=1;
         }
         else{
           Player2Cards.add(card);
           CardsPanel.removeAll();
           ImprimirCartas(Player1Cards);
           jLabel1.setText("Turno de "+jugadores[0].Nombre);
           jLabel3.setText("Puntos: "+PuntosJ1);
           turno=0;
         }
           CardsPanel.revalidate();
      }
      else{
         JOptionPane.showMessageDialog(null, "Ya no hay más cartas!!!");
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

        mainPanel = new javax.swing.JPanel();
        CenterPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CardsPanel = new javax.swing.JPanel();
        SelectedCardPanel = new javax.swing.JPanel();
        PlayerTurnPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ColorChooserPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblVerde = new javax.swing.JLabel();
        lblAmarillo = new javax.swing.JLabel();
        lblRojo = new javax.swing.JLabel();
        lblAzul = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        UNO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(uno_danjohwen.UNO_DanJohWenApp.class).getContext().getResourceMap(PantallaJuego.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setName("Form"); // NOI18N
        setResizable(false);

        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        mainPanel.setName("mainPanel"); // NOI18N

        CenterPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CenterPanel.setName("CenterPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        CardsPanel.setBackground(resourceMap.getColor("CardsPanel.background")); // NOI18N
        CardsPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        CardsPanel.setAlignmentX(7.5F);
        CardsPanel.setAlignmentY(10.0F);
        CardsPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CardsPanel.setName("CardsPanel"); // NOI18N
        CardsPanel.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(CardsPanel);

        SelectedCardPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SelectedCardPanel.setName("SelectedCardPanel"); // NOI18N

        PlayerTurnPanel.setBackground(resourceMap.getColor("PlayerTurnPanel.background")); // NOI18N
        PlayerTurnPanel.setName("PlayerTurnPanel"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout PlayerTurnPanelLayout = new javax.swing.GroupLayout(PlayerTurnPanel);
        PlayerTurnPanel.setLayout(PlayerTurnPanelLayout);
        PlayerTurnPanelLayout.setHorizontalGroup(
            PlayerTurnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerTurnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerTurnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        PlayerTurnPanelLayout.setVerticalGroup(
            PlayerTurnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerTurnPanelLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(12, 12, 12))
        );

        ColorChooserPanel.setBackground(resourceMap.getColor("ColorChooserPanel.background")); // NOI18N
        ColorChooserPanel.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("ColorChooserPanel.border.lineColor"), 1, true)); // NOI18N
        ColorChooserPanel.setName("ColorChooserPanel"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lblVerde.setBackground(resourceMap.getColor("lblVerde.background")); // NOI18N
        lblVerde.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("lblVerde.border.lineColor"), 1, true)); // NOI18N
        lblVerde.setName("lblVerde"); // NOI18N
        lblVerde.setOpaque(true);
        lblVerde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerdeMouseClicked(evt);
            }
        });

        lblAmarillo.setBackground(resourceMap.getColor("lblAmarillo.background")); // NOI18N
        lblAmarillo.setIcon(resourceMap.getIcon("lblAmarillo.icon")); // NOI18N
        lblAmarillo.setName("lblAmarillo"); // NOI18N
        lblAmarillo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAmarilloMouseClicked(evt);
            }
        });

        lblRojo.setBackground(resourceMap.getColor("lblRojo.background")); // NOI18N
        lblRojo.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("lblRojo.border.lineColor"), 1, true)); // NOI18N
        lblRojo.setName("lblRojo"); // NOI18N
        lblRojo.setOpaque(true);
        lblRojo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRojoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRojoMouseEntered(evt);
            }
        });

        lblAzul.setBackground(resourceMap.getColor("lblAzul.background")); // NOI18N
        lblAzul.setIcon(resourceMap.getIcon("lblAzul.icon")); // NOI18N
        lblAzul.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("lblAzul.border.lineColor"), 1, true)); // NOI18N
        lblAzul.setName("lblAzul"); // NOI18N
        lblAzul.setOpaque(true);
        lblAzul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAzulMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ColorChooserPanelLayout = new javax.swing.GroupLayout(ColorChooserPanel);
        ColorChooserPanel.setLayout(ColorChooserPanelLayout);
        ColorChooserPanelLayout.setHorizontalGroup(
            ColorChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorChooserPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ColorChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(ColorChooserPanelLayout.createSequentialGroup()
                        .addComponent(lblAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ColorChooserPanelLayout.setVerticalGroup(
            ColorChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorChooserPanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addGroup(ColorChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(ColorChooserPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        UNO.setFont(resourceMap.getFont("UNO.font")); // NOI18N
        UNO.setForeground(resourceMap.getColor("UNO.foreground")); // NOI18N
        UNO.setText(resourceMap.getString("UNO.text")); // NOI18N
        UNO.setName("UNO"); // NOI18N
        UNO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UNOMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(PlayerTurnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(UNO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                                .addComponent(CenterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SelectedCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(ColorChooserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addGap(44, 44, 44))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2)
                        .addContainerGap(371, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(PlayerTurnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CenterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SelectedCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(UNO)))
                        .addGap(44, 44, 44)
                        .addComponent(ColorChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void lblAmarilloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAmarilloMouseClicked
// TODO add your handling code here:
    ColorChooserPanel.setVisible(false);
    CenterCardChangeColor="Amarillo";
}//GEN-LAST:event_lblAmarilloMouseClicked

private void lblRojoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRojoMouseClicked
// TODO add your handling code here:
    ColorChooserPanel.setVisible(false);
    CenterCardChangeColor="Rojo";
}//GEN-LAST:event_lblRojoMouseClicked

private void lblAzulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAzulMouseClicked
// TODO add your handling code here:
    ColorChooserPanel.setVisible(false);
    CenterCardChangeColor="Azul";
}//GEN-LAST:event_lblAzulMouseClicked

private void lblVerdeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerdeMouseClicked
// TODO add your handling code here:
    ColorChooserPanel.setVisible(false);
    CenterCardChangeColor="Verde";
}//GEN-LAST:event_lblVerdeMouseClicked

private void lblRojoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRojoMouseEntered
// TODO add your handling code here:
}//GEN-LAST:event_lblRojoMouseEntered

private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
//Guardar Partida en curso
    String jugador;
    if(turno==0)
        jugador=jugadores[0].Nombre;
    else
        jugador=jugadores[1].Nombre;
    
    if(JOptionPane.showConfirmDialog(this, jugador+" solicitó guardar la partida, está de acuerdo?")==0){
        RandomAccessFile partidas=null;
            try {
                if(nomArchivo.equals("")){
                    int codigoP=getCodPartidas();
                    if(codigoP!=-1){
                        partidas= new RandomAccessFile(fl+"\\partidas"+codigoP+".uno","rw");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "ERROR: La partida no se ha podido guardar, intentelo mas tarde.");
                    }
                }
                else{                   
                    if(!nomArchivo.equalsIgnoreCase("")){
                        File files[]=fl.listFiles();
                        for(File fu:files){
                            if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                                fu.delete();
                        }                   
                    }
                    fl.createNewFile();
                    partidas= new RandomAccessFile("Partidas\\"+nomArchivo,"rw");
                }
                
                    partidas.writeInt(jugadores[0].Codigo);
                    partidas.writeInt(PuntosJ1);
                    partidas.writeInt(Player1Cards.size());
                    
                    for(Cartas c: Player1Cards){
                        partidas.writeUTF(c.Nombre);
                    }
                    
                    partidas.writeInt(jugadores[1].Codigo);
                    partidas.writeInt(PuntosJ2);
                    partidas.writeInt(Player2Cards.size());
                    
                    for(Cartas c: Player2Cards){
                        partidas.writeUTF(c.Nombre);
                    }             
                    
                    partidas.writeUTF(CenterCard2.getName()); 
                    partidas.writeInt(cards.size());
                    
                    for(Cartas c: cards){
                        partidas.writeUTF(c.Nombre);
                    }      
                    partidas.writeInt(turno);
               
                JOptionPane.showMessageDialog(this, "Partida Guardada exitosamente!!!");
                irAMenuPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERROR: "+ex.getMessage());
            }        
    }
}//GEN-LAST:event_jButton1MouseClicked

private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

    //Retirarse del juego
    if(JOptionPane.showConfirmDialog(this, "Está seguro de abandonar la partida?")==0){
        if(turno==0){
            for(Cartas c:Player1Cards){
                if(c instanceof CartasNumericas){
                    PuntosJ2+=c.Numero;
                }
                else{
                    CartasEspeciales ce=((CartasEspeciales)c);
                    if(ce.tipoCarta.equalsIgnoreCase("R")||ce.tipoCarta.equalsIgnoreCase("S")||ce.tipoCarta.equalsIgnoreCase("T")){
                        PuntosJ2+=20;
                    }
                    else{
                        PuntosJ2+=50;                        
                    }
                }
            }
            resultado=jugadores[1].Nombre + " ganó en UNO!"+" con "+PuntosJ2+" puntos porque "+jugadores[0].Nombre+" se retiró" ;
            h.GuardarHistorial(resultado);
            
            if(!nomArchivo.equalsIgnoreCase("")){
                  File files[]=fl.listFiles();
                  for(File fu:files){
                      if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                          fu.delete();
                  }                   
            }
            
            try{
                j.ActualizarPuntos(jugadores[0].Codigo, PuntosJ1);
                j.ActualizarPuntos(jugadores[1].Codigo, PuntosJ2);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "ERROR: "+e.getMessage());
            }
            
            JOptionPane.showMessageDialog(null,resultado);
            irAMenuPrincipal();
        }
        else{
            for(Cartas c:Player2Cards){
                if(c instanceof CartasNumericas){
                    PuntosJ1+=c.Numero;
                }
                else{
                    CartasEspeciales ce=((CartasEspeciales)c);
                    if(ce.tipoCarta.equalsIgnoreCase("R")||ce.tipoCarta.equalsIgnoreCase("S")||ce.tipoCarta.equalsIgnoreCase("T")){
                        PuntosJ1+=20;
                    }
                    else{
                        PuntosJ1+=50;                        
                    }
                }
            }
            resultado=jugadores[0].Nombre + " ganó en UNO!"+" con "+PuntosJ1+" puntos porque "+jugadores[1].Nombre+" se retiró" ;
            h.GuardarHistorial(resultado);
            
           if(!nomArchivo.equalsIgnoreCase("")){
              File files[]=fl.listFiles();
              for(File fu:files){
                  if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                      fu.delete();
              }                   
           }
            
            try{
                j.ActualizarPuntos(jugadores[0].Codigo, PuntosJ1);
                j.ActualizarPuntos(jugadores[1].Codigo, PuntosJ2);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "ERROR: "+e.getMessage());
            }
            
            JOptionPane.showMessageDialog(null,resultado);
            irAMenuPrincipal();
        }
    }
    
}//GEN-LAST:event_jButton2MouseClicked

private void UNOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UNOMouseClicked
// TODO add your handling code here:
    if(Player1Cards.size()==1 && turno==0){
        castigo=false;
        timer.stop();         
    }
    else if(Player2Cards.size()==1 && turno==1){
        castigo=false;
        timer.stop();
    }        
    else
        castigo=true;
        
}//GEN-LAST:event_UNOMouseClicked

private void LabelMouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
   //JOptionPane.showMessageDialog(null, "Haz clickeado la etiqueta no. !!!");
    Object etiqueta=evt.getSource();
    String nom=((JLabel)etiqueta).getName();
    String imageName,color, centerCardName=CenterCard2.getName();
    String centerCardColor;
    int pos=Integer.valueOf(nom),num=0, centerCardNum=0;
    
    if( !"".equals(CenterCardChangeColor)){
        centerCardColor=CenterCardChangeColor;
    }else{
        centerCardColor=centerCardName.substring(0, centerCardName.length()-1);  
    }       
     
    if(turno==0){            
        imageName=Player1Cards.get(pos).Nombre;   

          if(Player1Cards.get(pos) instanceof CartasNumericas){
              color=Player1Cards.get(pos).Color;
              num=Player1Cards.get(pos).Numero;

              try{
                  centerCardNum=Integer.valueOf(centerCardName.substring(centerCardName.length()-1,centerCardName.length()));
                  if(centerCardName.equalsIgnoreCase(imageName)|| 
                      centerCardNum==num || 
                      centerCardColor.equalsIgnoreCase(color)){

                      CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
                      CenterCard2.setName(imageName);
                      PuntosJ1+=num;

                      cambioJugador(pos);
                  }
                  else{
                      JOptionPane.showMessageDialog(null, "Carta Incorrecta!!!");
                  }
              }
              catch(Exception ex){
                  String centerCardTipo=String.valueOf(centerCardName.charAt(centerCardName.length()-1));

                  if(centerCardName.equalsIgnoreCase(imageName)||                       
                      centerCardColor.equalsIgnoreCase(color)){

                      CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
                      CenterCard2.setName(imageName);
                      PuntosJ1+=num;
                      cambioJugador(pos);
                  }
                  else{
                      JOptionPane.showMessageDialog(null, "Carta Incorrecta!!!");
                  }

              }

          }
          else{
              imageName=Player1Cards.get(pos).Nombre;
              color=Player1Cards.get(pos).Color;
              char tipo=imageName.charAt(imageName.length()-1);
              String tipoCarta=String.valueOf(tipo);
              String tipoCentro=centerCardName.substring(centerCardName.length()-1);

              if (validarCartaEspecial(imageName,pos,tipo,tipoCarta,tipoCentro,color,centerCardColor)){
                  if(tipo=='T'||tipo=='S'||tipo=='R')
                      PuntosJ1+=20;
                  else{
                      PuntosJ1+=50;
                      //cambioJugador(pos);
                  }                     
              }                      
          }                 
                  
//            else
//            {
//                JOptionPane.showMessageDialog(null,jugadores[0].Nombre + "ha ganado el juego con "+PuntosJ1+" puntos sobre "+jugadores[1].Nombre+" que obtuvo "+PuntosJ2+"puntos!!!");
//                Player1Cards=null;
//                Player2Cards=null;                    
//            }                 
          
    }
    else{
                             
        imageName=Player2Cards.get(pos).Nombre;

        if(Player2Cards.get(pos) instanceof CartasNumericas){
              color=Player2Cards.get(pos).Color;
              num=Player2Cards.get(pos).Numero;

              try{//Si la carta del centro es numerica
                  centerCardNum=Integer.valueOf(centerCardName.substring(centerCardName.length()-1,centerCardName.length()));

                  if(centerCardName.equalsIgnoreCase(imageName)|| 
                          centerCardNum==num || 
                          centerCardColor.equalsIgnoreCase(color)){

                      CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
                      CenterCard2.setName(imageName);
                      PuntosJ2+=num;
                      cambioJugador(pos);                    
                  }
                  else{
                      JOptionPane.showMessageDialog(null, "Carta Incorrecta!!!");
                  }
              }
              catch(Exception ex){//Si la carta del centro es especial
                  String centerCardTipo=String.valueOf(centerCardName.charAt(centerCardName.length()-1));

                  if(centerCardName.equalsIgnoreCase(imageName)||                       
                      centerCardColor.equalsIgnoreCase(color)){

                      CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
                      CenterCard2.setName(imageName);
                      PuntosJ2+=num;
                      cambioJugador(pos);
                  }
                  else{
                      JOptionPane.showMessageDialog(null, "Carta Incorrecta!!!");
                  }

              }              
          }
          else{//Si la carta escogida es Especial
              imageName=Player2Cards.get(pos).Nombre;
              color=Player2Cards.get(pos).Color;
              char tipo=imageName.charAt(imageName.length()-1);
              String tipoCarta=String.valueOf(tipo);
              String tipoCentro=centerCardName.substring(centerCardName.length()-1);

              if(validarCartaEspecial(imageName,pos,tipo,tipoCarta,tipoCentro,color,centerCardColor)){
                  if(tipo=='T'||tipo=='S'||tipo=='R')
                      PuntosJ2+=20;
                  else{
                     PuntosJ2+=50;
                     //cambioJugador(pos); 
                  }
              }
          }  

     }
     CenterCardChangeColor="";
     SelectedCardPanel.revalidate();
     CardsPanel.revalidate();
    
}

private void LabelMouseEntered(java.awt.event.MouseEvent evt) {
    Object etiqueta=evt.getSource();
    JLabel label=(JLabel)etiqueta;
    Border Lborder = LineBorder.createBlackLineBorder();
    label.setBorder(Lborder);
    //label.setOpaque(true);
    label.revalidate();
}

private void LabelMouseExited(java.awt.event.MouseEvent evt) {
    Object etiqueta=evt.getSource();
    JLabel label=(JLabel)etiqueta;    
    label.setBorder(null);
    //label.setOpaque(true);
    label.revalidate();
}

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
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PantallaJuego().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardsPanel;
    private javax.swing.JPanel CenterPanel;
    private javax.swing.JPanel ColorChooserPanel;
    private javax.swing.JPanel PlayerTurnPanel;
    private javax.swing.JPanel SelectedCardPanel;
    private javax.swing.JButton UNO;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAmarillo;
    private javax.swing.JLabel lblAzul;
    private javax.swing.JLabel lblRojo;
    private javax.swing.JLabel lblVerde;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    public void agregarDosCartas(String imageName, int pos){
        if(cards.size()>0){
           if(turno==0){
              for (int i=0;i<2;i++){
                Cartas card=cards.get(i);
                Player2Cards.add(card);
                cards.remove(i);
              } 
              CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
              CenterCard2.setName(imageName);
              
              validarTamanhoArregloJ1(pos);
              
           }
           else{
               for (int i=0;i<2;i++){
                Cartas card=cards.get(i);
                Player1Cards.add(card);
                cards.remove(i);
               }
               CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
               CenterCard2.setName(imageName);
               
               validarTamanhoArregloJ2(pos);
           }
        }
        else{
            JOptionPane.showMessageDialog(null, " Ya no hay mas cartas!!!");
        }
    }
    
    public void reversaPierdeTurno(String imageName, int pos){
        if(turno==0){
              CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
              CenterCard2.setName(imageName);

              validarTamanhoArregloJ1(pos);
           }
           else{
               
              CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
              CenterCard2.setName(imageName);

              validarTamanhoArregloJ2(pos);
           }
    }
    
    public void agregar4Cartas(String imageName, int pos){
        if(cards.size()>0){
            ColorChooserPanel.setVisible(true);
           if(turno==0){
              for (int i=0;i<4;i++){
                Cartas card=cards.get(i);
                Player2Cards.add(card);
                cards.remove(i);
              } 
              CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
              CenterCard2.setName(imageName);

              validarTamanhoArregloJ1(pos);        
           }
           else{
               for (int i=0;i<4;i++){
                Cartas card=cards.get(i);
                Player1Cards.add(card);
                cards.remove(i);
              }
              CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
              CenterCard2.setName(imageName);

             validarTamanhoArregloJ2(pos);
           }
        }
        else{
            JOptionPane.showMessageDialog(null, "Ya no hay mas cartas!!!");
        }
    }
    
    public void cambioColor(String imageName, int pos){
        ColorChooserPanel.setVisible(true);
                               
        CenterCard2= new JLabel(new ImageIcon(imageName+".png"));
        CenterCard2.setName(imageName);
        cambioJugador(pos);
    }
    
    private void cambioJugador(int pos){
        if(turno==0){
            if((Player1Cards.size()-1)==0){
                
                for(Cartas c:Player2Cards){
                    if(c instanceof CartasNumericas)
                        PuntosJ1+=c.Numero;
                    else{
                        String type=((CartasEspeciales)c).tipoCarta;
                        char tipo=type.charAt(0);
                        if(tipo=='T'||tipo=='S'||tipo=='R')
                            PuntosJ1+=20;
                        else
                            PuntosJ1+=50; 
                    }
                    
                }               
                resultado=jugadores[0].Nombre + " le ganó en UNO! a "+jugadores[1].Nombre+" con "+PuntosJ1+" puntos!" ;
                
                if(!nomArchivo.equalsIgnoreCase("")){
                      File files[]=fl.listFiles();
                      for(File fu:files){
                          if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                              fu.delete();
                      }                   
                  }
                
                JOptionPane.showMessageDialog(null,resultado);
                h.GuardarHistorial(resultado);
                try{
                    j.ActualizarPuntos(jugadores[0].Codigo, PuntosJ1);
                    j.ActualizarPuntos(jugadores[1].Codigo, PuntosJ2);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(this, "ERROR: "+e.getMessage());
                }
                Player1Cards=null;
                Player2Cards=null;    
                irAMenuPrincipal();
            }
            else if((Player1Cards.size()-1)==1){               
                UNO.setVisible(true);
                timer.start();
                
                Player1Cards.remove(pos);
                //CardsPanel.remove(((JLabel)etiqueta));
                CardsPanel.removeAll();
                SelectedCardPanel.removeAll();
                SelectedCardPanel.add(CenterCard2);
                //CardsPanel.repaint();
                ImprimirCartas(Player2Cards);
                jLabel1.setText("Turno de "+jugadores[1].Nombre);
                jLabel3.setText("Puntos: "+PuntosJ2);
                turno=1; 
                
                UNO.setVisible(true);
                timer.start();
            }
            else{
                Player1Cards.remove(pos);
                //CardsPanel.remove(((JLabel)etiqueta));
                CardsPanel.removeAll();
                SelectedCardPanel.removeAll();
                SelectedCardPanel.add(CenterCard2);
                //CardsPanel.repaint();
                ImprimirCartas(Player2Cards);
                jLabel1.setText("Turno de "+jugadores[1].Nombre);
                jLabel3.setText("Puntos: "+PuntosJ2);
                turno=1;  
            }
        }
        else
        {
              if((Player2Cards.size()-1)==0){                              
                  
                  for(Cartas c:Player1Cards){
                    if(c instanceof CartasNumericas)
                        PuntosJ2+=c.Numero;
                    else{
                        String type=((CartasEspeciales)c).tipoCarta;
                        char tipo=type.charAt(0);
                        if(tipo=='T'||tipo=='S'||tipo=='R')
                            PuntosJ2+=20;
                        else
                            PuntosJ2+=50; 
                    }

                  }
                  resultado=jugadores[1].Nombre + " le ganó en UNO! a "+jugadores[0].Nombre+" con "+PuntosJ2+" puntos!" ;
                  
                  if(!nomArchivo.equalsIgnoreCase("")){
                      File files[]=fl.listFiles();
                      for(File fu:files){
                          if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                              fu.delete();
                      }                   
                  }
                  
                  JOptionPane.showMessageDialog(null,resultado);
                  h.GuardarHistorial(resultado);
                  
                  try{
                      j.ActualizarPuntos(jugadores[0].Codigo, PuntosJ1);
                      j.ActualizarPuntos(jugadores[1].Codigo, PuntosJ2);
                  }
                  catch(Exception e){
                      JOptionPane.showMessageDialog(this, "ERROR: "+e.getMessage());
                  }
                  Player1Cards=null;
                  Player2Cards=null;  
                  irAMenuPrincipal();
              }
              else if((Player2Cards.size()-1)==1){
                  UNO.setVisible(true);
                  timer.start();
                  
                  Player2Cards.remove(pos);
                  //CardsPanel.remove(((JLabel)etiqueta));
                  CardsPanel.removeAll();
                  SelectedCardPanel.removeAll();
                  SelectedCardPanel.add(CenterCard2);
                  //CardsPanel.repaint();
                  ImprimirCartas(Player1Cards);
                  jLabel1.setText("Turno de "+jugadores[0].Nombre);
                  jLabel3.setText("Puntos: "+PuntosJ1);
                  turno=0;
                  
                  UNO.setVisible(true);                
                  timer.start();

              }
              else {
                    Player2Cards.remove(pos);
                    //CardsPanel.remove(((JLabel)etiqueta));
                    CardsPanel.removeAll();
                    SelectedCardPanel.removeAll();
                    SelectedCardPanel.add(CenterCard2);
                    //CardsPanel.repaint();
                    ImprimirCartas(Player1Cards);
                    jLabel1.setText("Turno de "+jugadores[0].Nombre);
                    jLabel3.setText("Puntos: "+PuntosJ1);
                    turno=0;
              }
            
        }
    }

    private boolean validarCartaEspecial(String imageName, int pos,char tipo, String tipoCarta,
            String tipoCentro,String color,String centerCardColor){
        
        if (!color.equalsIgnoreCase("Negro")){
                  if(centerCardColor.equalsIgnoreCase(color)|| tipoCarta.equalsIgnoreCase(tipoCentro)){
                      switch(tipo){
                          case 'T':
                              agregarDosCartas(imageName, pos);                       
                              break;
                          case 'S':
                              reversaPierdeTurno(imageName,pos);
                              break;
                          case 'R':
                              reversaPierdeTurno(imageName,pos);
                              break;
                      }
                      return true;                      
                  }
                  else{
                      JOptionPane.showMessageDialog(null, "Carta Incorrecta!!!");
                      return false;
                  }
                  
          }
          else{
              switch(tipo){
                  case 'C':                          
                      cambioColor(imageName,pos);
                      break;
                  case 'M':
                      agregar4Cartas(imageName,pos);
                      break;
              }
              return true;
          }  
    }

    private void inicializarCodPartidas() throws Exception{       
        rcodPartidas= new RandomAccessFile("Partidas\\CodigoPartidas.uno","rw");
        if(rcodPartidas.length()==0){
            rcodPartidas.writeInt(1);
        }
        rcodPartidas.close();
       
    }
    
    private int getCodPartidas(){
        try {
            rcodPartidas= new RandomAccessFile("Partidas\\CodigoPartidas.uno","rw");
            int c= rcodPartidas.readInt();
            rcodPartidas.seek(0);
            rcodPartidas.writeInt(c+1);
            rcodPartidas.close();
            return c;
            
        } catch (Exception ex) {
            return -1;
        }
    }
    
    private void irAMenuPrincipal(){
        PantallaInicio PI= new PantallaInicio();
        PI.setVisible(true);
        this.dispose();
    }
    
    private void validarTamanhoArregloJ1(int pos){
        if((Player1Cards.size()-1)==0){
                
                for(Cartas c:Player2Cards){
                    if(c instanceof CartasNumericas)
                        PuntosJ1+=c.Numero;
                    else{
                        String type=((CartasEspeciales)c).tipoCarta;
                        char tipo=type.charAt(0);
                        if(tipo=='T'||tipo=='S'||tipo=='R')
                            PuntosJ1+=20;
                        else
                            PuntosJ1+=50; 
                    }                    
                }
                resultado=jugadores[0].Nombre + " le ganó en UNO! a "+jugadores[1].Nombre+" con "+PuntosJ1+" puntos!" ;
                
                if(!nomArchivo.equalsIgnoreCase("")){
                      File files[]=fl.listFiles();
                      for(File fu:files){
                          if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                              fu.delete();
                      }                   
                  }
                
                JOptionPane.showMessageDialog(null,resultado);
                h.GuardarHistorial(resultado);
                try{
                      j.ActualizarPuntos(jugadores[0].Codigo, PuntosJ1);
                      j.ActualizarPuntos(jugadores[1].Codigo, PuntosJ2);
                }
                catch(Exception e){
                      JOptionPane.showMessageDialog(this, "ERROR: "+e.getMessage());
                }
                Player1Cards=null;
                Player2Cards=null;    
                irAMenuPrincipal();
             }
             else if((Player1Cards.size()-1)==1){               
                UNO.setVisible(true);                
                timer.start();
         
                Player1Cards.remove(pos);
                CardsPanel.removeAll();
                SelectedCardPanel.removeAll();
                SelectedCardPanel.add(CenterCard2);
                //CardsPanel.repaint();
                ImprimirCartas(Player1Cards);
                jLabel3.setText("Puntos: "+PuntosJ1);
                UNO.setVisible(true);                
                timer.start();
//                if(castigo)
//                    CastigoEspecial();
             }
             else{              
                Player1Cards.remove(pos);
                //CardsPanel.remove(((JLabel)etiqueta));
                CardsPanel.removeAll();
                SelectedCardPanel.removeAll();
                SelectedCardPanel.add(CenterCard2);
                //CardsPanel.repaint();
                ImprimirCartas(Player1Cards);
                jLabel3.setText("Puntos: "+PuntosJ1);
             }
    }
    
    private void validarTamanhoArregloJ2(int pos){
        if((Player2Cards.size()-1)==0){           
          for(Cartas c:Player1Cards){
            if(c instanceof CartasNumericas)
                PuntosJ2+=c.Numero;
            else{
                String type=((CartasEspeciales)c).tipoCarta;
                char tipo=type.charAt(0);
                if(tipo=='T'||tipo=='S'||tipo=='R')
                    PuntosJ2+=20;
                else
                    PuntosJ2+=50; 
            }

          }
          resultado=jugadores[1].Nombre + " le ganó en UNO! a "+jugadores[0].Nombre+" con "+PuntosJ2+" puntos!" ;
          
          if(!nomArchivo.equalsIgnoreCase("")){
              File files[]=fl.listFiles();
              for(File fu:files){
                  if(fu.isFile() && fu.getName().equalsIgnoreCase(nomArchivo))
                      fu.delete();
              }                   
          }
          
          JOptionPane.showMessageDialog(null,resultado);
          h.GuardarHistorial(resultado);
          try{
              j.ActualizarPuntos(jugadores[0].Codigo, PuntosJ1);
              j.ActualizarPuntos(jugadores[1].Codigo, PuntosJ2);
          }
          catch(Exception e){
              JOptionPane.showMessageDialog(this, "ERROR: "+e.getMessage());
          }
          
          Player1Cards=null;
          Player2Cards=null;  
          irAMenuPrincipal();
       }
       else if((Player2Cards.size()-1)==1){
          UNO.setVisible(true);
          timer.start();
          
          Player2Cards.remove(pos);
          //CardsPanel.remove(((JLabel)etiqueta));
          CardsPanel.removeAll();
          SelectedCardPanel.removeAll();
          SelectedCardPanel.add(CenterCard2);
          //CardsPanel.repaint();
          ImprimirCartas(Player2Cards);
          jLabel3.setText("Puntos: "+PuntosJ2);
          UNO.setVisible(true);                
          timer.start();
//          if(castigo)
//              CastigoEspecial();

       }
       else {                  

          Player2Cards.remove(pos);
          //CardsPanel.remove(((JLabel)etiqueta));
          CardsPanel.removeAll();
          SelectedCardPanel.removeAll();
          SelectedCardPanel.add(CenterCard2);
          //CardsPanel.repaint();
          ImprimirCartas(Player2Cards);
          jLabel3.setText("Puntos: "+PuntosJ2);
       }
    }
    
    private void Castigo(){
        if(cards.size()>0){
            if(turno==0){
                JOptionPane.showMessageDialog(this,jugadores[1].Nombre + " haz sido castigado(a) por no haber dicho UNO! tiempo");
                for (int i=0;i<2;i++){
                    Cartas card=cards.get(i);
                    Player2Cards.add(card);
                    cards.remove(i);
                }                 
            }            
            else{
                JOptionPane.showMessageDialog(this,jugadores[0].Nombre + " haz sido castigado(a) por no haber dicho UNO! tiempo");
                for (int i=0;i<2;i++){
                    Cartas card=cards.get(i);
                    Player1Cards.add(card);
                    cards.remove(i);
                }
            }                
        }
        else{
            JOptionPane.showMessageDialog(null, " Ya no hay mas cartas!!!");
        }
    }
    
    private void CastigoEspecial(){
        if(cards.size()>0){
            if(turno==0){
                JOptionPane.showMessageDialog(this,jugadores[0].Nombre + " haz sido castigado(a) por no haber dicho UNO! tiempo");
                for (int i=0;i<2;i++){
                    Cartas card=cards.get(i);
                    Player1Cards.add(card);
                    cards.remove(i);
                }                 
            }            
            else{
                JOptionPane.showMessageDialog(this,jugadores[1].Nombre + " haz sido castigado(a) por no haber dicho UNO! tiempo");
                for (int i=0;i<2;i++){
                    Cartas card=cards.get(i);
                    Player2Cards.add(card);
                    cards.remove(i);
                }
            }                
        }
        else{
            JOptionPane.showMessageDialog(null, " Ya no hay mas cartas!!!");
        }
    }     

}


