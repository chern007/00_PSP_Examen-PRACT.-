/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servido_multiThread;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Cliente {

    public static void main(String[] args) {

        DataInputStream loQueEntra;
        DataOutputStream loQueSale;

        Scanner entrada = new Scanner(System.in);

        String respuesta;

        try {
            Socket sc = new Socket("localhost", 6060);

            loQueEntra = new DataInputStream(sc.getInputStream());
            loQueSale = new DataOutputStream(sc.getOutputStream());

            System.out.println("Dile la palabra clave al servidor.");
            String respuesta2 = entrada.nextLine();
            loQueSale.writeUTF(respuesta2);//W1

            if (respuesta2.equals("fichero")) {

                
                
                int longitud = loQueEntra.readInt();               
                byte[] bytesArchivo = new byte[longitud];

                loQueEntra.readFully(bytesArchivo, 0, longitud);

                FileOutputStream fos = new FileOutputStream("psp06_copia.txt");
                
                fos.write(bytesArchivo);
                
               
            } else {
                respuesta = loQueEntra.readUTF();//R1            
                System.out.println(respuesta);
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
