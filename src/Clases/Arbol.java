/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author diego
 */

public class Arbol {
    //Clase del nodo
    class Nodo {
        public String etiquetas;
        public Nodo derecha;
        public Nodo izquierda;
        public String descripcion;
        public String Anulable;
        public String Primeros;
        public String Ultimos;

        public Nodo(String nombre, String descrip, String anulable, String P, String U) {

            this.etiquetas = nombre;
            this.descripcion = descrip;
            this.Anulable = anulable;
            this.Primeros = P;
            this.Ultimos = U;
            this.derecha = null;
            this.izquierda = null;
        }

        public String getEtiquetas() {
            return etiquetas;
        }

        public String getDescripcion() {
            return descripcion;
        }

    }

    // Clase del arbol
    public Nodo raiz = null;
    public static ArrayList<Lista_ER> cara;
    public static ArrayList<Lista_Follow> Siguientes = new ArrayList<>();
    public static ArrayList<Estado> Tabla_Tansicion = new ArrayList<>();
    public static int punterodelista;
    public static String dot;
    public int index=0, primero = 1, ultimo = 1, indez;

    public Arbol(ArrayList<Lista_ER> Caracteres, int inde, int folow, ArrayList<String> nombres) throws IOException, InterruptedException {

        this.cara = Caracteres;
        this.index = inde;
        this.indez = folow;
        raiz = Agregar();
        ArbolER(nombres);
        Follow(raiz);
        Tabla_Follow generar = new Tabla_Follow();
        generar.TablaFollow(Siguientes, nombres.get(indez));
        estadoinicial();
        Tabla_Transicion graficar = new Tabla_Transicion();
        graficar.Tabla(transiciones, encabezado,nombres.get(indez));
        Generar_AFD afd = new Generar_AFD();
        afd.AFD(nombres.get(indez), transiciones);
        Siguientes.clear();
        punterodelista = 0;
    }

