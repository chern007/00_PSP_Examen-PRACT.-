/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParesNones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

        try {

            Scanner entrada = new Scanner(System.in);

            Socket sc = new Socket("localhost", 6060);

            DataInputStream entra = new DataInputStream(sc.getInputStream());
            DataOutputStream sale = new DataOutputStream(sc.getOutputStream());

            String nombre = "";
            do {
                System.out.println("Introduce tu nombre:");

                nombre = entrada.nextLine();

            } while (nombre.equals(""));

            //enviamos el nombre del usuario
            sale.writeUTF(nombre);//W1

            //recibimos el saludo del server
            System.out.println(entra.readUTF());//R0

            //comenzamos el juego
            boolean finJuego = false;
            while (!finJuego) {

                //imprimimos el marcador que nos manda   
                System.out.println(entra.readUTF());//R1

                //imprimimos mensaje de pares o nones
                String mensajeServidorParesNones = entra.readUTF();//R2              

                String paresNones = "";
                do {
                    System.out.println(mensajeServidorParesNones);

                    paresNones = entrada.nextLine().toUpperCase();

                } while (!paresNones.equals("PARES") && !paresNones.equals("NONES"));

                //le enviamos la eleccion al servidor
                sale.writeUTF(paresNones);//W2

                int eligeNumero = 0;
                do {
                    System.out.println("Elije un número del 1 al 5:");

                    try {
                        eligeNumero = entrada.nextInt();
                        entrada.nextLine();
                    } catch (Exception e) {
                    }

                } while (eligeNumero != 1 && eligeNumero != 2 && eligeNumero != 3 && eligeNumero != 4 && eligeNumero != 5);

                //el enviamos el nmero que hemos elegido
                sale.writeInt(eligeNumero);//W3

                //imprimimos el resultado
                System.out.println(entra.readUTF());//R3

                //imprimimos si quiere seguir jugando
                String seguirJugando = entra.readUTF();//R3

                String seguimos = "";
                do {
                    System.out.println(seguirJugando);

                    seguimos = entrada.nextLine().toUpperCase();

                } while (!seguimos.equals("SI") && !seguimos.equals("NO"));

                //le mandamos si seguimos o no            
                sale.writeUTF(seguimos);

                if (seguimos.equals("NO")) {

                    finJuego = true;

                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
