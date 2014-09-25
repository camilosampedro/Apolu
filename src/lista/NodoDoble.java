package lista;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author camilo
 */
public class NodoDoble {
    NodoDoble anterior, siguiente;
    Object dato;
    
    public NodoDoble(Object d){
        anterior = siguiente = null;
        dato = d;
    }
    
    public void asignaLI(NodoDoble x){
        anterior = x;
    }
    
    public void asignaLD(NodoDoble x){
        siguiente = x;
    }
    
    public void asignaDato(Object d){
        dato = d;
    }
    
    public Object retornaDato(){
        return dato;
    }
    
    public NodoDoble retornaLD(){
        return siguiente;
    }
    
    public NodoDoble retornaLI(){
        return anterior;
    }
}
