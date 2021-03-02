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
public class Lista_Follow {

    private String Hoja;
    private int Numero;
    private String Follow;

    public Lista_Follow(String Hoja, int Numero, String Follow) {
        this.Hoja = Hoja;
        this.Numero = Numero;
        this.Follow = Follow;
    }

    public String getHoja() {
        return Hoja;
    }

    public int getNumero() {
        return Numero;
    }

    public String getFollow() {
        return Follow;
    }

    public void setFollow(String Follo) {
        this.Follow = Follo;
    }   
}
