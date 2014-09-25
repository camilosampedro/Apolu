/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pilasYColas;

import lista.ListaSimple;

/**
 *
 * @author camilo
 */
public class Pila extends ListaSimple {
    public void apilar(Object o) {
        agregar(o);
    }

    public Object desapilar() {
        Object o = ultimo.retornaDato();
        eliminar(o);
        return o;
    }

    public Object extraer() {
        return ultimo.retornaDato();
    }
}
