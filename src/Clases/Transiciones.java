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
    public String inicio;
    public String alfabeto;
    public String fin;
    
    public Transiciones(String inicio, String alfabeto, String fin){
        this.inicio = inicio;
        this.alfabeto = alfabeto;
        this.fin = fin;
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
}