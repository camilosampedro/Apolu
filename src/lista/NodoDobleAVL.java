/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

/**
 *
 * @author camilo
 */
public class NodoDobleAVL extends NodoDoble {

    private int fb;
    private NodoDobleAVL siguiente, anterior;

    public NodoDobleAVL(Object d) {
        super(d);
        this.fb = 0;
    }

    public NodoDobleAVL retornaLD() {
        return siguiente;
    }

    public NodoDobleAVL retornaLI() {
        return anterior;
    }

    public void asignaLI(NodoDobleAVL x) {
        anterior = x;
    }

    public void asignaLD(NodoDobleAVL x) {
        siguiente = x;
    }
    
    public int grado() {
        int g = 0;
        if (siguiente != null) {
            g = g + 1;
        }
        if (anterior != null) {
            g = g + 1;
        }
        return g;
    }
    
    public void asignaFB(int f){
        fb = f;
    }
    
    public int retornaFB(){
        return fb;
    }
}
