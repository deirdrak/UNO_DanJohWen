/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno_danjohwen;

/**
 *
 * @author Wendy
 */
public class Jugadores {
    private int Codigo=0;
    protected String Nombre;
    protected int Edad;
    protected char Genero;
    protected boolean Activo=true;
    
    public Jugadores(String n, int e,char g){
        Codigo+=1;
        Nombre=n;
        Edad=e;
        Genero=g;
    }
    
}
