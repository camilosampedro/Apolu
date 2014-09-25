/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import lista.NodoSimple;
import lista.NodoDoble;
import matricesDispersas.Tripleta;
import matricesDispersas.MatrizForma1;
import matricesDispersas.MatrizForma2;
import matricesDispersas.MatrizEnTripleta;
import pilasYColas.Cola;

/**
 *
 * @author camilo
 */
public class GrafoEnListaDeAdyacencia {

    NodoSimple[] vector;
    private int[] visitado;
    Cola cola;

    public GrafoEnListaDeAdyacencia(int numeroVertices) {
        vector = new NodoSimple[numeroVertices + 1];
    }

    public void agregarLado(int vertice1, int vertice2) {
        NodoSimple x = new NodoSimple(vertice2);
        x.asignaLiga(vector[vertice1]);
        vector[vertice1 - 1] = x;
        System.out.println("Inserta en " + vertice1 + " con " + vertice2);
    }

    public int grado(int vertice) {
        NodoSimple x = vector[vertice];
        int grado = 0;
        while (x != null) {
            grado = grado + 1;
            x = x.retornaLiga();
        }
        return grado;
    }

    /**
     * Muestra todos los elementos del grafo por consola.
     */
    public void mostrarPorConsola() {
        for (int i = 1; i < vector.length; i++) {
            NodoSimple x = vector[i];
            while (x != null) {
                System.out.println("Existe lado (" + i + "," + (Integer) x.retornaDato() + ")");
            }
            System.out.println("Llega a null");
        }
    }

    /**
     * Convierte de la representación en matriz primitiva a la representación en
     * listas. Convierte todo un grafo representado en matriz primitiva en
     * matriz de adyacencia a la representación de listas, teniendo en cuenta
     * solamente los elementos que sean 1.
     *
     * @param adyacencia Matriz en la forma primitiva a convertir.
     */
    public void construirAPartirDeMatriz(int adyacencia[][]) {
        int i, j, n;
        n = adyacencia.length;
        NodoSimple x;
        //Recorre filas.
        for (i = 0; i < n; i++) {
            //Recorre columnas.
            for (j = 0; j < n; j++) {
                //Se tienen en cuenta solamente los que tengan dato 1.
                if (adyacencia[i][j] == 1) {
                    //Se inserta x por la izquierda de vector[i].
                    x = new NodoSimple(j);
                    x.asignaLiga(vector[i]);
                    vector[i] = x;
                }
            }
        }
    }

    /**
     * Construye la representación en listas a partir de la representación en
     * tripletas. Convierte todo un grafo representado en matriz en tripletas de
     * matriz de adyacencia a la representación de listas, convirtiendo todos
     * sus elementos.
     *
     * @param grafo Matriz de adtacencia en tripletas del grafo a convertir.
     */
    public void construirAPartirDeTripletas(MatrizEnTripleta grafo) {
        int i, f, c, nt;
        Tripleta t;
        NodoSimple x;
        nt = grafo.numeroTripletas();
        //Se recorren todas las tripletas.
        for (i = 0; i < nt; i++) {
            t = grafo.retornaTripleta(i);
            f = t.retornaFila();
            c = t.retornaColumna();
            //En vector[f] se debe agregar el nuevo nodo con dato c por la
            //izquierda.
            x = new NodoSimple(c);
            x.asignaLiga(vector[f]);
            vector[f] = x;
        }
    }

    /**
     * Construye la representación en listas a partir de la representación en
     * matriz de adyacencia en forma 1. Convierte todo un grafo representado en
     * matriz en forma 1 de matriz de adyacencia a la representación de listas,
     * convirtiendo todos sus elementos.
     *
     * @param grafo Matriz de adyacencia en forma 1 del grafo a convertir.
     */
    public void construirAPartirDeForma1(MatrizForma1 grafo) {
        int f, c;
        NodoDoble p, q;
        NodoSimple x;
        Tripleta t;
        p = grafo.primero();
        while (!grafo.finRecorrido(p)) {
            q = p.retornaLD();
            while (q != p) {
                t = (Tripleta) q.retornaDato();
                f = t.retornaFila();
                c = t.retornaColumna();
                x = new NodoSimple(c);
                x.asignaLiga(vector[f]);
                vector[f] = x;
                q = q.retornaLD();
            }
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
        }
    }