    Nodo Agregar() {
        switch (cara.get(punterodelista).getDescripcion()) {
            //Si es una Concatenacion
            case ".":
                Nodo concatenacion = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", "0", "0");
                punterodelista++;
                Nodo conca_izquierda = Agregar();
                Nodo conca_derecha = Agregar();
                concatenacion.izquierda = conca_izquierda;
                concatenacion.derecha = conca_derecha;

                //Anulables
                if ("A".equals(conca_izquierda.Anulable) && "A".equals(conca_derecha.Anulable)) {
                    concatenacion.Anulable = "A";
                } else if ("A".equals(conca_izquierda.Anulable) && "N".equals(conca_derecha.Anulable)) {
                    concatenacion.Anulable = "N";
                } else if ("N".equals(conca_izquierda.Anulable) && "A".equals(conca_derecha.Anulable)) {
                    concatenacion.Anulable = "N";
                } else if ("N".equals(conca_izquierda.Anulable) && "N".equals(conca_derecha.Anulable)) {
                    concatenacion.Anulable = "N";
                }

                //Primeros de concatenacion
                if ("A".equals(conca_izquierda.Anulable)) {
                    concatenacion.Primeros = conca_izquierda.Primeros + "," + conca_derecha.Primeros;
                } else {
                    concatenacion.Primeros = conca_izquierda.Primeros;
                }

                //Ultimos de concatenacion
                if ("A".equals(conca_derecha.Anulable)) {
                    concatenacion.Ultimos = conca_izquierda.Ultimos + "," + conca_derecha.Ultimos;
                } else {
                    concatenacion.Ultimos = conca_derecha.Ultimos;
                }
                return concatenacion;
            //Si es un Or    
            case "|":
                Nodo or = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", "0", "0");
                punterodelista++;
                Nodo or_izquierda = Agregar();
                Nodo or_derecha = Agregar();
                or.izquierda = or_izquierda;
                or.derecha = or_derecha;

                //Anulables
                if ("N".equals(or_izquierda.Anulable) && "N".equals(or_derecha.Anulable)) {
                    or.Anulable = "N";
                } else if ("A".equals(or_izquierda.Anulable) && "N".equals(or_derecha.Anulable)) {
                    or.Anulable = "Anulable";
                } else if ("N".equals(or_izquierda.Anulable) && "A".equals(or_derecha.Anulable)) {
                    or.Anulable = "Anulable";
                } else if ("A".equals(or_izquierda.Anulable) && "A".equals(or_derecha.Anulable)) {
                    or.Anulable = "A";
                }
                //Primeros
                or.Primeros = or_izquierda.Primeros + "," + or_derecha.Primeros;
                //Ultimos
                or.Ultimos = or_izquierda.Ultimos + "," + or_derecha.Ultimos;
                return or;
            //Si es una cerradura de Kleen    
            case "*":
                Nodo kleen = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", "0", "0");
                punterodelista++;
                Nodo kleen_izquierda = Agregar();
                kleen.izquierda = kleen_izquierda;
                if ("N".equals(kleen_izquierda.Anulable)) {
                    kleen.Anulable = "A";
                } else if ("A".equals(kleen_izquierda.Anulable)) {
                    kleen.Anulable = "A";
                }
                //Primeros
                kleen.Primeros = kleen_izquierda.Primeros;
                //Ultimos
                kleen.Ultimos = kleen_izquierda.Ultimos;
                return kleen;
            //Si es una cerradura Positiva
            case "+":
                Nodo positiva = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", "0", "0");
                punterodelista++;
                Nodo positiva_izquierda = Agregar();
                positiva.izquierda = positiva_izquierda;
                if ("A".equals(positiva_izquierda.Anulable)) {
                    positiva.Anulable = "N";
                } else if ("N".equals(positiva_izquierda.Anulable)) {
                    positiva.Anulable = "N";
                }
                //Primeros
                positiva.Primeros = positiva_izquierda.Primeros;
                //Ultimos
                positiva.Ultimos = positiva_izquierda.Ultimos;
                return positiva;
            //Si es una cerradura de Aparicion
            case "?":
                Nodo aparicion = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", "0", "0");
                punterodelista++;
                Nodo aparicion_izquierda = Agregar();
                aparicion.izquierda = aparicion_izquierda;
                if ("N".equals(aparicion_izquierda.Anulable)) {
                    aparicion.Anulable = "A";
                } else if ("A".equals(aparicion_izquierda.Anulable)) {
                    aparicion.Anulable = "A";
                }
                //Primeros
                aparicion.Primeros = aparicion_izquierda.Primeros;
                //Ultimos
                aparicion.Ultimos = aparicion_izquierda.Ultimos;
                return aparicion;
            //Si es una Cadena se trata como Hoja    
            case "cadena":
                Nodo cadena = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", String.valueOf(primero), String.valueOf(ultimo));
                agregar_follow(cara.get(punterodelista).getEtiqueta(), primero, "");
                punterodelista++;
                primero++;
                ultimo++;
                return cadena;

            //Si es un Id se trata como Hoja    
            case "identificador":
                Nodo id = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", String.valueOf(primero), String.valueOf(ultimo));
                agregar_follow(cara.get(punterodelista).getEtiqueta(), primero, "");
                punterodelista++;
                primero++;
                ultimo++;
                return id;

            //Si es un Id se trata como Hoja    
            case "Aceptacion":
                Nodo aceptar = new Nodo(cara.get(punterodelista).getEtiqueta(), cara.get(punterodelista).getDescripcion(), "N", String.valueOf(primero), String.valueOf(ultimo));
                agregar_follow(cara.get(punterodelista).getEtiqueta(), primero, "");
                punterodelista++;
                primero++;
                ultimo++;
                return aceptar;
            default:
                return null;
        }
    }

    public void ArbolER(ArrayList<String> nombres) throws IOException, InterruptedException {
        dot = "digraph g{\n"
                + "node [shape = record, heigth=.1];\n";
        Recorrer_Arbol(raiz);
        dot = dot + "}";
        Generar_Arbol_ER generar = new Generar_Arbol_ER();
        generar.Crear(nombres.get(index), dot);
        index++;
    }

    public void Recorrer_Arbol(Nodo temporal) {
        if (temporal != null) {
            Recorrer_Arbol(temporal.izquierda);
            if (temporal.etiquetas.equals("|") || temporal.etiquetas.equals(">") || temporal.etiquetas.equals("{") || temporal.etiquetas.equals("}")) {
                dot = dot + "\"" + temporal.toString() + "\"" + "[label = \"P: " + temporal.Primeros + "|{" + temporal.Anulable + " |\\" + temporal.etiquetas + "}|U:" + temporal.Ultimos + " \"];\n";
            } else {
                dot = dot + "\"" + temporal.toString() + "\"" + "[label = \"P: " + temporal.Primeros + " |{" + temporal.Anulable + " |" + temporal.etiquetas + "}|U:" + temporal.Ultimos + " \"];\n";
            }
            if (temporal.derecha != null) {
                dot = dot + "\"" + temporal.toString() + "\"" + "->" + "\"" + temporal.derecha.toString() + "\";\n";
            }
            if (temporal.izquierda != null) {
                dot = dot + "\"" + temporal.toString() + "\"" + "->" + "\"" + temporal.izquierda.toString() + "\";\n";
            }
            Recorrer_Arbol(temporal.derecha);
        }
    }

    //Tabla Follows
    public static LinkedList<String> f = new LinkedList();

