/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servido_multiThread;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Main_Servidor {

    public static boolean finalizar = false;

    public static void main(String[] args) {

        try {

            ServerSocket ss = new ServerSocket(6060);

            Socket sc;

            while (!finalizar) {

                sc = ss.accept();

                new Thread(new Servidor(sc)).start();

                System.out.println("Nuevo hilo de servidor generado.");

            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

class Servidor implements Runnable {

    Socket sc;

    DataInputStream loQueEntra;
    DataOutputStream loQueSale;

    String respuesta;

    public Servidor(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

        try {
            loQueEntra = new DataInputStream(sc.getInputStream());
            loQueSale = new DataOutputStream(sc.getOutputStream());

            respuesta = loQueEntra.readUTF();//R1

            if (respuesta.equals("andeve")) {

                loQueSale.writeUTF("Palabra secreta correcta.");//W1

            } else if (respuesta.equals("fichero")) {

                Path ruta = Paths.get("C:\\Users\\chern007\\Desktop\\tarea06.txt");
                //Path ruta = Paths.get("psp06.txt");
                byte[] ficheroBytes = Files.readAllBytes(ruta);

                loQueSale.writeInt(ficheroBytes.length);//W1                
                loQueSale.write(ficheroBytes);//W2              
            } else if (respuesta.equals("chao")) {

                loQueSale.writeUTF("El servidor se cerrará");//W1
                Main_Servidor.finalizar = true;

            } else {

                loQueSale.writeUTF("Palabra secreta incorrecta.");//W1

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
