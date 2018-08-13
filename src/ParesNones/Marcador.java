/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParesNones;

/**
 *
 * @author CARLOS-HC
 */
public class Marcador {
    
    int puntosMaquina;
    int puntosCliente;
    
    String nombreCliente;

    public Marcador(String nombreCliente) {
        puntosMaquina = 0;
        puntosCliente = 0;
        this.nombreCliente = nombreCliente;
        
    }
    
    
    public void sumarCliente(){
       
        puntosCliente ++;
        
    }
    
    public void sumarMaquina(){
        
        puntosMaquina ++;
        
    }
    
    public String sacaResultado(){
        
        return nombreCliente + ": " + puntosCliente + " - Maquina: " + puntosCliente;
        
    }
    
    
}
