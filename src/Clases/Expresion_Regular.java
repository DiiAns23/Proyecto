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

        //Aqui recorro la linkedlist que contiene la ER 
        for (int i = 0; i < ER.size(); i++) {
            agregar(".", ".");
            er = ER.get(i);
            //Aqui separo la ER de caracter por caracter y lo guardo en una linkedlist de caracteres
            for (int j = 0; j < er.length(); j++) {
                caracter = er.charAt(j);
                switch (estado) {
                    case 0:
                        if (caracter == (char) 46) {
                            agregar(Character.toString(caracter), ".");
                            estado = 0;
                        } else if (caracter == (char) 124) {
                            agregar(Character.toString(caracter), "|");
                            estado = 0;
                        } else if (caracter == (char) 63) {
                            agregar(Character.toString(caracter), "?");
                            estado = 0;
                        } else if (caracter == (char) 42) {
                            agregar(Character.toString(caracter), "*");
                            estado = 0;
                        } else if (caracter == (char) 43) {
                            agregar(Character.toString(caracter), "+");
                            estado = 0;
                        } else if (caracter == (char) 34) {
                            estado = 1;
                        } else if (caracter == (char) 123) {
                            estado = 2;
                        }
                        break;
                    case 1:
                        if (caracter != (char) 34) {
                            cc += caracter;
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

