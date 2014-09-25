/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import lista.NodoMultilista;
import matricesDispersas.Tripleta;
import matricesDispersas.MatrizEnTripleta;
import pilasYColas.Cola;

/**
 *
 * @author camilo
 */
public class GrafoEnMultilistasDeAdyacencia {

    private int n;
    private int[] visitado;
    NodoMultilista vector[];
    private Cola cola;

    /**
     * Convierte de la forma de arreglo de la matriz de adyacencia a la forma de
     * multilistas de adyacencia. Convierte solamente los elementos que sean 0
     * dentro de los arreglos.
     *
     * @param adyacencia Grafo representado en matriz de adyacencia en arreglo.
     */
    public void construirDesdeMatrizDeAdyacencia(int[][] adyacencia) {
        int i, j;
        NodoMultilista x;
        for (i = 0; i < adyacencia.length; i++) {
            for (j = 0; j < adyacencia[i].length; j++) {
                if (adyacencia[i][j] == 1) {
                    x = new NodoMultilista(i, j);
                    x.asignaLVi(vector[i]);
                    vector[i] = x;
                    x.asignaLVj(vector[j]);
                    vector[j] = x;
                }
            }
        }
    }

    public void construirDesdeMatrizDeIncidencia(int[][] incidencia) {
        int i, j, k;
        NodoMultilista x;
        for (i = 0; i < incidencia[0].length; i++) {
            j = 0;
            while (incidencia[j][i] == 0) {
                j = j + 1;
            }
            k = j + 1;
            while (incidencia[k][i] == 0) {
                k = k + 1;
            }
            x = new NodoMultilista(j, k);
            x.asignaLVi(vector[j]);
            vector[j] = x;
            x.asignaLVj(vector[k]);
            vector[k] = x;
        }
    }

    public void construirDesdeMatrizDeIncidenciaEnTripletas(MatrizEnTripleta incidencia) {
        int i, n, f, c;
        NodoMultilista x;
        MatrizEnTripleta aux;
        aux = incidencia.traspuesta();
        Tripleta t;
        n = aux.numeroTripletas();
        i = 0;
        while (i < n) {
            t = (Tripleta) aux.retornaTripleta(i);
            f = t.retornaColumna();
            i = i + 1;
            t = (Tripleta) aux.retornaTripleta(i);
            c = t.retornaColumna();
            x = new NodoMultilista(f, c);
            x.asignaLVi(vector[f]);
            vector[f] = x;
            x.asignaLVj(vector[c]);
            vector[c] = x;
            i = i + 1;
        }
    }

    public void mostrarDFS() {
        visitado = new int[vector.length];
        mostrarDFSRecursivo(0);
    }

    private void mostrarDFSRecursivo(int v) {
        System.out.println(v);
        visitado[v] = 1;
        NodoMultilista p;
        p = vector[v];
        int i;
        while (p != null) {
            if (p.retornaVi() == v) {
                i = p.retornaVj();
                if (visitado[i] == 0) {
                    mostrarDFSRecursivo(i);
                }
                p = p.retornaLVi();
            } else {
                i = p.retornaVi();
                if (visitado[i] == 0) {
                    mostrarDFSRecursivo(i);
                }
                p = p.retornaLVj();
            }
        }
    }

    public void mostrarBFS() {
        cola = new Cola();
        visitado = new int[vector.length];
        mostrarBFSCompleto(0);
    }

    private void mostrarBFSCompleto(int v) {
        visitado[v] = 1;
        cola.add(v);
        NodoMultilista p;
        int i;
        while (!cola.isEmpty()) {
            v = (Integer) cola.poll();
            System.out.println(v);
            p = vector[v];
            while (p != null) {
                if (p.retornaVi() == v) {
                    i = p.retornaVj();
                    p = p.retornaLVi();
                } else {
                    i = p.retornaVi();
                    p = p.retornaLVj();
                }
                if (visitado[i] == 0) {
                    visitado[i] = 1;
                    cola.add(i);
                }
            }
        }
    }
}