    /**
     * Construye la representación en listas a partir de la representación en
     * matriz de adyacencia en forma 2. Convierte todo un grafo representado en
     * matriz en forma 2 de matriz de adyacencia a la representación de listas,
     * convirtiendo todos sus elementos.
     *
     * @param grafo Grafo representado en matriz de adyacencia de la forma 2 a
     * convertir.
     */
    public void construirAPartirDeForma2(MatrizForma2 grafo) {
        int f, c;
        NodoDoble p;
        NodoSimple x;
        Tripleta t;
        p = grafo.cabezaPorFilas();
        while (!grafo.finRecorrido(p)) {
            t = (Tripleta) p.retornaDato();
            f = t.retornaFila();
            c = t.retornaColumna();
            x = new NodoSimple(c);
            x.asignaLiga(vector[f]);
            vector[f] = x;
            p = p.retornaLD();
        }
    }

    public void convertirDeMatrizDeIncidencia(int[][] incidencia) {
        int i, j, k;
        NodoSimple x;
        //Se recorre la matriz por columnas (por lados y no por vértices).
        for (i = 0; i < incidencia[0].length; i++) {
            j = 0;
            //Se busca el primer vértice de este lado i.
            while (incidencia[j][i] == 0) {
                j = j + 1;
            }
            k = j + 1;
            //Se busca el segundo vértice de este lado i.
            while (incidencia[k][i] == 0) {
                k = k + 1;
            }
            //Se conecta cada uno de los vértices. Se deben conectar dos veces,
            //debido a que este no se repite como si lo haría en la matriz de
            //adyacencia.
            x = new NodoSimple(j);
            x.asignaLiga(vector[k]);
            vector[k] = x;
            x = new NodoSimple(k);
            x.asignaLiga(vector[j]);
            vector[j] = x;
        }
    }

    public void construyeDesdeMatrizDeIncidenciaForma1(MatrizForma1 incidencia) {
        NodoDoble p, q;
        NodoSimple x;
        int f, c;
        Tripleta t;
        p = incidencia.primero();
        while (!incidencia.finRecorrido(p)) {
            q = p.retornaLI();
            t = (Tripleta) q.retornaDato();
            f = t.retornaFila();
            q = q.retornaLI();
            t = (Tripleta) q.retornaDato();
            c = t.retornaFila();
            x = new NodoSimple(c);
            x.asignaLiga(vector[f]);
            vector[f] = x;
            x = new NodoSimple(f);
            x.asignaLiga(vector[c]);
            vector[c] = x;
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
        }
    }

    public void mostrarDFS() {
        visitado = new int[vector.length];
        mostrarDFSRecursivo(0);
    }

    private void mostrarDFSRecursivo(int v) {
        System.out.println(v);
        visitado[v] = 1;
        NodoSimple p = vector[v];
        int j;
        while (p != null) {
            j = (Integer) p.retornaDato();
            if (visitado[j] == 0) {
                mostrarDFSRecursivo(j);
            }
            p = p.retornaLiga();
        }
    }

    public void mostrarBFS() {
        visitado = new int[vector.length];
        cola = new Cola();
        mostrarBFSCompleto(0);
    }

    private void mostrarBFSCompleto (int v) {
        visitado[v] = 1;
        cola.add(v);
        NodoSimple p;
        int i;
        while (!cola.isEmpty()) {
            v = (Integer) cola.poll();
            System.out.println(v);
            p = vector[v];
            while (p != null) {
                i = (Integer) p.retornaDato();
                if (visitado[i] == 0) {
                    visitado[i] = 1;
                    cola.add(i);
                }
                p = p.retornaLiga();
            }
        }
    }
}
