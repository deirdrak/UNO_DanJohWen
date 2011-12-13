/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno_danjohwen;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Wendy
 */
public class Historial {
    private String resultado;
    private Date fecha= new Date();
    private RandomAccessFile rHistorial;
    
    public Historial(){
        try{
            rHistorial=new RandomAccessFile("Partidas\\Historial.uno","rw");            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+ e.getMessage());
        }
        
    }   
    
    public void GuardarHistorial(String mensaje){
        try{
            rHistorial.seek(rHistorial.length());
            rHistorial.writeUTF(mensaje);
            rHistorial.writeLong(fecha.getTime());            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+ e.getMessage());
        }
       
    }
    
    public String[] getHistorial(){   
        int cantRegistros=0;
        ArrayList<String> historial=new ArrayList();
        String[] hist;
        try{
            rHistorial.seek(0);
            while(rHistorial.getFilePointer()< rHistorial.length()){
                String msj=rHistorial.readUTF();
                fecha=new Date(rHistorial.readLong());
                historial.add(msj+"----"+fecha);
                cantRegistros++;
            }
            hist=new String[cantRegistros];
            return historial.toArray(hist);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+ e.getMessage());
            return null;
        }
    }
}


