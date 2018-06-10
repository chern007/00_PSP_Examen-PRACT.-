/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author chern007
 */
public class Main {
    
    public static boolean cocineroTermino = false;
    
    public static void main(String[] args) {
        

        //porque el cocinero saca comida para 1 comensal
        Semaphore semaforo = new Semaphore(1);
        //platos que hay actualmente
        List platos = new ArrayList();
        
           
        new Thread(new Cocinero(0,semaforo,platos)).start();
        new Thread(new Comensal(1,semaforo,platos)).start();
        new Thread(new Comensal(2,semaforo,platos)).start();
        new Thread(new Comensal(3,semaforo,platos)).start();
        
        
    }
    
    
}
