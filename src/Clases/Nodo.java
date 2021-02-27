/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author diego
 */
public class Nodo {
    public String nombre,rangoa,rangob;
    public String []caracteres = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
                                                            "q","r","s","t","u","v","w","x","y","z"};
    
    public String []CARACTERES = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
                                                            "Q","R","S","T","U","V","W","X","Y","Z"};
    
    public String []numeros = {"0","1","2","3","4","5","6","7","8","9"};

    public ArrayList<String> conjuntos = new ArrayList<>();
    public ArrayList<String> alfabeto = new ArrayList<>();
    public ArrayList<Nodo> nodos = new ArrayList<>();
    
    public void nombre(String nombre){
        this.nombre = nombre;
    }
    
    public void ranga(String transiciones){
        this.rangoa = transiciones;
    }
    
    public void rangb(String transiciones){
        this.rangob = transiciones;
    }
    
    public void conj(String conjunto){
        this.conjuntos.add(conjunto);
    }
    
    public void Alfabeto(){
        int a1 = 0,a2 =0;
        if (this.conjuntos != null){
            for(int i=0; i<this.conjuntos.size(); i++){
                this.alfabeto.add(this.conjuntos.get(i));
            }
        }

        for(int i =0; i<caracteres.length;i++){
            if (caracteres[i].equals(this.rangoa) | CARACTERES[i].equals(this.rangoa)){
                a1 = i;
            }
            if (caracteres[i].equals(this.rangob) | CARACTERES[i].equals(this.rangob)){
                a2 = i;
            }
        }
        
        for(int i = 0; i<numeros.length;i++){
            if(numeros[i].equals(this.rangoa)){
                a1 = i;
            }
            if(numeros[i].equals(this.rangob)){
                a2 = i;
            }
        }
        
        if(caracteres[a1].equals(this.rangoa)){
            for(int b = a1; b<=a2; b++){
                this.alfabeto.add(caracteres[b]);
            }
        }
        
        if(CARACTERES[a1].equals(this.rangoa)){
            for(int b = a1; b<=a2; b++){
                this.alfabeto.add(CARACTERES[b]);
            }
        }
        
        if(numeros[a1].equals(this.rangoa)){
            for(int b = a1; b<=a2; b++){
                this.alfabeto.add(numeros[b]);
            }
        }
        this.rangoa="";
        this.rangob="";
    }
    
    public void RecorrerAlfabeto(){
        System.out.println("Si llegamos ;3");
        System.out.println("El alfabeto es: ");
        for(int i=0;i<alfabeto.size();i++){
            System.out.print(this.alfabeto.get(i)+ " ");
        }
        System.out.println("");
    }
}
