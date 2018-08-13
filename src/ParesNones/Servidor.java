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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Servidor implements Runnable{

    Socket sc;
    
    public Servidor(Socket sc) {
        
        this.sc = sc;
    }
    

    @Override
    public void run() {
        
            
        try {
            
            DataInputStream entra = new DataInputStream(sc.getInputStream());
            DataOutputStream sale = new DataOutputStream(sc.getOutputStream());
            
            
            //esperamos el nombre del usuario
            String nombreUsuario = entra.readUTF();//R1
            
            //creamos un marcador
            Marcador miMarcador = new Marcador(nombreUsuario);
            
            //le enviamos el saludo
            sale.writeUTF("Hola "+nombreUsuario + " vamos a jugar a PARES Y NONES.");//W0
            
            //comenzamos el juego
            boolean finJuego = false;
            while (!finJuego) {                 
                
            //le mandamos el marcador actual
            sale.writeUTF(miMarcador.sacaResultado());//W1
            
            //le decimos si quiere pares o nones
            sale.writeUTF("Elige: ¿PARES o NONES?");//W2
                
            String paresOnones = entra.readUTF();//R2
                
            int numeroCliente = entra.readInt();//R3  
                
            int numeroServidor =  sacaParesOnones(); 
              
            
            int sumaNumeros = numeroCliente + numeroServidor;
            //resolvemos la partida
            
                if ((paresOnones.equals("PARES") && sumaNumeros%2 == 0) || (paresOnones.equals("NONES") && sumaNumeros%2 != 0)) {
                    
                    sale.writeUTF("Has GANADO! La suma de las dos apuestas es de " + sumaNumeros);//W3
                    miMarcador.sumarCliente();
                    
                }else{
                    
                     sale.writeUTF("Has PERDIDO! La suma de las dos apuestas es de " + sumaNumeros);//W3
                     miMarcador.sumarMaquina();
                }
                
            //quieres seguir jugando?    
               sale.writeUTF("Quieres seguir jugando???");//W4 
               String seguimosJugando = entra.readUTF();//R3
               
                if (seguimosJugando.equals("NO")) {
                    
                  finJuego= true; 
                  
                }  
               
            }
                        
            entra.close();
            sale.close();
            sc.close(); 
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    private int sacaParesOnones(){
        
       int[] opciones = {1,2,3,4,5}; 
       int rnd = new Random().nextInt(5);
        
       return opciones[rnd];
        
    }
    
    
    
    
}
