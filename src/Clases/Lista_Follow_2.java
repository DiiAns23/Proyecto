/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class Lista_Follow_2 {
    private String Hoja;
    private String Numero;
    private LinkedList<Integer> Follow;

    public Lista_Follow_2(String Hoja, String Numero, LinkedList<Integer> Follow) {
        this.Hoja = Hoja;
        this.Numero = Numero;
        this.Follow = Follow;
    }

    public String getHoja() {
        return Hoja;
    }

    public String getNumero() {
        return Numero;
    }

    public LinkedList<Integer> getFollow() {
        return Follow;
    }

    public void setFollow(LinkedList<Integer> Follow) {
        this.Follow = Follow;
    }   
}
