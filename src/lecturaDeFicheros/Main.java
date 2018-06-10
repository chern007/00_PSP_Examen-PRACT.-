/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaDeFicheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chern007
 */
public class Main {
    
    
    public static void main(String[] args) {
        
        
        try {
            
            RandomAccessFile raf = new RandomAccessFile("C:\\Users\\chern007\\Desktop\\tarea06.txt","rw");           
            
            
            String linea;
            while((linea = raf.readLine())!= null){
                
                System.out.println(linea); 
                
            }
            
            
            System.out.println("***********************************************************************************");
 
            FileReader fr = new FileReader("C:\\Users\\chern007\\Desktop\\tarea06.txt");    
            
            BufferedReader br = new BufferedReader(fr);//creamos un buffered para que almacene TODOS los caracteres del fichero y tener mas recursos para sacar el contenido de este.
            
            System.out.println(br.readLine());
            
            fr.close();
            br.close();
            
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
}
