/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import java.io.Serializable;
import java.util.Enumeration;

/**
 *
 * @author CamiloAndrés
 */
public class ListaSimple implements Serializable, Enumeration {

    protected NodoSimple primero, ultimo, actual;
    protected int modificaciones;
    protected int tamaño;

    /**
     * @return Número de elementos dentro de la lista.
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param modificaciones Asigna el valor de modificaciones (Usado para
     * heredarlo de una lista anterior).
     */
    public void setModificaciones(int modificaciones) {
        this.modificaciones = modificaciones;
    }

    /**
     * @return Número de modificaciones hechas en la lista (uniones,
     * intersecciones, ...). Usado en métodos recursivos o ciclos.
     */
    public int getModificaciones() {
        return modificaciones;
    }

    /**
     * @return Primer nodo de la lista, para comenzar a recorrer.
     */
    public NodoSimple getPrimero() {
        return primero;
    }

    /**
     * @return Último nodo de la lista.
     */
    public NodoSimple getUltimo() {
        return ultimo;
    }

    /**
     * Constructor vacío de una lista, inicializa la lista vacía.
     */
    public ListaSimple() {
        primero = ultimo = null;
        tamaño = 0;
    }

    /**
     * @return true si la lista no contiene ningún elemento, false en caso
     * contrario.
     */
    public boolean esVacia() {
        return primero == null;
    }

    /**
     * Agrega al final de la lista un elemento nuevo. Se verifica unicidad, si
     * ya existe el elemento, no se inserta.
     *
     * @param nuevo Objeto a insertar en la lista.
     */
    public void agregar(Object nuevo) {
        NodoSimple insertar = new NodoSimple(nuevo);

        //Si es vacía, se inserta como elemento único.
        if (esVacia()) {
            primero = ultimo = insertar;
            tamaño++;
            return;
        }

        //Se inserta el nuevo nodo al final.
        ultimo.asignaLiga(insertar);
        ultimo = insertar;
        tamaño++;
    }

    /**
     * Crea una réplica exacta de la lista actual. La réplica contendrá los
     * mismos valores de la lista actual, pero con direcciones diferentes, con
     * el fin de poder hacer operaciones en la lista nueva sin alterar la lista
     * actual.
     *
     * @return Lista exactamente igual a la lista actual.
     */
    public ListaSimple duplicar() {
        ListaSimple nuevo = new ListaSimple();
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
     * Verifica si existe el elemento dentro de la lista actual.
     *
     * @param elemento Elemento a verificar.
     * @return true, si existe el objeto en la lista, false en caso contrario.
     */
    public boolean existe(Object elemento) {
        NodoSimple nodoActual = primero;
        Object dato;

        //Se verifica que no haya ningún nodo que tenga como dato el elemento a 
        //verificar.
        while (nodoActual != null) {
            dato = (Object) nodoActual.retornaDato();
            if (dato.equals(elemento)) {
                return true;
            }
            nodoActual = nodoActual.retornaLiga();
        }
        return false;
    }

    /**
     * Verifica si un lista es igual a otro. Para ser iguales dos listas, deben
     * tener el mismo tamaño y todos sus elementos deben de ser iguales. No se
     * tiene en cuenta el orden.
     *
     * @param otraLista Lista con la cuál verificar.
     * @return true, si todos los elementos son iguales, false en caso
     * contrario.
     */
    public boolean equals(ListaSimple otraLista) {
        //Si tienen tamaños diferentes, no pueden ser iguales.
        if (tamaño != otraLista.tamaño) {
            return false;
        }

        //Si se da el caso en que uno de los elementos del primero no existe en
        //el segundo, no son iguales. Téngase en cuenta la unicidad de los 
        //elementos.
        NodoSimple nodoActual = primero;
        while (nodoActual != null) {
            if (!otraLista.existe(nodoActual.retornaDato())) {
                return false;
            }
            nodoActual = nodoActual.retornaLiga();
        }
        return true;
    }

    /**
     * Busca la posición del elemento en la lista.
     *
     * @param elemento Elemento a buscar.
     * @return Indice de posición del elemento.
     */
    public int buscar(Object elemento) {
        NodoSimple p = primero;
        int posicion = 0;
        while (p != null) {
            if (p.retornaDato().equals(elemento)) {
                return posicion;
            }
            posicion = posicion + 1;
            p = p.retornaLiga();
        }
        return -1;
    }

    /**
     * Elimina el elemento de la lista.
     *
     * @param elemento Elemento a eliminar.
     */
    public void eliminar(Object elemento) {
        NodoSimple p = primero;
        NodoSimple q = null;
        while (p != null && !p.retornaDato().equals(elemento)) {
            q = p;
            p = p.retornaLiga();
        }
        if (p != null) {
            if (primero == p) {
                if (ultimo == p) {
                    primero = ultimo = null;

                } else {
                    primero = p.retornaLiga();
                }
            } else {
                q.asignaLiga(p.retornaLiga());
                if (ultimo == p) {
                    ultimo = q;
                }
            }
            tamaño--;
        }
    }

    /**
     * Inserta al inicio de la lista.
     *
     * @param nuevo Objeto a insertar.
     */
    void agregarAlInicio(Object nuevo) {
        NodoSimple insertar = new NodoSimple(nuevo);

        //Si es vacía, se inserta como elemento único.
        if (esVacia()) {
            primero = ultimo = insertar;
            tamaño++;
            return;
        }

        //Se verifica que no exista en la lista.
        NodoSimple p = primero;
        while (p != null) {
            if (nuevo.equals(p.retornaDato())) {
                return;
            }
            p = p.retornaLiga();
        }

        //Se inserta el nuevo nodo al final.
        insertar.asignaLiga(primero);
        primero = insertar;
        tamaño++;
    }

    /**
     * Desconecta el nodo de la lista.
     *
     * @param anterior Nodo inmediatamente anterior al nodo a desconectar.
     * @param desconectar Nodo a desconectar.
     */
    public void desconectarNodo(NodoSimple anterior, NodoSimple desconectar) {
        if (desconectar == null) {
            System.err.println("desconectarNodo(): Nodo ingresado nulo, no se puede desconectar. Se detiene el metodo.");
            return;
        }
        if (desconectar != primero) {
            anterior.asignaLiga(desconectar.retornaLiga());
            if (desconectar == ultimo) {
                ultimo = anterior;
            }
        } else {
            primero = primero.retornaLiga();
            if (primero == null) {
                ultimo = null;
            }
        }
    }

    /**
     * Convierte la lista a una hilera String.
     *
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

    public void comenzarRecorrido() {
        actual = primero;
    }

    @Override
    public boolean hasMoreElements() {
        return actual != null;
    }

    @Override
    public Object nextElement() {
        if (actual == null) {
            actual = primero;
        }
        Object dato = actual.dato;
        actual = actual.liga;
        return dato;
    }
}
