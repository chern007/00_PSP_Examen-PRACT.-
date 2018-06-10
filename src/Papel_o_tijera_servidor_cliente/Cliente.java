/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Papel_o_tijera_servidor_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Cliente {  
    
    
    public static void main(String[] args) {
        
        Socket sc;
        DataInputStream loQueEntra;
        DataOutputStream loQeSale;
        
        Scanner entrada = new Scanner(System.in);
        
        String respuesta;
        
        boolean finDelJuego = false;
        
        try {
            
            sc = new Socket("localhost", 7070);
            
            loQueEntra = new DataInputStream(sc.getInputStream());
            loQeSale = new DataOutputStream(sc.getOutputStream());
            
            String paraOsigue ="";
            
            //recibimos el mensaje inicial del servidor
            System.out.println(loQueEntra.readUTF());//R1        
            
            while(!finDelJuego){
                
                
            do{
                System.out.println("Teclea una de las siguientes opciones:\n-piedra.\n-papel.\n-tijera.");  
            
                respuesta = entrada.nextLine();
                
            }while(!(respuesta.equals("piedra") || respuesta.equals("papel") || respuesta.equals("tijera")));
                
            //enviamos la apuesta del cliente
            loQeSale.writeUTF(respuesta);//W1
               
            //vemos la resolucion del servidor
            System.out.println(loQueEntra.readUTF());//R2
            
            //vemos si el servidor nos dice que ha acabado el juego o seguimos
            paraOsigue = loQueEntra.readUTF();
                System.out.println(paraOsigue);//imprimimos el mensaje
                
                if (paraOsigue.contains("ganado")) {
                    
                    finDelJuego = true;
                    
                }               
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    
    
    
}
