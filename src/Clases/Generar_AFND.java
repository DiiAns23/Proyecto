/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class Generar_AFND {
    
    public static void AFND(String nombre, LinkedList<AFND> transiciones) throws FileNotFoundException, IOException{
        String Contenido;
        Contenido ="digraph G { \n"
                + "rankdir=LR;\n"
                + "node [shape= circle];"
                + " \n";
        
        String aux = "";
        for(AFND hola: transiciones){
            if(null != hola.getAceptacion()){
                aux += "S"+hola.getInicio()+";\n";
                aux += "S"+hola.getFin()+"[shape=\"doublecircle\", style= filled,color=\"#098e3\"]; \n";
            }else{
                aux += "S"+hola.getInicio()+";\n";
            }
            
        }
        for(AFND jais: transiciones){
            aux += "S"+jais.getInicio() +"-> S"+jais.getFin()+" [label =\""+jais.getValor()+"\"];\n";
        }
        
        aux = aux +"}";
        Contenido = Contenido + aux;
        File archivo;
        PrintWriter Escribir;
        String ruta;
        File arbol = new File("AFND_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasAFND.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasAFND.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        
        String text = "dot -Tpng " + ruta+".dot" + " -o " + ruta + ".png";
        CMD(text);
        File borrar = new File (ruta+".dot");
        borrar.delete();
    }
     
    //Aqui se hace el proceso de consola para ejecutar el archivo .dot
    private static void CMD(String cmd) {
        Process proceso;
        try {
            proceso = Runtime.getRuntime().exec(cmd);
            proceso.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
