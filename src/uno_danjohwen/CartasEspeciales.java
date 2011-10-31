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
           case TOMA_DOS: tipoCarta="TOMA_DOS"; break;
           case REVERSA: tipoCarta="REVERSA"; break;
           case SALTA: tipoCarta="SALTA";break;
           case COMODIN_COLOR: tipoCarta="COMODIN_COLOR"; break;
           case TOMA_CUATRO: tipoCarta="TOMA_CUATRO"; break;
       }
       Nombre=Color+tipoCarta;
   }
   
}
