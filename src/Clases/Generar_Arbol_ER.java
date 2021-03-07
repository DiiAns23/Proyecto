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
import java.io.*;

public class Generar_Arbol_ER {

    
    public void Crear(String titulo, String Texto) throws IOException {
        String ruta;
        File archivo;
        PrintWriter Escribir;
        File arbol = new File("ARBOLES_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/"+titulo+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+titulo;
            Interfaz1.RutasArboles.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Texto);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/"+titulo+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+titulo;
            Interfaz1.RutasArboles.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Texto);
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
