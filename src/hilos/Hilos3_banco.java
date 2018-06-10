/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chern007
 */
public class Hilos3_banco {

    public static void main(String[] args) {

        Cuenta miCuenta = new Cuenta();

        new Thread(new Usuario(miCuenta)).start();
        new Thread(new Usuario(miCuenta)).start();
        new Thread(new Usuario(miCuenta)).start();

    }

}

//ESTE SERÁ EL OBJETO COMPARTIDO, CON EL QUE SINCRONIZAREMOS LOS HILOS
class Cuenta {

    int saldo = 1000;

    public synchronized void sacarImporteCuentaComun(int importe) {

        if (saldo > 0) {

            if(saldo>importe){
            saldo -= importe;
            }else{
                importe = saldo;
                saldo = 0;
            }

            System.out.println("El usuario " + Thread.currentThread().getName() + " ha retirado " + importe + " euros." +"\nEn el banco quedan " + saldo + " euros.");

        } else {

            System.out.println("NO HAY SALDO!! El usuario " + Thread.currentThread().getName() + " no ha podido sacar dinero.");

        }

    }

    public synchronized void ingresarImporteCuentaComun(int importe) {

        saldo += importe;

    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(int saldo) {
        this.saldo = saldo;
    }

}

class Usuario implements Runnable {

    Cuenta cuenta;

    public Usuario(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {

        while (cuenta.getSaldo() > 0) {

            //try {
            //calculamos un importe rancom entre 100 y 500 euros
            int importe = 100 + new Random().nextInt(401);

            //sacamos el importe de la cuenta comun
            cuenta.sacarImporteCuentaComun(importe);

                        
            
            try {
                
              Thread.sleep(100);

            } catch (InterruptedException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("El hilo" + Thread.currentThread().getName() + " sale del banco.");
        

    }

}
