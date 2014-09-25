/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matricesDispersas;

import lista.NodoDoble;
import matricesDispersas.Tripleta;

/**
 * 
 * MatrizForma2 dispersa representada en listas ligadas de la forma 2.
 * Similar a la forma 1. Contiene un nodo cabezaPorFilas con las dimensiones de la
 * matriz, contiene solamente dos listas, una por la liga izquierda, la cual
 * recorre todos los elementos por columna (de arriba a abajo y de izquierda a 
 * derecha); y otra por la liga derecha, que recorre los elementos por filas (
 * de izquierda a derecha y de arriba a abajo).
 * 
 * @author camilo
 */
public class MatrizForma2 {

    NodoDoble mat;

    /**
     * Constructor de la instancia.
     * Inicializa la matriz completamente vacía, agregando únicamente el nodo
     * cabezaPorFilas, con las dimensiones especificadas
     * @param filas Número de filas que contendrá la matriz.
     * @param columnas Número de columnas que contendrá la matriz.
     */
    public MatrizForma2(int filas, int columnas) {
        NodoDoble x;
        Tripleta tx;
        Tripleta t = new Tripleta(filas, columnas, 0);
        mat = new NodoDoble(t);
        //Creación del nodo auxiliar con parámetros vacíos.
        tx = new Tripleta(0, 0, 0);
        x = new NodoDoble(tx);
        //El nodo cabezaPorFilas auxiliar cuando está la lista vacía apunta hacia sí mismo por
        //ambas ligas.
        x.asignaLI(x);
        x.asignaLD(x);
        mat.asignaLD(x);
    }

    /**
     * 
     * @return Nodo con las dimensiones de la matriz y referencia al nodo auxiliar.
     */
    public NodoDoble primero() {
        return mat;
    }

    /**
     * Primer nodo para avanzar por filas. 
     * Para avanzar por filas, se debe de avanzar por Liga Derecha.
     * @see #finRecorrido(Lista.NodoDoble) 
     * @return Nodo cabezaPorFilas auxiliar.
     */
    public NodoDoble cabezaPorFilas() {
        return mat.retornaLD();
    }
    
    /**
     * Primer nodo para avanzar por columnas. 
     * Para avanzar por columnas, se debe de avanzar por Liga Izquierda.
     * @see #finRecorrido(Lista.NodoDoble) 
     * @return Nodo cabezaPorFilas auxiliar.
     */
    public NodoDoble cabezaPorColumnas() {
        return mat.retornaLI();
    }

    /**
     * Es vacía. La matriz solamente es vacía si el nodo cabezaPorFilas auxiliar apunta
     * hacia sí mismo por ambos lados.
     * 
     * @return Verdadero si la matriz no contiene ningún elemento.
     */
    public boolean esVacia() {
        NodoDoble p = mat.retornaLD();
        return (p.retornaLI()==p && p.retornaLD()==p);
    }

    /**
     * Verifica si el nodo p, avanzando, ha llegado a ser de nuevo la cabezaPorFilas. Se
     * utiliza para hacer recorridos dentro de la matriz. Se verifica si se le
     * ha dado la vuelta completamente a la matriz.
     *
     * @param p Nodo al que se le realizará la verificación de si se ha llegado
     * a la cabezaPorFilas o no.
     * @return Verdadero/Falso si se ha llegado a la cabezaPorFilas de nuevo o no.
     */
    public boolean finRecorrido(NodoDoble p) {
        return p == cabezaPorFilas();
    }

    /**
     * Método para mostrar la matriz por la consola.
     */
    public void muestraMatriz() {
        int qf, qc;
        Object qv;
        NodoDoble q;
        Tripleta tq;
        q = cabezaPorFilas().retornaLD();
        while (!finRecorrido(q)) {
            tq = (Tripleta) q.retornaDato();
            qf = tq.retornaFila();
            qc = tq.retornaColumna();
            qv = tq.retornaValor();
            System.out.println(qf + ", " + qc + ", " + qv);
            q = q.retornaLD();
        }
    }

