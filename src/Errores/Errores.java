/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errores;

import Clases.Interfaz1;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Errores {
    
    public static ArrayList<Excepcion> ErroresCometidos = new ArrayList();
    
    public Errores(String tipo, String descripcion, String linea, String columna){
        Excepcion nuevo = new Excepcion(tipo, descripcion, linea, columna);
        ErroresCometidos.add(nuevo);
    }
    
    public static void ReporteErrores() throws IOException{
        String Contenido;
        Contenido ="digraph G { node [rankdir=TB,shape= filled, style= filled, fontname=\"Times New Roman\", \n"
                + "color=\"white\", fillcolor=\"#90EE90\"] nodotable \n"
                + "[ label =<<table cellpadding='10' border = '1' align='center'> \n"
                + "<tr>\n"
                + "<td>REPORTE DE ERRORES</td>\n"
                + "</tr>\n";
               

        String aux = "";
        for(Excepcion excepcion: ErroresCometidos){
            aux += "<tr>\n"
                + "<td>"+excepcion.toString()+"</td>\n"
                + "</tr>\n";
        }
        aux += "</table>>]\n"
                + "}";
        Contenido += aux;
        String ruta;
        File archivo;
        PrintWriter Escribir;
        File arbol = new File("ERRORES_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/ReporteErrores.dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/ReporteErrores";
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/ReporteErrores.dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/ReporteErrores";
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        
        String text = "dot -Tpdf " + ruta+".dot" + " -o " + ruta + ".pdf";
        CMD(text);
        //File borrar = new File (ruta+".dot");
        //borrar.delete();
    }
    
    //Aqui se hace el proceso de consola para ejecutar el archivo .dot
    public static void CMD(String cmd) {
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
