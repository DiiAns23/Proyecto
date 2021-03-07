/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocupjlexwindows;

import java.io.FileInputStream;

/**
 *
 * @author diego
 */
public class ProyectoCupJLexWindows {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        interpretar("Entrada.olc");
    }
    
    public static void interpretar(String path){
        Analizadores.Sintactico pars;
        try {
            pars=new Analizadores.Sintactico(new Analizadores.Lexico(new FileInputStream(path)));
            pars.parse();        
        } catch (Exception ex) {
            System.out.println("Error Fatal de Compilacion :c");
            ex.printStackTrace();
        }
    }
    
    
}