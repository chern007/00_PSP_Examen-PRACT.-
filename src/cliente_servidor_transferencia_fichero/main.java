/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servidor_transferencia_fichero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chern007
 */
public class main {

    public static boolean finalizarServer = false;
    public static ServerSocket ss;

    public static void main(String[] args) {

        try {

            ss = new ServerSocket(6060);
            Socket sc;

            while (true) {

                //capturamos el socket del cliente y se lo mandamos al contructor del hilo del servidor
                sc = ss.accept();
                //primero nos fijamos si el servidor a finalizado
                if (!finalizarServer) {
                    //creamos un hilo nuevo para la nueva peticion de conexion del cliente
                    new Thread(new Servidor(sc)).start();
                } else {
                    break;
                }

            }
            System.out.println("El servidor se ha apagado.");

        } catch (IOException ex) {

            if (ex.getMessage().contains("socket closed")) {

                System.out.println("El metodo principal del servidor se ha cerrado.");

            } else {

                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}
