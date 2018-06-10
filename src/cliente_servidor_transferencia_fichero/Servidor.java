/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servidor_transferencia_fichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chern007
 */
public class Servidor implements Runnable {

    Socket sc;

    DataInputStream loQueEntra;
    DataOutputStream loQueSale;

    ObjectOutputStream oos;

    public Servidor(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

        String respuesta;
        boolean finHiloServidor = false;

        try {

            loQueEntra = new DataInputStream(sc.getInputStream());
            loQueSale = new DataOutputStream(sc.getOutputStream());
            oos = new ObjectOutputStream(sc.getOutputStream());
            
            String contenidoTotal = "";

            while (!finHiloServidor) {                

                //loQueSale.writeUTF("Elija una de las siguientes opciones a ejecutar:\n-1)Saludo.\n-2)Transferir fichero \"hola.txt\"\n-3)Leer la primera linea del fichero \"hola.txt\"");//W1
                respuesta = loQueEntra.readUTF();//R1

                //escribimos en funcion de lo que haya elegido el cliente
                switch (respuesta) {
                    case "1":

                        loQueSale.writeUTF("Hola, que pasa compadre?!");//W1

                        break;

                    case "2":

                        transferirFichero();

                        break;

                    case "3":

                        loQueSale.writeUTF("Este hilo servidor se cierra");//W1                    
                        finHiloServidor = true;

                        break;

                    case "4":

                        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\chern007\\Desktop\\tarea06.txt", "rw");

                        contenidoTotal = "";

                        //LEEMOS TODO EL DOCUMENTO
                        String linea;
                        while ((linea = raf.readLine()) != null) {

                            contenidoTotal += linea + "\n";
                        }

                        //mandamos el contenido del documento por "loQueSale"
                        loQueSale.writeUTF(contenidoTotal);//W1

                        break;

                    case "5":
                        
                        //W1
                        loQueSale.writeUTF("Para que funcione la busqueda es necesario que se haya ejecutado antes la OPCION 4) si no no encontrara nada.");
                        
                        //R2
                        String patron = loQueEntra.readUTF();
                        
                        //creamos el objeto patron
                        Pattern pat = Pattern.compile(patron);
                        Matcher mat = pat.matcher(contenidoTotal);
                        String cadenaEncontradaParaEnviar = "";
                        
                        if (mat.find()) {
                            
                            cadenaEncontradaParaEnviar = mat.group(0);
                            
                            loQueSale.writeUTF(cadenaEncontradaParaEnviar);//W2
                        }else{
                            
                            loQueSale.writeUTF("No se ha encontrado nada.");//W2
                            
                        }
                        
                        

                        break;

                    default:
                        throw new AssertionError();
                }

            }

            //cerramos el socket con el cliente y el serversocket del main
            sc.close();
            main.ss.close();

        } catch (IOException ex) {

            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void transferirFichero() {

        try {
            Path rutaArchivo = Paths.get("C:\\Users\\chern007\\Desktop\\GIFS\\eHd87.gif");
            byte[] ficheroEnBytes = Files.readAllBytes(rutaArchivo);
            //int longitudFichero = ficheroEnBytes.length;

            //loQueSale.write(longitudFichero);//W1 mandamos la longitud del array de bytes            
            //loQueSale.write(ficheroEnBytes);//W2 mandamos los bytes del fichero
            oos.writeObject(ficheroEnBytes);

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
