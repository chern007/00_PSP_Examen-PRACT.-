/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class hilos {

    public static void main(String[] args) {

        almacen myAlmacen = new almacen();

        new Thread(new productor(myAlmacen)).start();
        new Thread(new consumidor(myAlmacen)).start();
        new Thread(new consumidor(myAlmacen)).start();

    }

}

class almacen {

    public int[] almacen = {-1, -1, -1, -1, -1};
    public int posicionActual = 0;

    public synchronized void meter() {

        if (posicionActual < 5) {

            almacen[posicionActual] = new Random().nextInt(11);
            System.out.println("El productor-" +  Thread.currentThread().getName() + " a metido el numero " + almacen[posicionActual] + " || POSICION:" + (posicionActual));
            posicionActual++;
            
            

        }

    }

    public synchronized void sacar() {

        if (posicionActual > 0) {

            System.out.println("El consumidor-" +  Thread.currentThread().getName() + " a sacado el numero " + almacen[posicionActual - 1] + " || POSICION:" + (posicionActual - 1));
            almacen[posicionActual - 1] = -1;
            posicionActual--;

        }

    }

}

class productor implements Runnable {

    almacen almacen1;

    public productor(almacen myAlmacen) {

        almacen1 = myAlmacen;
    }

    @Override
    public void run() {

        while (true) {

            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(productor.class.getName()).log(Level.SEVERE, null, ex);
            }

            almacen1.meter();

        }

    }

}

class consumidor implements Runnable {

    almacen almacen1;

    public consumidor(almacen myAlmacen) {

        almacen1 = myAlmacen;
    }

    @Override
    public void run() {

        while (true) {

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(productor.class.getName()).log(Level.SEVERE, null, ex);
            }

            almacen1.sacar();

        }

    }

}
