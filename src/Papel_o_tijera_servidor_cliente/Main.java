/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Papel_o_tijera_servidor_cliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Main {
    
    public static ServerSocket ss;
    
    public static void main(String[] args) {
        
        try {
            
             ss = new ServerSocket(7070);
             Socket sc;
            
            while(true){
                
                //esperamos a una conexion del cliente
                sc = ss.accept();
                
                //creamos un hilo servidor para el cliente
                new Thread(new Servidor(sc)).start();
                
                
            }
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
}
