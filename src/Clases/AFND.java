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
public class AFND {
    
    private int inicio,fin;
    private String valor,aceptacion;

    public AFND(int inicio, String valor, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.valor = valor;
    }

    public int getInicio() {
        return inicio;
    }
    
    public String getAceptacion(){
        return this.aceptacion;
    }
    
    public void setAceptacion(String aceptacion){
        this.aceptacion = aceptacion;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
