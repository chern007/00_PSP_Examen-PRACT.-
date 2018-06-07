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
public class hilos2 {

    public static void main(String[] args) {

        Candado miCandado = new Candado();
        
        new Thread(new Productor(miCandado)).start();
        new Thread(new Consumidor(miCandado)).start();
        new Thread(new Consumidor(miCandado)).start();
        

    }

}

class Candado {

    public boolean finalizado = false;

    int[] almacen = {-1, -1, -1, -1, -1};
    int posicionActual = 0;

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getAlmacen(int posicion) {
        return almacen[posicion];
    }

    public void setAlmacen(int posicion, int valor) {
        this.almacen[posicion] = valor;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

}

class Productor implements Runnable {

    Candado candado1;

    public Productor(Candado candado1) {
        this.candado1 = candado1;
    }

    @Override
    public void run() {

        synchronized (candado1) {

            for (int i = 0; i < 101; i++) {

                if (candado1.getPosicionActual() < 5) {

                    candado1.setAlmacen(candado1.getPosicionActual(), new Random().nextInt(11));

                    System.out.println("El productor-" + Thread.currentThread().getName() + " a metido el numero " + candado1.getAlmacen(candado1.getPosicionActual()) + " || en la POSICION:" + candado1.getPosicionActual());
                    candado1.setPosicionActual(candado1.getPosicionActual() + 1);

                } else {

                    try {
                        System.out.println("El productor tiene que esperar. �ALMACEN LLENO!. (" + Thread.currentThread().getName() + ")");
                        candado1.notifyAll();//notificamos a todos para que los CONSUMIDORES se activen
                        candado1.wait();//paramos el hilo PRODUCTOR
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }


            }
            
                System.out.println("El productor termin� toda su producci�n. (" + Thread.currentThread().getName() + ")");
                candado1.finalizado = true;
                candado1.notifyAll();

        }

    }

}

class Consumidor implements Runnable {

    Candado candado1;

    public Consumidor(Candado candado1) {
        this.candado1 = candado1;
    }

    @Override
    public void run() {

        synchronized (candado1) {

            while (true) {

                if (candado1.getPosicionActual() > 0) {

                    try {
                        System.out.println("El consumidor-" + Thread.currentThread().getName() + " a sacado el numero " + candado1.getAlmacen((candado1.getPosicionActual() - 1)) + " || de la POSICION:" + (candado1.getPosicionActual() - 1));
                        
                        candado1.setAlmacen((candado1.getPosicionActual() - 1), -1);
                        
                        candado1.setPosicionActual((candado1.getPosicionActual() - 1));
                        
                        candado1.notifyAll();
                        
                        candado1.wait();
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else{
                    
                    try {
                        System.out.println("El consumidor-" + Thread.currentThread().getName() + " est� esperando.");
                        candado1.notifyAll();//notificamos a todos para que el PRODUCTOR se active
                        candado1.wait();//paramos este hilo
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }

                if (candado1.finalizado && candado1.getPosicionActual() == 0 ) {
                    
                    System.out.println("El consumidor-" + Thread.currentThread().getName() + " se fu�.");
                    
                        candado1.notifyAll();
                        break;
                }

            }

        }

    }

}
