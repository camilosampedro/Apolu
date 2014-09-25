/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import pilasYColas.Cola;

/**
 *
 * @author camilo
 */
public class GrafoEnMatrizDeIncidencia {

    private int n, nl;
    private int[] visitado;
    private int[][] inci;
    private Cola cola;

    /**
     * @deprecated Necesaria revisión, los lados se podrían repetir si es no
     * dirigido.
     * @param adyacencia Matriz de adyacencia base para construir la matriz de
     * incidencia.
     */
    public void convertirDeAdyacencia(int[][] adyacencia) {
        int i, j, k;
        k = 0;
        for (i = 0; i < adyacencia.length; i++) {
            for (j = 0; j < adyacencia.length; i++) {
                if (adyacencia[i][j] == 1) {
                    k = k + 1;
                    inci[i][k] = 1;
                    inci[j][k] = 1;
                }
            }
        }
    }

    public void mostrarDFS() {
        visitado = new int[inci[0].length];
        mostrarDFSRecursivo(0);
    }

    private void mostrarDFSRecursivo(int v) {
        System.out.println(v);
        visitado[v] = 1;
        int i, j;
        for (i = 0; i < nl; i++) {
            if (inci[v][i] == 1) {
                for (j = 0; j < n; j++) {
                    if (v != j) {
                        if (inci[j][i] == 1) {
                            if (visitado[j] == 0) {
                                mostrarDFSRecursivo(j);
                            }
                        }
                    }
                }
            }
        }
    }

    public void mostrarBFS() {
        cola = new Cola();
        visitado = new int[n];
        mostrarBFSCompleto(0);
    }

    private void mostrarBFSCompleto(int v) {
        visitado[v] = 1;
        cola.add(v);
        int i, j;
        while (!cola.isEmpty()) {
            v = (Integer) cola.poll();
            System.out.println(v);
            for (i = 0; i < nl; i++) {
                if (inci[v][i] == 1) {
                    for (j = 0; j < n; j++) {
                        if (v != j) {
                            if (inci[j][i] == 1) {
                                if (visitado[j] == 0) {
                                    visitado[j] = 1;
                                    cola.add(j);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
