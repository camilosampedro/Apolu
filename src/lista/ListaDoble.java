/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

/**
 *
 * Falta implementar muchos métodos!!!!!
 *
 * @author camilo
 */
public class ListaDoble {

    private NodoDoble primero, ultimo;
    private int numeroNodos;

    public ListaDoble() {
        primero = ultimo = null;
        numeroNodos = 0;
    }

    public void asignaPrimero(NodoDoble nodoPrimero) {
        primero = nodoPrimero;
    }

    public void asignaUltimo(NodoDoble nodoUltimo) {
        ultimo = nodoUltimo;
    }

    public NodoDoble retornaPrimero() {
        return primero;
    }

    public NodoDoble retornaUltimo() {
        return ultimo;
    }

    public int numeroNodos() {
        return numeroNodos;
    }

    public void asignaNumeroNodos(int numeroDeNodos) {
        numeroNodos = numeroDeNodos;
    }

    public boolean finRecorrido(NodoDoble p) {
        return p == null;
    }

    public boolean esVacia() {
        return primero == null;
    }

    public NodoDoble buscaDondeInsertar(Object d) {
        NodoDoble p, y;
        double d1;
        int d2;
        p = retornaPrimero();
        y = p.retornaLI();
        if (d instanceof Double) {
            d1 = (Double) p.retornaDato();
            while (!finRecorrido(p) && (Double) p.retornaDato() < d1) {
                y = p;
                p = p.retornaLD();
            }
        }
        if (d instanceof Integer) {
            d2 = (Integer) p.retornaDato();
            while (!finRecorrido(p) && (Double) p.retornaDato() < d2) {
                y = p;
                p = p.retornaLD();
            }
        }
        return y;
    }

    public void insertar(Object d, NodoDoble y) {
    }

    public void insertarAlFinal(Object d) {
        NodoDoble x = new NodoDoble(d);
        if(esVacia()){
            primero = ultimo = x;
        }
        ultimo.asignaLD(x);
        x.asignaLI(ultimo);
        ultimo = x;
        numeroNodos = numeroNodos + 1;
    }

    public void conectar(NodoDoble x, NodoDoble y) {
    }

    public NodoDoble buscar(Object d) {
        if (!esVacia()) {
            NodoDoble p = primero;
            while (!finRecorrido(p)) {
                if (p.retornaDato() == d) {
                    return p;
                }
                p = p.retornaLD();
            }
        }
        return null;
    }

    public NodoDoble nodoEn(int indice) {
        if (indice > numeroNodos) {
            return null;
        }
        if (!esVacia()) {
            NodoDoble p = primero;
            for (; indice > 0; indice--) {
                p = p.retornaLD();
            }
            return p;
        }
        return null;
    }

    public void borrar(Object d) {
    }

    public void desconectar(NodoDoble x) {
    }
}
