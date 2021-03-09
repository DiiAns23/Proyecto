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
    LinkedList<LinkedList> auxiliar = new LinkedList();
    @SuppressWarnings("empty-statement")
    public void Tabla(LinkedList<Transiciones> transis, LinkedList<String> encabezado, String nombre, LinkedList<Estado> estados) throws IOException {

        String Contenido;
        Contenido ="digraph G { node [rankdir=TB,shape= filled, style= filled, fontname=\"Times New Roman\", \n"
                + "color=\"white\", fillcolor=\"#90EE90\"] nodotable \n"
                + "[ label =<<table cellpadding='10' border = '1' align='center'> \n"
                + "<tr>\n"
                + "<td colspan='"
                +(encabezado.size()+1)
                + "'>TABLA DE TRANSICIONES</td>\n"
                + "</tr>\n"
                + "<tr><td>Estado</td>\n";

        String aux = "";
        for(String a: encabezado){
            aux = aux +"<td>"+a+"</td> \n";
        }
        aux = aux +"</tr>\n";
        
        for(Estado estado: estados){
            aux = aux +"<tr>\n <td>"+ estado.getEstado()+estado.getSiguientes()+"</td>";
            LinkedList<String> aux1 = new LinkedList();
            aux1.add(estado.getEstado());
            for(String alfabeto: encabezado){
                for(Transiciones transicion: transis){
                    if(transicion.getInicio().equals(estado.getEstado())){
                        if(transicion.getAlfabeto().equals(alfabeto)){
                            aux = aux +"<td>"+transicion.getFin()+"</td>\n";
                            aux1.add(transicion.getFin());
                            transis.remove(transicion);
                            break;
                        }else{
                            aux = aux +"<td>---</td>\n";    
                            aux1.add("---");
                            break;
                        }
                    }
                }
            }
            aux = aux + "</tr>\n";
            auxiliar.add(aux1);
        }
              
//        for(Transiciones transis: transiciones){
//            aux = aux +"<tr>\n <td>"+ transis.getInicio()+"</td>";
//            for(String a: encabezado){
//                if(a.equals(transis.alfabeto)){
//                    aux = aux +"<td>"+transis.fin+"</td>\n";
//                }else{
//                    aux = aux +"<td> --- </td>\n";
//                    
//                }
//            }
//            aux = aux + "</tr>\n";
//        }
        
        
        aux = aux 
                +"</table>>]\n"
                + "}";
        Contenido = Contenido + aux;
        File archivo;
        PrintWriter Escribir;
        String ruta;
        File arbol = new File("TRANSICIONES_201903865");
        if(arbol.exists()){
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasTransiciones.add(ruta+".png");
            Escribir = new PrintWriter(archivo, "utf-8");
            Escribir.println(Contenido);
            Escribir.close();
        }
        else{
            arbol.mkdirs();
            archivo = new File(arbol.getAbsolutePath()+"/"+nombre+".dot");
            archivo.createNewFile();
            ruta = arbol.getAbsolutePath()+"/"+nombre;
            Interfaz1.RutasTransiciones.add(ruta+".png");
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
