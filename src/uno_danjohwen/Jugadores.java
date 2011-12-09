/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno_danjohwen;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wendy
 */
public class Jugadores {
    protected int Codigo=0;
    protected String Nombre;
    protected int Edad;
    protected char Genero;
    protected int Puntaje;
    protected boolean Activo=true;
    
    private RandomAccessFile jugadores = null;
    
    public Jugadores(){
        try{           
            jugadores = new RandomAccessFile ("Jugadores.uno","rw");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR: "+ e.getMessage());
        }
    } 
    
    public Jugadores(int cod,String nombre,int edad, char genero,int puntos){
        Codigo=cod;
        Nombre=nombre;
        Edad=edad;
        Genero=genero;
        Puntaje=puntos;
    }
  
    private int getCodigo(){
        try{
            RandomAccessFile codigos = new RandomAccessFile("codigos.uno","rw");
            int cod =1;
            if(codigos.length() == 0){
                codigos.writeInt(cod + 1);
                return cod;
            }
            else{
                codigos.seek(0);
                cod = codigos.readInt();
                codigos.seek(0);
                codigos.writeInt(cod+1);
                codigos.close();
                return cod;
            }
        }
        catch(Exception error){
           JOptionPane.showMessageDialog(null,"Error: " + error.getMessage());
           return 0;
        }
    }
    
    public boolean CrearJugador(String nombre,int edad,char genero)throws IOException{
        int codigo = getCodigo();
        
        if(codigo != 0){
                        
            jugadores.seek(jugadores.length());
            jugadores.writeInt(codigo);
            jugadores.writeUTF(nombre);
            jugadores.writeInt(edad);
            jugadores.writeChar(genero);
            jugadores.writeInt(0);
            jugadores.writeBoolean(true);
            return true;
        }
        JOptionPane.showMessageDialog(null,"No puede crear un nuevo jugador!!");
        return false;
    }
    
    public boolean EliminarJugador(int code)throws IOException{
        jugadores.seek(0);
        
        while(jugadores.getFilePointer()<jugadores.length()){
            int cod = jugadores.readInt();
            jugadores.readUTF();
            jugadores.seek(jugadores.getFilePointer()+10);
            
            
            if(jugadores.readBoolean() && cod == code){
                jugadores.seek(jugadores.getFilePointer()-1);
                jugadores.writeBoolean(false);
                JOptionPane.showMessageDialog(null,"Se ha eliminado el jugador exitosamente!!");
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Jugadores> listarJugadores()throws IOException{
        ArrayList<Jugadores> players= new ArrayList();
        jugadores.seek(0);
        
        while(jugadores.getFilePointer() < jugadores.length()){
            int code = jugadores.readInt();
            String name = jugadores.readUTF();
            int age = jugadores.readInt();
            char gen = jugadores.readChar();
            int pun = jugadores.readInt();
            
            if(jugadores.readBoolean())
                players.add(new Jugadores(code,name,age,gen,pun));
        }
        return players;
    }
    
    public boolean ActualizarPuntos(int code,int CantidadPuntos)throws IOException{
        jugadores.seek(0);
        
        while(jugadores.getFilePointer()<jugadores.length()){
            int cod = jugadores.readInt();
            jugadores.readUTF();
            jugadores.readInt();
            jugadores.readChar();
            long posact = jugadores.getFilePointer();
            int puntos = jugadores.readInt();
            
            if(jugadores.readBoolean() && code == cod){
                jugadores.seek(posact);
                jugadores.writeInt(puntos + CantidadPuntos);
                return true;
            }
        }
        return false;
    }
    
    public Jugadores ObtenerDatosJugador(int code)throws IOException{
        Jugadores jugador = new Jugadores();
        jugadores.seek(0);
        
        while(jugadores.getFilePointer()<jugadores.length()){
            int cod = jugadores.readInt();
            String name = jugadores.readUTF();
            int edad = jugadores.readInt();
            char genero = jugadores.readChar();
            int puntos = jugadores.readInt();
            
            if(jugadores.readBoolean() && code == cod){
               jugador.Codigo = cod;
                jugador.Nombre = name;
                jugador.Edad = edad;
                jugador.Genero = genero;
                jugador.Puntaje = puntos;
             
                return jugador;
            }
        }
        return null;
    }
}
