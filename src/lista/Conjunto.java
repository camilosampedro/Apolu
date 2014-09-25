/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import java.io.Serializable;

/**
 * Estructura conjunto. Estructura basada en una lista simplemente ligada, de
 * tamaño dinámico. Cada elemento es único dentro del conjunto.
 *
 * @author Camilo Sampedro, Sergio Loaiza
 */
public class Conjunto extends ListaSimple implements Serializable {

    /**
     * Constructor vacío de un conjunto, inicializa el conjunto vacío.
     */
    public Conjunto() {
        super();
    }

    /**
     * Agrega al final del conjunto un elemento nuevo. Se verifica unicidad, si
     * ya existe el elemento, no se inserta.
     *
     * @param nuevo Objeto a insertar en el conjunto.
     */
    @Override
    public void agregar(Object nuevo) {
        NodoSimple insertar = new NodoSimple(nuevo);

        //Si es vacía, se inserta como elemento único.
        if (esVacia()) {
            primero = ultimo = insertar;
            tamaño++;
            return;
        }

        //Se verifica que no exista en el conjunto.
        NodoSimple p = primero;
        while (p != null) {
            if (nuevo.equals(p.retornaDato())) {
                return;
            }
            p = p.retornaLiga();
        }

        //Se inserta el nuevo nodo al final.
        ultimo.asignaLiga(insertar);
        ultimo = insertar;
        tamaño++;
    }

    /**
     * Hace el proceso de unión con otro conjunto. Une los elementos del
     * conjunto actual con los elementos de otro conjunto, conservando la
     * unicidad en el nuevo conjunto.
     *
     * @param segundoConjunto Conjunto con el que se unirá.
     * @return Conjunto nuevo con ambos conjuntos unidos.
     */
    public Conjunto unir(Conjunto segundoConjunto) {
        modificaciones = 0;
        NodoSimple p = segundoConjunto.getPrimero();
        Conjunto nuevoConjunto = duplicar();
        Object dato;

        //Se verifica que se cumpla la condición de que si existe en el segundo 
        //conjunto, no se tome en cuenta en el nuevo.
        while (p != null) {
            dato = (Object) p.retornaDato();
            if (!existe(dato)) {
                nuevoConjunto.agregar(dato);
                modificaciones = modificaciones + 1;
            }
            p = p.retornaLiga();
        }

        //Se le asigna el número de modificaciones que se hicieron al nuevo 
        //conjunto.
        nuevoConjunto.setModificaciones(modificaciones);
        return nuevoConjunto;
    }

    /**
     * Crea una réplica exacta del conjunto actual. La réplica contendrá los
     * mismos valores del conjunto actual, pero con direcciones diferentes, con
     * el fin de poder hacer operaciones en el conjunto nuevo sin alterar el
     * conjunto actual.
     *
     * @return Conjunto exactamente igual al conjunto actual.
     */
    @Override
    public Conjunto duplicar() {
        Conjunto nuevo = new Conjunto();
        NodoSimple nodoActual = primero;
        Object dato;
        while (nodoActual != null) {
            dato = (Object) nodoActual.retornaDato();
            nuevo.agregar(dato);
            nodoActual = nodoActual.retornaLiga();
        }
        return nuevo;
    }

    /**
     * Verifica si un conjunto es igual a otro. Para ser iguales dos conjuntos,
     * deben tener el mismo tamaño y todos sus elementos deben de ser iguales.
     * No se tiene en cuenta el orden.
     *
     * @param otroConjunto Conjunto con el cuál verificar.
     * @return true, si todos los elementos son iguales, false en caso
     * contrario.
     */
    public boolean equals(Conjunto otroConjunto) {
        //Si tienen tamaños diferentes, no pueden ser iguales.
        if (tamaño != otroConjunto.tamaño) {
            return false;
        }

        //Si se da el caso en que uno de los elementos del primero no existe en
        //el segundo, no son iguales. Téngase en cuenta la unicidad de los 
        //elementos.
        NodoSimple nodoActual = primero;
        while (nodoActual != null) {
            if (!otroConjunto.existe(nodoActual.retornaDato())) {
                return false;
            }
            nodoActual = nodoActual.retornaLiga();
        }
        return true;
    }

    /**
     * Convierte el conjunto a una hilera String.
     * @return Cadena de todos los elementos.
     */
    @Override
    public String toString() {
        String cadena = "";
        for (NodoSimple p = primero; p != null; p = p.retornaLiga()) {
            cadena = cadena + p.retornaDato().toString();
        }
        return cadena;
    }
    
    public boolean esDisjunto(Conjunto B) {
        NodoSimple p;
        for (p = getPrimero(); p != null; p = p.retornaLiga()) {
            if (B.existe(p.retornaDato())) {
                return false;
            }
        }
        return true;
    }
}
