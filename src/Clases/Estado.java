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
public class Estado {

    private String estado;
    private LinkedList<String> lista;
    private String simbolo;

    public Estado(String estado, LinkedList<String> lista) { //{1,2,3} {1,4,5}
        this.estado = estado;
        this.lista = lista;
        this.simbolo = simbolo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LinkedList getLista() {
        return lista;
    }

    public void setLista(LinkedList lista) {
        this.lista = lista;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    

}
