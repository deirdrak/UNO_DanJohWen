/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno_danjohwen;

/**
 *
 * @author Wendy
 */
public class Cartas {
    protected int Numero;
    protected String Color;
    protected String Nombre;
    
    private final static int AMARILLO=0,
                         AZUL=1,
                         ROJO=2,
                         VERDE=3,
                         NEGRO=4;
    
    public Cartas(int color){
        switch(color){
            case AMARILLO: Color="Amarillo";
                           break;
            case AZUL: Color="Azul";
                       break;
            case ROJO: Color="Rojo";
                       break;
            case VERDE: Color="Verde";
                        break;
            case NEGRO: Color="Negro"; 
                        break;
                
        }
    }
    
   
    
    public Cartas[] BarajarCartas(Cartas[] arregloCartas){
        for(int i=arregloCartas.length;i>=0;i--){
            int ale=(int)(Math.random()*(i+1));
            Cartas temp=arregloCartas[i];
            arregloCartas[i]=arregloCartas[ale];
            arregloCartas[ale]=temp;
        }
        return arregloCartas;
    }
    
}
