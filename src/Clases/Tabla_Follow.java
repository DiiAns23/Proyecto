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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tabla_Follow {
        public void TablaFollow(ArrayList<Lista_Follow> Aceptacion,String nombre) throws IOException {
        String Contenido;
        Contenido ="digraph G{ "
                + "node[shape= filled, style= filled, fontname=\"Times New Roman\",color=\"white\", fillcolor=\"#90EE90\"] "
                + "nodotable[ label =<"
                + "<table cellpadding='10' border = '1' align='center'>"
                + "<tr><td colspan=\"10\">TABLA DE SIGUIENTES</td></tr>"
                + "<tr><td>Hoja</td><td>Numero de hoja</td><td>Siguientes</td></tr>";

        String CadTokens = "";
        String tempotk;
        for (int i = 0; i < Aceptacion.size(); i++) {
            tempotk = " ";
            tempotk = "<tr>"
                    + "<td>" + Aceptacion.get(i).getHoja()
                    + "</td>"
                    + "<td>"
                    + String.valueOf(Aceptacion.get(i).getNumero())
                    + "</td>"
                    + "<td>" + Aceptacion.get(i).getFollow()
                    + "</td>"
                    + "</tr>";
            CadTokens = CadTokens + tempotk;
        }
        Contenido = Contenido + CadTokens
                + "</table>>]"
                + "}";
        
        File archivo;
        PrintWriter Escribir;
        String ruta;
        File arbol = new File("SIGUIENTES_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasSiguientes.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasSiguientes.add(ruta+".png");
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
