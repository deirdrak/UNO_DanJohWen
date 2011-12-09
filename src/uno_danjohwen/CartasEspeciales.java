/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno_danjohwen;

/**
 *
 * @author Wendy
 */
public class CartasEspeciales extends Cartas {
   protected String tipoCarta;
   private final static int TOMA_DOS=0,
               REVERSA=1,
               SALTA=2,
               COMODIN_COLOR=3,
               TOMA_CUATRO=4;
   
   public CartasEspeciales(int color,int tipo){
       super(color);
       switch(tipo){
           case TOMA_DOS: tipoCarta="T"; break;
           case REVERSA: tipoCarta="R"; break;
           case SALTA: tipoCarta="S";break;
           case COMODIN_COLOR: tipoCarta="C"; break;
           case TOMA_CUATRO: tipoCarta="M"; break;
       }
       Nombre=Color+tipoCarta;
   }
   
}
