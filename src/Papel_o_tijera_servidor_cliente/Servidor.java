/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Papel_o_tijera_servidor_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS-HC
 */
public class Servidor implements Runnable {

    Socket sc;
    DataInputStream loQueEntra;
    DataOutputStream loQeSale;

    int[] marcador = {0, 0};//SERVIDOR - CLIENTE

    boolean juegoFinalizado;

    public Servidor(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

        try {
            loQueEntra = new DataInputStream(sc.getInputStream());
            loQeSale = new DataOutputStream(sc.getOutputStream());

            String entrada;
            String apuestaServidor;
            int puntoPara;

            boolean juegoFinalizado = false;

            loQeSale.writeUTF("Hola, bienvenido al juego PIEDRA, PAPEL o TIJERA!!!\n(Gana el mejor de 3)"
                    + "\n\nEmpezamos: ¿Piedra, papel o tijera?");//W1

            while (!juegoFinalizado) {

                //recogemos la apuesta del cliente
                entrada = loQueEntra.readUTF();//R1

                apuestaServidor = SacaOpcion();
                
                //vemos quien gana
                puntoPara = quienGana(apuestaServidor, entrada);
                
                //actualizamos el marcador
                if (puntoPara!=-1) {                    
                    marcador[puntoPara] = marcador[puntoPara] +1;

                    if (puntoPara==0) {
                       loQeSale.writeUTF("Has perdido, el servidor ha sacado " + apuestaServidor);//W2
                    }else{
                       loQeSale.writeUTF("Has ganado, el servidor ha sacado " + apuestaServidor);//W2
                    }
                    
                    
                }else{
                    loQeSale.writeUTF("Has empatado, el servidor ha sacado " + apuestaServidor);//W2
                }
                
                
                //comprobamos si alguien ha ganado
                if (marcador[0]>1) {
                    
                    loQeSale.writeUTF("El servidor ha ganado la partida al mejor de 3.");//W3
                    juegoFinalizado= true;
                    
                }else if (marcador[1]>1){
                    
                    loQeSale.writeUTF("Has ganado la partida al mejor de 3. ENHORABUENA!");//W3
                    juegoFinalizado= true;
                    
                }else{
                    
                    loQeSale.writeUTF("Siguiente turno.\n\n¿Piedra, papel o tijera?");//W3
                    
                }
                
                

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String SacaOpcion() {

        String[] opciones = {"piedra", "papel", "tijera"};

        int aleatorio = new Random().nextInt(3);

        return opciones[aleatorio];

    }

    private int quienGana(String apuestaServidor, String apuestaCliente) {
        
        int respuesta_aux = -1;

        switch (apuestaServidor) {
            case "piedra":

                if (apuestaCliente.equals("papel")) {

                    respuesta_aux = 1;

                } else if (apuestaCliente.equals("tijera")) {

                    respuesta_aux = 0;

                } else {

                    respuesta_aux = -1;

                }

                break;

            case "tijera":
                
                if (apuestaCliente.equals("papel")) {

                    respuesta_aux = 0;

                } else if (apuestaCliente.equals("piedra")) {

                    respuesta_aux = 1;

                } else {

                    respuesta_aux = -1;

                }

                break;

            case "papel":
                
                if (apuestaCliente.equals("piedra")) {

                    respuesta_aux = 0;

                } else if (apuestaCliente.equals("tijera")) {

                    respuesta_aux = 1;

                } else {

                    respuesta_aux = -1;

                }

                break;

            default:
                throw new AssertionError();
        }
        
        return respuesta_aux;

    }

}
