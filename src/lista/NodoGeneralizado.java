/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

/**
 *
 * @author camilo
 */
public class NodoGeneralizado {
    private NodoGeneralizado siguiente;
    private boolean sw;
    private Object dato;
    
    public NodoGeneralizado(char d){
        dato = d;
        sw = false;
    }
    
    public NodoGeneralizado(){
        sw = false;
    }
    
    public void asignaDato(Object d){
        dato = d;
    }
    
    public void asignaSW(boolean s){
        sw = s;
    }
    
    public void asignaSiguiente(NodoGeneralizado sig){
        siguiente = sig;
    }
    
    public Object retorneDato(){
        return dato;
    }
    
    public boolean retorneSW(){
        return sw;
    }
    
    public NodoGeneralizado retorneLiga(){
        return siguiente;
    }
}
