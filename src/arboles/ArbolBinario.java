/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;
import lista.NodoDobleAVL;
/**
 *
 * @author camilo
 */
public class ArbolBinario {

    private NodoDobleAVL raiz;
    public static final int INORDEN = 0;
    public static final int PREORDEN = 1;
    public static final int POSORDEN = 2;

    public ArbolBinario() {
        raiz = null;
    }

    public boolean esVacio() {
        return (raiz == null);
    }

    public void agregar(double d) {
        NodoDobleAVL n = new NodoDobleAVL(d);

        /*
         * Si es vacía se puede proceder a hacer el nodo como su único miembro,
         * la raíz.
         * 
         */
        if (esVacio()) {
            System.out.println("No existían más nodos.");
            raiz = n;
            return;
        }
        NodoDobleAVL p = raiz;
        NodoDobleAVL q = null;


        /*
         * Aquí se buscará el lugar donde se ubicará en nuevo nodo y la
         * dirección de su padre (q).
         */
        while (p != null) {
            if ((double) p.retornaDato() == d) {
                System.out.println("Dato ya existe");
                return;
            }
            q = p;
            if (d < (double) p.retornaDato()) {
                p = p.retornaLI();
            } else {
                p = p.retornaLD();
            }
        }
        /*
         * Se determina si el lugar donde se insertará es a la izquierda o a la
         * derecha del nodo p y se procede con la inserción.
         * 
         */
        if (d > (double) q.retornaDato()) {
            q.asignaLD(n);
            System.out.println(n.retornaDato() + "Agregado a la derecha de " + q.retornaDato());
        } else {
            q.asignaLI(n);
            System.out.println(n.retornaDato() + "Agregado a la izquierda de " + q.retornaDato());
        }

    }

    public void mostrar(int opcion) {
        System.out.println();
        if (opcion == INORDEN) {
            System.out.println("INORDEN:");
            mostrarInorden(raiz);
        }
        if (opcion == PREORDEN) {
            System.out.println("PREORDEN:");
            mostrarPreorden(raiz);
        }
        if (opcion == POSORDEN) {
            System.out.println("POSORDEN:");
            mostrarPosorden(raiz);
        }
    }

    private void mostrarInorden(NodoDobleAVL x) {
        if (x != null) {
            mostrarInorden(x.retornaLI());
            System.out.println(x.retornaDato());
            mostrarInorden(x.retornaLD());
        }
    }

    private void mostrarPreorden(NodoDobleAVL x) {
        if (x == null) {
            return;
        }
        System.out.println(x.retornaDato());
        mostrarPreorden(x.retornaLI());
        mostrarPreorden(x.retornaLD());
    }

    private void mostrarPosorden(NodoDobleAVL x) {
        if (x == null) {
            return;
        }
        mostrarPosorden(x.retornaLI());
        mostrarPosorden(x.retornaLD());
        System.out.println(x.retornaDato());
    }

    public int hojas() {
        if (esVacio()) {
            return 0;
        }
        return (hojasPorNodo(raiz));
    }

    private int hojasPorNodo(NodoDobleAVL x) {
        if (x == null) {
            return 0;
        }
        if (x.retornaLI() == null && x.retornaLD() == null) {
            return 1;
        }
        int izquierda = hojasPorNodo(x.retornaLI());
        int derecha = hojasPorNodo(x.retornaLD());
        return (izquierda + derecha);
    }

    public int grado() {
        if (esVacio()) {
            return 0;
        }
        return (gradoConRaiz(raiz));
    }

    private int gradoConRaiz(NodoDobleAVL x) {
        if (x == null) {
            return 0;
        }
        int g = x.grado();
        int gmax = g;
        g = gradoConRaiz(x.retornaLD());
        if (g > gmax) {
            gmax = g;
        }
        g = gradoConRaiz(x.retornaLI());
        if (g > gmax) {
            gmax = g;
        }
        if (gmax == 2) {
            return 2;
        }
        return gmax;
    }

    public int altura() {
        return alturaPorNodo(raiz);
    }

    private int alturaPorNodo(NodoDobleAVL x) {
        if (x == null) {
            return 0;
        }
        int izquierda = alturaPorNodo(x.retornaLI());
        int derecha = alturaPorNodo(x.retornaLD());
        if (izquierda > derecha){
            return (izquierda+1);
        }
        return (derecha+1);
    }
}
