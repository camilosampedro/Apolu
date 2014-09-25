/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import java.io.Serializable;

/**
 *
 * @author camilo
 */
public class NodoSimple implements Serializable{
    NodoSimple liga;
    Object dato;
    
    public NodoSimple(Object d){
        liga = null;
        dato = d;
    }
    
    public void asignaLiga(NodoSimple x){
        liga = x;
    }
    
    public void asignaDato(Object d){
        dato = d;
    }
    
    public Object retornaDato(){
        return dato;
    }
    
    public NodoSimple retornaLiga(){
        return liga;
    }
}
