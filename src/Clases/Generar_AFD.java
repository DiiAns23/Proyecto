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
public class Generar_AFD {
    
    public void AFD(String nombre, LinkedList<Transiciones> transiciones, LinkedList<Estado> estados) throws FileNotFoundException, IOException{
        String Contenido;
        Contenido ="digraph G { \n"
                + "rankdir=LR;\n"
                + "node [shape= circle];"
                + " \n";
        
        String aux = "";
        int tam = transiciones.size()-1;
        for (Transiciones trans: transiciones) {
            if(trans.getAceptacion().equals("A")){
                aux += trans.getFin()+"[shape=\"doublecircle\", style= filled,color=\"#098e3\"]; \n";
            }
        }
        aux += transiciones.get(tam).getFin()+"[shape=\"doublecircle\", style= filled,color=\"#098e3\"]; \n";

        for(Transiciones trans: transiciones){
            aux += trans.getInicio()+" -> "+trans.getFin()+" [label =\""+trans.getAlfabeto()+"\"];\n";
        }
        
        aux = aux +"}";
        Contenido = Contenido + aux;
        File archivo;
        PrintWriter Escribir;
        String ruta;
        File arbol = new File("AFD_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasAutomatas.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasAutomatas.add(ruta+".png");
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
    private void CMD(String cmd) {
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
