/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegEx;

import java.io.IOException;
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
public class Main_RegEx {

    public static void main(String[] args) {
        
        
        
        try {
            Path ruta = Paths.get("C:\\Users\\chern007\\Desktop\\tarea06.txt");
            byte[] ficheroBytes = Files.readAllBytes(ruta);
            
            System.out.println(ficheroBytes.length);
            
            
            
            
            String contenido = "Enunciado.\n"
                    + "La tarea de la unidad esta dividida en dos actividades.\n"
                    + "\n"
                    + "Actividad 6.1. Crea una aplicación que realice los siguientes pasos:\n"
                    + "\n"
                    + "Solicita el nombre del usuario que va a utilizar la aplicación. El login tiene una longitud de 8 caracteres y está compuesto únicamente por letras minúsculas.\n"
                    + "Solicita al usuario el nombre de un fichero que quiere mostrar. El nombre del fichero es como máximo de 8 caracteres y tiene una extensión de 3 caracteres.\n"
                    + "Visualiza en pantalla el contenido del fichero.\n"
                    + "Es importante tener en cuenta que se tiene que realizar una validación de los datos de entrada y llevar un registro de la actividad del programa.\n"
                    + "\n"
                    + "Actividad 6.2. Utilizando la aplicación desarrollada en la actividad anterior, configura las políticas de acceso para:\n"
                    + "\n"
                    + "Firmar digitalmente la aplicación.\n"
                    + "Que sólo pueda leer los datos del directorio c:/datos.\n"
                    + "Criterios de puntuación. Total 10 puntos.\n"
                    + "Actividad 6.1 (5 puntos).\n"
                    + "Actividad 6.2 (5 puntos).\n"
                    + "Recursos necesarios para realizar la Tarea.\n"
                    + "Para realización de la actividad tan sólo es necesario tener instalado el entorno de desarrollo de Java.\n"
                    + "Consejos y recomendaciones.\n"
                    + "Leer detenidamente el contenido de la unidad.";
            
            //creamos el objeto patron
            Pattern pat = Pattern.compile("del");
            Matcher mat = pat.matcher(contenido);
            
            mat.find();
            
            System.out.println(mat.group(0));
            
            
        } catch (IOException ex) {
            Logger.getLogger(Main_RegEx.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
