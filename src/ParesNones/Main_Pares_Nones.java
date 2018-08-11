/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParesNones;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Main_Pares_Nones {
    
    
    public static void main(String[] args) {
            
        try {
            ServerSocket ss = new ServerSocket(6060);
            
            Socket sc;
            
            
            while (true) {                
               
                //recogemos el socket dl cliente
               sc = ss.accept();
                
               //creamos un nuevo hilo servidor con el socket del cliente 
                
                new Thread(new Servidor(sc)).start();   
                
                
                
            }
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Main_Pares_Nones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
}
