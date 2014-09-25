/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import lista.NodoGeneralizado;
import java.util.Stack;

/**
 *
 * @author camilo
 */
public class ArbolGeneral {

    private NodoGeneralizado primero, ultimo;

    public boolean esVacia() {
        return (primero == null);
    }

    public void construirConString(String s) {
        int i, n;
        Stack pila = new Stack();
        NodoGeneralizado x;
        primero = new NodoGeneralizado();
        ultimo = primero;
        primero.asignaDato(s.charAt(1));
        System.out.println("Primero: " + primero.retorneDato());
        n = s.length();
        if (n == 3) {
            System.out.println("Árbol de solo la raíz");
            return;
        }
        i = 3;
        while (i <= n - 3) {
            System.out.println("\n");
            x = new NodoGeneralizado();
            ultimo.asignaSiguiente(x);
            ultimo = x;
            if (s.charAt(i + 1) == '(') {
                ultimo.asignaSW(true);
                pila.push(ultimo);
                x = new NodoGeneralizado(s.charAt(i));
                ultimo.asignaDato(x);
                System.out.println("Se agregó: " + x.retorneDato());
                ultimo = x;
                i = i + 2;
            } else {
                ultimo.asignaDato(s.charAt(i));
                System.out.println("Se inserta en último: " + s.charAt(i));
                if (s.charAt(i + 1) == ')') {
                    i = i + 1;
                    while (i < n && s.charAt(i) == ')' && !pila.isEmpty()) {
                        ultimo = (NodoGeneralizado) pila.pop();
                        System.out.println("Se insertó en último el nodo con dato : " + ultimo.retorneDato());
                        i = i + 1;
                    }
                    if (s.charAt(i) == ',') {
                        i = i + 1;
                    }
                } else {
                    i = i + 2;
                }
            }
        }
    }

    public void mostrar() {
        if (esVacia()) {
            return;
        }
        mostrarPorNodo(primero);
    }

    private void mostrarPorNodo(NodoGeneralizado x) {
        if (x == null) {
            System.out.print(")");
            return;
        }
        if (x.retorneSW()) {
            System.out.print("->");
            mostrarPorNodo((NodoGeneralizado) x.retorneDato());
        } else {
            System.out.print((Character) x.retorneDato());
        }
        mostrarPorNodo(x.retorneLiga());
    }

    public int hojas() {
        int hojas = 0;
        hojas = hojasPorNodo(primero);
        return hojas;
    }

    public int hojasPorNodo(NodoGeneralizado r) {
        int hojas = 0;
        if (r == null) {
            return hojas;
        }
        r = r.retorneLiga();
        while (r != null) {
            if (r.retorneSW()) {
                hojas = hojas + hojasPorNodo((NodoGeneralizado) r.retorneDato());
            } else {
                hojas = hojas + 1;
            }
            r = r.retorneLiga();
        }
        return hojas;
    }

    double padre(double dato) {
        if (esVacia()) {
            return Double.MAX_VALUE;
        }
        if (dato == (Double) primero.retorneDato()) {
            return Double.MAX_VALUE;
        }
        return (padrePorSublista(primero, dato));
    }

    double padrePorSublista(NodoGeneralizado raiz, double dato) {
        NodoGeneralizado p = raiz.retorneLiga();
        while (p != null) {
            if (p.retorneSW()) {
                NodoGeneralizado q = (NodoGeneralizado) p.retorneDato();
                if (dato == (double) q.retorneDato()) {
                    return (Double) raiz.retorneDato();
                } else {
                    double r = padrePorSublista(q, dato);
                    if (r != 0) {
                        return (r);
                    }
                }
            } else {
                if ((double) p.retorneDato() == dato) {
                    return (Double) raiz.retorneDato();
                }
            }
            p = p.retorneLiga();
        }
        return 0;
    }
}
