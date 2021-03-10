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
public class Transiciones {
    private String inicio;
    private String alfabeto;
    private String fin;
    private String aceptacion;
    
    public Transiciones(String inicio, String alfabeto, String fin, String aceptacion){
        this.inicio = inicio;
        this.alfabeto = alfabeto;
        this.fin = fin;
        this.aceptacion = aceptacion;
    }
    
    public String getInicio(){
        return this.inicio;
    }
    
    public String getAlfabeto(){
        return this.alfabeto;
    }
    
    public String getFin(){
        return this.fin;
    }
    
    public String getAceptacion(){
        return this.aceptacion;
    }
}
