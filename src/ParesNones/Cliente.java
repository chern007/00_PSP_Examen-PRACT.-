/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParesNones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Cliente {    
    

    public static void main(String[] args) {
            
        try {
            
           Socket sc = new Socket("localhost",6060);
            
           DataInputStream entra = new DataInputStream(sc.getInputStream()); 
           DataOutputStream sale = new DataOutputStream(sc.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
}
