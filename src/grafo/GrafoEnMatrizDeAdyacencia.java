/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import lista.NodoDoble;
import lista.NodoSimple;
import matricesDispersas.MatrizEnTripleta;
import matricesDispersas.MatrizForma1;
import matricesDispersas.MatrizForma2;
import matricesDispersas.Tripleta;
import pilasYColas.Cola;

/**
 *
 * @author camilo
 */
public class GrafoEnMatrizDeAdyacencia {

    private MatrizForma2 adya;
    private int[][] adyacencia;
    //private MatrizForma1 adya;
    private MatrizEnTripleta adyaT;
    private int[] visitado;
    private Tripleta[] ST;
    private int[][] E;
    private int[] s;
    private int[] ruta;
    private Cola cola;
    private int[] conectado;
    private double[][] costos;

    public GrafoEnMatrizDeAdyacencia(int numeroElementos) {
        adya = new MatrizForma2(numeroElementos, numeroElementos);
        adyacencia = new int[numeroElementos][numeroElementos];
        //adya = new MatrizForma1(m,n);
        /*Tripleta t = new Tripleta(m,n,0);
         adya = new MatrizEnTripleta(t);
         */
    }

    public void agregar(int vertice1, int vertice2) {
        Tripleta t = new Tripleta(vertice1, vertice2, 1);
        NodoDoble x = new NodoDoble(t);
        adya.conecta(x);
    }

    public boolean esVacia() {
        return adya.esVacia();
    }

    public void mostrar() {
        adya.muestraMatriz();
    }

    public GrafoEnListaDeAdyacencia convertirAListaDeAdyacencia() {
        GrafoEnListaDeAdyacencia g2;
        int v1, v2, numeroElementos;
        numeroElementos = adya.numeroFilas();
        System.out.println("Número de elementos: " + numeroElementos);
        g2 = new GrafoEnListaDeAdyacencia(numeroElementos);
        NodoDoble p = adya.cabezaPorFilas().retornaLD();
        System.out.println("Es fin de recorrido: " + adya.finRecorrido(p));
        while (!adya.finRecorrido(p)) {
            Tripleta t = (Tripleta) p.retornaDato();
            v1 = t.retornaFila();
            v2 = t.retornaColumna();
            System.out.println("Manda " + v1 + " y " + v2);
            g2.agregarLado(v1, v2);
            p = p.retornaLD();
        }
        return g2;
    }

    public void convertirDeIncidencia(int[][] incidencia) {
        int i, j, k;
        for (i = 0; i < incidencia[0].length; i++) {
            j = 0;
            while (incidencia[j][i] == 0) {
                j = j + 1;
            }
            k = j + 1;
            while (incidencia[k][i] == 0) {
                k = k + 1;
            }
            adyacencia[j][k] = 1;
            adyacencia[k][j] = 1;
        }
    }

    public void convertirDeListasATripletas(GrafoEnListaDeAdyacencia grafo) {
        int i, j;
        NodoSimple p;
        Tripleta t;
        for (i = 0; i < grafo.vector.length; i++) {
            p = grafo.vector[i];
            while (p != null) {
                j = (Integer) p.retornaDato();
                t = new Tripleta(i, j, 1);
                adyaT.insertaTripleta(t);
                p = p.retornaLiga();
            }
        }
    }

    public void convertirDeIncidenciaForma1(MatrizForma1 inci) {
        NodoDoble p, q;
        Tripleta t;
        int i, j;
        p = inci.primero();
        while (!inci.finRecorrido(p)) {
            q = p.retornaLI();
            t = (Tripleta) q.retornaDato();
            i = t.retornaFila();

            q = q.retornaLI();
            t = (Tripleta) q.retornaDato();
            j = t.retornaFila();

            adyacencia[i][j] = 1;
            adyacencia[j][i] = 1;
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
        }
    }

    public void convertirDeListasDeAdyacencia(NodoSimple grafo[]) {
        NodoSimple x;
        int i, j;
        for (i = 0; i < grafo.length; i++) {
            x = grafo[i];
            while (x != null) {
                j = (Integer) x.retornaDato();
                adyacencia[i][j] = 1;
                x = x.retornaLiga();
            }
        }
    }

    public void mostrarDFS() {
        mostrarDFSRecursivo(0);
        visitado = new int[adyacencia.length];
    }

    private void mostrarDFSRecursivo(int v) {
        System.out.println(v);
        visitado[v] = 1;
        int j;
        for (j = 0; j < adyacencia.length; j++) {
            if (adyacencia[v][j] == 1) {
                if (visitado[j] == 0) {
                    mostrarDFSRecursivo(j);
                }
            }
        }
    }

    public void mostrarBFS() {
        visitado = new int[adyacencia.length];
        cola = new Cola();
        mostrarBFSCompleto(0);
    }

    public void mostrarBFSDesde(int i) {
        visitado = new int[adyacencia.length];
        cola = new Cola();
        mostrarBFSCompleto(i);
    }

    private void mostrarBFSCompleto(int v) {
        visitado[v] = 1;
        cola.add(v);
        int i;
        while (!cola.isEmpty()) {
            v = (Integer) cola.poll();
            System.out.println(v);
            for (i = 0; i < adyacencia.length; i++) {
                if (adyacencia[v][i] == 1) {
                    if (visitado[i] == 0) {
                        visitado[i] = 1;
                        cola.add(i);
                    }
                }
            }
        }
    }

    /**
     * @deprecated No terminado.
     */
    /*
    public void kruskal(MatrizForma1 g) {
        Pareja l;
        int i, j, k, v, w;
        NodoSimple p;
        eneConjunto nv;
        nv = new eneConjunto(n);
        Conjunto st = new Conjunto();
        nv.inicializa();
        k = 0;
    }
    */

