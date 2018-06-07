/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforos;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static semaforos.Main.cocineroTermino;

/**
 *
 * @author chern007
 */



public class Cocinero implements Runnable{
    
    int idCocinero;
    Semaphore semaforo;
    List platos;

    public Cocinero(int idCocinero, Semaphore semaforo, List platos) {
        this.idCocinero = idCocinero;
        this.semaforo = semaforo;
        this.platos = platos;
    }

    @Override
    public void run() {
        
        int contador = 0;
        
        while(!cocineroTermino){  
            
            
            
            try {               
                
                semaforo.acquire();
                
                if (platos.size() == 0) {                    
                    
                    
                    
                    platos.add("receta"+ contador);
                    System.out.println("El cocinero ha cocinado un plato! Platos para servir: " + platos.size());
                    
                    contador++;//sumamos uno a los platos cocinados por el cocinero
                    
                    //Thread.sleep(1000);//tarda en cocinar el plato un segundo
                    
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
            }           
            
            //liberamos el semaforo
            semaforo.release();
            
           //si el cocinero ha sacado 10 platos le decimos que termine
           if(contador == 10){cocineroTermino = true;}
           
           
        }
        
        System.out.println("El cocinero a dejado de cocinar.");
    }
    
}
