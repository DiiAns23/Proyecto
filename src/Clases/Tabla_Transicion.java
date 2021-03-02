/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author diego
 */
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Tabla_Transicion {

    public void Tabla(String S, LinkedList<String> encabezado, String nombre) throws IOException {

        String Contenido;
        Contenido ="digraph G { node [rankdir=TB,shape= filled, style= filled, fontname=\"Times New Roman\", "
                + "color=\"white\", fillcolor=\"#90EE90\"] nodotable "
                + "[ label =<<table cellpadding='10' border = '1' align='center'>"
                + "<tr>"
                + "<td colspan=\"3\">TABLA DE TRANSICIONES</td>"
                + "</tr>"
                + "<tr><td>Hoja"
                + "</td>"
                + "<td>Numero de hoja"
                + "</td>"
                + "<td>Siguientes"
                + "</td>"
                + "</tr>";

        String CadTokens = "";
        String tempotk;
        
        tempotk = "";
        tempotk = "<tr>";
        for (int i = 0; i < encabezado.size(); i++) {

            tempotk = tempotk + "<td>" + ""
                    + "</td>"
                    + "<td>"
                    + encabezado.get(i)
                    + "</td>";

            CadTokens = CadTokens + tempotk;

        }

        CadTokens = CadTokens + "</tr>";
        
        tempotk = "<tr>";
        CadTokens =CadTokens +tempotk + "<td>" + S
                + "</td>"
                + "</tr>";;

         Contenido = Contenido + CadTokens
                + "</table>>]}";


        File archivo;
        PrintWriter Escribir;
        String ruta;
        File arbol = new File("TRANSICIONES_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
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
