/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servidor_transferencia_fichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chern007
 */
public class Cliente {

    public static void main(String[] args) {

        Socket sc;
        DataInputStream loQueEntra;
        DataOutputStream loQueSale;

        ObjectInputStream ois;

        try {

            Scanner entrada = new Scanner(System.in);
            String viene;
            String va;

            boolean finCliente = false;

            sc = new Socket("localhost", 6060);

            loQueEntra = new DataInputStream(sc.getInputStream());
            loQueSale = new DataOutputStream(sc.getOutputStream());
            ois = new ObjectInputStream(sc.getInputStream());

//            viene = loQueEntra.readUTF();//R1
//            System.out.println(viene);
            while (!finCliente) {
                System.out.println("Elija una de las siguientes opciones a ejecutar:\n-1)Saludo.\n"
                        + "-2)Transferir fichero \"tamariz.gif\"\n"
                        + "-3)Matar el servidor(no generará mas hilos)\n"
                        + "-4)Leer el contenido del fichero \".txt\"\n"
                        + "-5)Buscar palabra en el fichero\".txt\""
                );

                //leemos la entrada de la opcion
                do {
                    va = entrada.nextLine();
                } while (!va.equals("1") && !va.equals("2") && !va.equals("3") && !va.equals("4") && !va.equals("5"));

                //mandamos la respuesta al servidor
                loQueSale.writeUTF(va);//W1

                //leemos dependiendo de lo que hayamos elegido:
                switch (va) {
                    case "1":

                        viene = loQueEntra.readUTF();//R1
                        System.out.println(viene);

                        break;

                    case "2":

                        //recibiendo el fichero
//                    int longitudFichero2 = loQueEntra.readInt();//R1                   
//                    byte[] ficheroEnBytes2 = new byte[longitudFichero2];
                        //loQueEntra.readFully(ficheroEnBytes2, 0, longitudFichero2);//R2 
                        byte[] ficheroEnBytes2 = (byte[]) ois.readObject();

                        FileOutputStream guardaFichero = new FileOutputStream("tamariz.gif");

                        guardaFichero.write(ficheroEnBytes2);

                        System.out.println("El fichero \"tamariz.gif\" se ha guardado.");

                        break;

                    case "3":

                        viene = loQueEntra.readUTF();//R1
                        System.out.println(viene);

                        finCliente = true;

                        break;

                    case "4":                       
                        
                        
                        viene = loQueEntra.readUTF();//R1                       
                        
                        System.out.println("Este es el contenido del fichero: \n\n" + viene);
                        
                        

                        break;
                        
                        case "5":                       
                        
                        viene = loQueEntra.readUTF();//R1                       
                        System.out.println("");
                        
                            System.out.println("Por favor escriba la palabra que quiere buscar en el documento \"tarea06.txt\"");
                            
                        loQueSale.writeUTF(entrada.nextLine());
                        
                        viene = loQueEntra.readUTF();//R1                       
                        System.out.println(viene);                       

                        break;

                    default:
                        throw new AssertionError();
                }

            }

            sc.close();//cerramos el socket

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
