/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado_de_ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author chern007
 */

//ejercicio que cifra el archivo "tarea06.txt" que se encuentra en el escrito rio y saca un nuevo archivo "tarea06.cif"
public class Main_cifrado {
    
    public static void main(String[] args) {      
        
        
        try {
            //generamos una clave con el keygenerator
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstanceStrong();
            sr.setSeed("tuuusaaa".getBytes());
            
            //indicamos la longitud de esta
            kg.init(128,sr);
            //generamos la clave
            SecretKey key = kg.generateKey();         
            
            
            
            //creamos el cifrador
            Cipher cifrador = Cipher.getInstance("AES");
            //lo ponemos en modo "CIFRAR" y le pasamos la clave
            cifrador.init(Cipher.ENCRYPT_MODE, key);
            
            
            
            
            
            //apuntamos al fichero para hacer un Stream de los bytes
            FileInputStream fis =  new FileInputStream("C:\\Users\\chern007\\Desktop\\tarea06.txt");
            //apuntamos al fichero cifrado que vamos a crear
            FileOutputStream fos = new FileOutputStream("C:\\Users\\chern007\\Desktop\\tarea06.cif");
            //creamos el buffer de almacenamiento
            byte[] buffer = new byte[1024];
            byte[] buferCifrado;
            
            //leemos-->ciframos-->y escribimos el fichero cifrado
            
            int lectura = 0;
            
            while ((lectura=fis.read(buffer,0,1024)) != -1) {
                
                buferCifrado = cifrador.update(buffer, 0, lectura);
                
                fos.write(buferCifrado);                
                
            }
            
            
            
            
            
            //finalizamos para CERRAR el archivo
            buferCifrado = cifrador.doFinal();
            fos.write(buferCifrado);
            
            //cerramos los Streams
            fis.close();
            fos.close();
            
            System.out.println("Se ha creado el fichero cifrado.");
            
            //PROCEDEMOS A DESCIFRAR EL ARCHIVO CIFRADO QUE HEMOS GENERADO
            
            //ponemos el cifrador en modo descifrar
            cifrador.init(Cipher.DECRYPT_MODE, key);
            
            //para leer y construir el nuevo fichero descifrado utilizaremos el fis y el fos que hemos declarado anteriormente:            
            fis = new FileInputStream("C:\\Users\\chern007\\Desktop\\tarea06.cif");
            fos = new FileOutputStream("C:\\Users\\chern007\\Desktop\\tarea06(descifrado).txt");
            
            byte[] bufferCifrado2 = new byte[1024];
            byte[] bufferDescifrado2;
            
            int lectura2 = 0;
            
            while ((lectura2= fis.read(bufferCifrado2, 0, bufferCifrado2.length))!=-1) {                
                
                bufferDescifrado2 = cifrador.update(bufferCifrado2, 0, lectura2);
                
                fos.write(bufferDescifrado2);                
            }
            
            
            //ciframos el final y cerramos el fichero
            bufferDescifrado2 = cifrador.doFinal();
            fos.write(bufferDescifrado2);
            
            //cerramos el fis y el fos
            fis.close();
            fos.close();
            
            System.out.println("Se ha generado el fichero descifrado.");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main_cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(Main_cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }   
    
}
