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
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Expresion_Regular {

    public static LinkedList<String> ER = new LinkedList();
    public static ArrayList<Lista_ER> Caracteres = new ArrayList<Lista_ER>();
    public static ArrayList<String> nombres = new ArrayList<String>();
    public static int index = 0, follow =0;

    public static void ER(String cadena,String nombre) throws IOException, InterruptedException {
        ER.add(cadena);   
        nombres.add(nombre);
    }

    public static void agregar(String etiqueta, String descripcion) {
        Lista_ER nuevo = new Lista_ER(etiqueta, descripcion);
        Caracteres.add(nuevo);
    }

    public static void Separacion() throws IOException, InterruptedException {
        String cc = "";
        String er = "";
        char caracter = ' ';
        int estado = 0;

        //Se recorre la lista que contiene las cadenas de Expresiones Regulaes
        for (int i = 0; i < ER.size(); i++) {
            agregar(".", ".");
            er = ER.get(i);
            //Se separa caracter por caracter la Expresion Regular
            for (int j = 0; j < er.length(); j++) {
                caracter = er.charAt(j);
                switch (estado) {
                    case 0:
                        switch (caracter) {
                            case (char) 46:
                                agregar(Character.toString(caracter), ".");
                                estado = 0;
                                break;
                            case (char) 124:
                                agregar(Character.toString(caracter), "|");
                                estado = 0;
                                break;
                            case (char) 63:
                                agregar(Character.toString(caracter), "?");
                                estado = 0;
                                break;
                            case (char) 42:
                                agregar(Character.toString(caracter), "*");
                                estado = 0;
                                break;
                            case (char) 43:
                                agregar(Character.toString(caracter), "+");
                                estado = 0;
                                break;
                            case (char) 92:
                                estado = 3;
                                break;
                            case (char) 34:
                                estado = 1;
                                break;
                            case (char) 123:
                                estado = 2;
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        if (caracter != (char) 34) {
                            if(caracter == (char) 32){
                                cc += " ";
                            }else{
                                cc += caracter;
                            }
                            
                        } else {
                            agregar(cc, "cadena");
                            cc = "";
                            estado = 0;
                        }
                        break;
                    case 2:
                        if (caracter != (char) 125) {
                            cc += caracter;
                        } else {
                            agregar(cc, "identificador");
                            cc = "";
                            estado = 0;
                        }
                        break;
                        
                    case 3:
                        switch (caracter) {
                            case (char) 110:
                                agregar("\\\\n", "cadena");
                                cc="";
                                estado = 0;
                                break;
                            case (char) 39:
                                agregar("\'", "cadena");
                                cc="";
                                estado = 0;
                                break;
                            case (char) 34:
                                agregar("\"", "cadena");
                                cc="";
                                estado = 0;
                                break;
                            default:
                                break;
                        }
                        break;
                }
            }
            //Verificacion si separa la Expresion Regular  es correcta 
            agregar("#", "Aceptacion");
            new Arbol(Caracteres, index,follow,nombres);
            index++;
            follow++;
            Caracteres.clear();
        }

    }
}

