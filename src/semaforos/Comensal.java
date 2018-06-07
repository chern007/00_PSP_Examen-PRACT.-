/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforos;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static semaforos.Main.cocineroTermino;

/**
 *
 * @author chern007
 */
public class Comensal implements Runnable{
    
    int idComensal;
    Semaphore semaforo;
    List platos;

    public Comensal(int idComensal, Semaphore semaforo, List platos) {
        this.idComensal = idComensal;
        this.semaforo = semaforo;
        this.platos = platos;
    }

    @Override
    public void run() {
        
        while (!cocineroTermino) {  
            
            try {
                semaforo.acquire();
                
                System.out.println("El comensal <" + idComensal + "> ha adquirido el semaforo");
                
                if(platos.size() > 0){                    
;
                    platos.remove(0);   
                    System.out.println("El comensal <" + Thread.currentThread().getName() +"> ha consumido un plato! Platos para servir: " + platos.size());
                    
                    //tarda medio seg en comerse el plato
                    semaforo.release();//***IMPORTANTE DONDE HEMOS PUESTO LA LIBERACION DEL CANDADO!!!
                    Thread.sleep(500);
                    
                }else{
                    
                    System.out.println("NO HAY PLATOS el comensal <" + idComensal + "> pasa de turno.");
                    //tarda un seg en esperar
                    semaforo.release();//***IMPORTANTE DONDE HEMOS PUESTO LA LIBERACION DEL CANDADO!!!
                    Thread.sleep(new Random().nextInt(10) * 10);
                    
                }               
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Comensal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        
            
            
        } 
        
        
        System.out.println("El comensal " + Thread.currentThread().getName() + ", ha dejado de comer."); 
        
        
    }
    
    
    
    
    
    
}