    public void prim(int v) {
        ST = new Tripleta[adyacencia.length];
        conectado = new int[adyacencia.length];
        int m = 0, k, vi, vj;
        Tripleta l;
        conectado[v] = 1;
        k = 0;
        while (m <= adyacencia.length - 1) {
            l = escogeLado();
            ST[k] = l;
            m = m + 1;
            vi = l.retornaFila();
            vj = l.retornaColumna();
            conectado[vi] = 1;
            conectado[vj] = 1;
        }
    }

    private Tripleta escogeLado() {
        double menor = Double.MAX_VALUE - 10;
        int i, j;
        Tripleta lado = new Tripleta(0, 0, menor);
        for (i = 0; i < adyacencia.length; i++) {
            if (conectado[i] == 1) {
                for (j = 0; j <= adyacencia.length; j++) {
                    if (conectado[j] == 0) {
                        if (costos[i][j] < menor) {
                            menor = costos[i][j];
                            lado = new Tripleta(i, j, menor);
                        }
                    }
                }
            }
        }
        return lado;
    }

    public boolean esConexo() {
        int i, j;
        for (i = 0; i < adyacencia.length; i++) {
            mostrarBFSDesde(i);
            for (j = 0; j < visitado.length; j++) {
                if (visitado[j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int elementosNoConexos() {
        int i, j, elementos = 1;
        for (i = 0; i < adyacencia.length; i++) {
            mostrarBFSDesde(i);
            cicloVerificacion:
            for (j = 0; j < visitado.length; j++) {
                if (visitado[j] == 0) {
                    elementos = elementos + 1;;
                    break cicloVerificacion;
                }
            }
        }
        return elementos;
    }

    public void dijkstra(int n, int v, double costoMinimo[]) {
        int i, j, w;
        double paso;
        //Se inicializa costoMinimo, asumiendo que el existente en costos es el
        //mínimo. ruta se inicializa como si existiera ruta directa.
        for (i = 0; i < n; i++) {
            costoMinimo[i] = costos[v][i];
            s[i] = 0;
            ruta[i] = i;
        }
        //Se marca s en la posición v como 1, debido a que es la salida.
        s[v] = 1;
        i = 1;
        cicloTotal:
        while (i < n - 1) {
            j = 0;
            //Se busca el siguiente que no tenga una ruta definitiva.
            while (j < n && s[j] == 1) {
                j = j + 1;
            }
            if (j > n) {
                break cicloTotal;
            }
            w = j;
            //Desde el j que no tiene ruta definitiva, se comienzan a redefinir 
            //los costos mínimos. Se busca un w tal que este tenga el costo
            //mínimo dentro de los que no tienen ruta definitiva.
            for (j = w + 1; j <= n; j++) {
                if (s[j] == 0 && costoMinimo[j] < costoMinimo[w]) {
                    w = j;
                }
            }
            //Se marca en s el vértice w, ya tiene la ruta más corta.
            s[w] = 1;
            i = i + 1;
            //Se buscan los vértices que tienen en s un cero y con estos se
            //calcula si por w (El cual acaba de encontrar una ruta definitiva)
            //se encuentra una ruta menor.
            for (j = 1; j <= n; j++) {
                if (s[j] == 0) {
                    paso = costoMinimo[w] + costos[w][j];
                    if (paso < costoMinimo[j]) {
                        //Si esta ruta por w es menor, se actualiza como su
                        //costo mínimo y la ruta para llegar a este pasa por w.
                        costoMinimo[j] = paso;
                        ruta[j] = w;
                    }
                }
            }
        }
    }

    /**
     * Crea una matriz en donde se puede apreciar a qué vértices se puede llegar
     * desde un nodo dado.
     *
     * @return Una matriz en la que la posición [i,j] define que desde el
     * vértice i se puede llegar al vértice j, directa o indirectamente.
     */
    public int[][] warshall() {
        int i, j, k;
        int[][] cierreTransitivo = new int[adyacencia.length][adyacencia.length];
        for (i = 0; i < adyacencia.length; i++) {
            for (j = 0; j < adyacencia.length; j++) {
                cierreTransitivo[i][j] = adyacencia[i][j];
            }
        }
        for (k = 0; k < adyacencia.length; k++) {
            for (i = 0; i < adyacencia.length; i++) {
                for (j = 0; j < adyacencia.length; j++) {
                    if (cierreTransitivo[i][k] == 1 && cierreTransitivo[k][j] == 1 && i != j) {
                        cierreTransitivo[i][j] = 1;
                    }
                }
            }
        }
        return cierreTransitivo;
    }

    public double[][] floyd(double[][] costos) {
        double[][] caminos = new double[costos.length][costos.length];
        int i, j, k;
        double aux;
        for (i = 0; i < costos.length; i++) {
            for (j = 0; j < costos.length; j++) {
                caminos[i][j] = costos[i][j];
            }
        }
        for (k = 0; k < costos.length; k++) {
            for (i = 0; i < costos.length; i++) {
                for (j = 0; j < costos.length; j++) {
                    aux = caminos[i][k] + caminos[k][j];
                    if (aux < caminos[i][j]) {
                        caminos[i][j] = aux;
                    }
                }
            }
        }
        return caminos;
    }

    /**
     * @deprecated No terminado.
     * @return
     */
    public boolean tieneCiclos() {
        int[] analizados = new int[adyacencia.length];
        int[] noAnalizados = new int[adyacencia.length];
        int i, j;
        for (i = 0; i < adyacencia.length; i++) {
            noAnalizados[i] = i;
        }
        for (i = 0; i < adyacencia.length; i++) {
            for (j = 0; i < adyacencia.length; j++) {
            }
        }
        return false;
    }
}