    public void Follow(Nodo raiz) {
        if (raiz != null) {
            Follow(raiz.izquierda);
            switch (raiz.descripcion) {
                case "+":
                    String primero_positivo = raiz.Primeros;
                    String ultimo_positivo = raiz.Ultimos;
                    recorer(ultimo_positivo, primero_positivo);
                    break;

                case "*":
                    String primero_kleen = raiz.Primeros;
                    String ultimo_kleen = raiz.Ultimos;
                    recorer(ultimo_kleen, primero_kleen);
                    break;

                case ".":
                    String ultimo_conca = raiz.izquierda.Ultimos;
                    String primero_conca = raiz.derecha.Primeros;
                    recorer(ultimo_conca, primero_conca);
                    break;
                default:
                    break;
            }
            Follow(raiz.derecha);
        }
    }

    public void recorer(String primer, String ult) {
        String separar = primer;
        char caracter = ' ';
        for (int i = 0; i < separar.length(); i++) {
            caracter = separar.charAt(i);
            if (Character.isDigit(caracter)) {
                f.add(Character.toString(caracter));
            }
        }
        String a;
        for (int vector = 0; vector < f.size(); vector++) {
            for (int i = 0; i < Siguientes.size(); i++) {
                if (Siguientes.get(i).getNumero() == Integer.parseInt(f.get(vector))) {
                    if (Siguientes.get(i).getFollow().equals("")) {
                        a = ult;
                        Siguientes.get(i).setFollow(a);
                        a = " ";
                    } else {
                        a = Siguientes.get(i).getFollow() + "," + ult;
                        Siguientes.get(i).setFollow(a);
                        a = " ";
                    }
                    break;
                }
            }
        }
        f.clear();
    }

    public void agregar_follow(String hoja, int Numero, String sus_siguientes) {
        Lista_Follow add = new Lista_Follow(hoja, Numero, sus_siguientes);
        Siguientes.add(add);
    }

    //Tabla de Transiciones
    public LinkedList<String> encabezado = new LinkedList();
    public ArrayList<Lista_Follow_2> aux = new ArrayList();
    public LinkedList<Estado> estados = new LinkedList();
    public LinkedList<Transiciones> transiciones = new LinkedList();
    public LinkedList<String> usados = new LinkedList();
    public static int num_estado = 1;
    
    
    public void estadoinicial() {
        Encabezado();
    }
    
        
    public void Encabezado() {
        Lista_Follow_2 n;
        for (int follow = 0; follow <Siguientes.size(); follow++) {
            String hoja = Siguientes.get(follow).getHoja();
            String numero = Siguientes.get(follow).getNumero()+"";
            LinkedList<Integer> sig = new LinkedList();
            String []sigs =  Siguientes.get(follow).getFollow().split(",");
            for (String sig1 : sigs) {
                if (!sig1.equals("")) {
                    sig.add(Integer.parseInt(sig1));
                }
            }
            n = new Lista_Follow_2(hoja, numero, sig);
            aux.add(n);
            if(!encabezado.contains(Siguientes.get(follow).getHoja())){
                encabezado.add(Siguientes.get(follow).getHoja());
            }   
        }
        Siguientes.clear();
        int a = encabezado.size();
        encabezado.remove(a-1);
        String []b = raiz.Primeros.split(",");
        LinkedList<Integer> sigs = new LinkedList();
        for (String b1 : b) {
            sigs.add(Integer.parseInt(b1));
        }
        estados.add(new Estado("S0", sigs));
        Estados("S0",sigs);
        aux.clear();
        num_estado = 1;
    }
    
    public void Estados(String nombre, LinkedList<Integer> siguientes){
        for (String encabezado1 : encabezado) {  
            LinkedList<Integer> auxiliar1 = new LinkedList();
            for(int siguientes1 : siguientes){
                Lista_Follow_2 sig = aux.get(siguientes1-1);
                if(encabezado1.equals(sig.getHoja())){
                    for(int i: sig.getFollow()){
                        if(!auxiliar1.contains(i)){
                            auxiliar1.add(i);
                        }
                    }
                }
            }
            
            if(!auxiliar1.isEmpty()){
                if(!usados.contains(auxiliar1.toString())){
                    String nombre2 = "S"+num_estado;
                    estados.add(new Estado(nombre2,auxiliar1));
                    transiciones.add(new Transiciones(nombre, encabezado1,nombre2));
                    num_estado++;
                    usados.add(auxiliar1.toString());
                    Estados(nombre2,auxiliar1);
                }else{
                    for(Estado x: estados){
                        if(x.getSiguientes().equals(auxiliar1)){
                            transiciones.add(new Transiciones(nombre,encabezado1, x.getEstado()));
                        }
                    }
                }
            }
        }
    }     
}