    /**
     * Conecta por filas el nodo especificado.
     * Busca el lugar correspondiente en filas para insertar x. Se debería de
     * llamar junto con conectaPorColumnas() para construir correctamente la
     * matriz.
     * 
     * @see #conectaPorColumnas(Lista.NodoDoble) 
     * @param x Nodo a insertar.
     */
    private void conectaPorFilas(NodoDoble x) {
        NodoDoble p, q, anterior;
        Tripleta tx, tq;
        int i;
        //Se extrae las tripleta de x, contenedora de la posición de x y se
        //inicializa p como la cabezaPorFilas auxiliar, anterior se asume como p y q
        //comienza a recorrer desde p.
        tx = (Tripleta) x.retornaDato();
        p = cabezaPorFilas();
        anterior = p;
        q = p.retornaLD();
        tq = (Tripleta) q.retornaDato();
        //Se recorre por filas(LD) hasta que encuentre la fila en la que debería
        //de insertar.
        while (q != p && tq.retornaFila() < tx.retornaFila()) {
            anterior = q;
            q = q.retornaLD();
            tq = (Tripleta) q.retornaDato();
        }
        //A lo largo de la fila en la que debe de insertar, se busca en donde
        //insertar alrededor de las columnas.
        while (q != p && tq.retornaFila() == tx.retornaFila() 
                && tq.retornaColumna() < tx.retornaColumna()) {
            anterior = q;
            q = q.retornaLD();
            tq = (Tripleta) q.retornaDato();
        }
        //Se inserta x entre anterior y q.
        anterior.asignaLD(x);
        x.asignaLD(q);
        
    }

    /**
     * Conecta por columnas el nodo especificado.
     * Busca el lugar correspondiente en columnas para insertar x. Se debería de
     * llamar junto con conectaPorColumnas() para construir correctamente la 
     * matriz.
     * 
     * @see #conectaPorFilas(Lista.NodoDoble) 
     * @param x Nodo a insertar.
     */
    private void conectaPorColumnas(NodoDoble x) {
        NodoDoble p, q, anterior;
        Tripleta tq, tx;
        int i;
        //Extrae la tripleta de x, la cual contiene la dirección en la que debe
        //ir x. Se inicializan p como la cabezaPorFilas, anterior se asume como p y 
        //q comenzará a recorrer la matriz.
        tx = (Tripleta) x.retornaDato();
        p = cabezaPorFilas();
        anterior = p;
        q = p.retornaLI();
        tq = (Tripleta) q.retornaDato();
        //Recorriendo por columnas(LI), se busca en dónde comienza, si existe
        //la columna correspondiente a x.
        while (p != q && tq.retornaColumna() < tx.retornaColumna()) {
            anterior = q;
            q = q.retornaLI();
            tq = (Tripleta) q.retornaDato();
        }
        //Luego se continúa recorriendo dicha columna, buscando la posición en
        //filas del nodo x.
        while (p != q && tq.retornaColumna() == tx.retornaColumna() 
                && tq.retornaFila() < tx.retornaFila()) {
            anterior = q;
            q = q.retornaLI();
            tq = (Tripleta) q.retornaDato();
        }
        //Se inserta x entre anterior y q.
        anterior.asignaLI(x);
        x.asignaLI(q);
    }
    
    public void conecta(NodoDoble x){
        conectaPorFilas(x);
        conectaPorColumnas(x);
        //¡¡¡¡¡¡¡¡Sin probar!!!!!!!!!!!!!:
        //Aumento del número de elementos no vacíos en mat.
        Tripleta t = (Tripleta) mat.retornaDato();
        int n = (Integer) t.retornaValor();
        n = n + 1;
        t.asignaValor(n);
    }
    
    public int numeroDeElementos(){
        Tripleta t = (Tripleta) mat.retornaDato();
        return (Integer) t.retornaValor();
    }
    
    public int numeroFilas(){
        Tripleta t = (Tripleta) mat.retornaDato();
        return (Integer) t.retornaFila();
    }
    
    public int numeroColumnas(){
        Tripleta t = (Tripleta) mat.retornaDato();
        return (Integer) t.retornaColumna();
    }
    
    /*
     * Prototipo para leer e insertar datos:
     * 
     * read (m,n);
     * MatrizForma2 a = new MatrizForma2 (m,n);
     * mientras haya datos por leer haga
     *      read (f,c,v);
     *      t = new Tripleta(f,c,v);
     *      x = new NodoDoble(t);
     *      a.conectaPorFilas(x);
     *      a.conectaPorColumnas(x);
     * fin(mientras)
     */
}
