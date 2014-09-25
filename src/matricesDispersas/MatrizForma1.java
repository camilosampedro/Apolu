package matricesDispersas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import lista.NodoDoble;
import matricesDispersas.Tripleta;

/**
 * 
 * MatrizForma1 dispersa representada en listas ligadas de la forma 1.
 * Las matrices en esta forma de representación contienen una lista para cada
 * fila, enlazadas por la liga derecha y una lista para cada columna, enlazadas
 * por la liga izquierda. Existe un nodo cabeza para cada fila/columna. Un nodo
 * cabeza 1, es la cabeza para todos los elementos de la fila 1 por la liga
 * izquierda y a los de la columna 1 por la liga derecha.
 * 
 * @author camilo
 */
public class MatrizForma1 {

    private NodoDoble mat;
    private int m, n;

    /**
     * Crea una matriz completamente vacía. Únicamente se crea el nodo que
     * contendrá las dimensiones de la matriz.
     *
     * @param filas Filas de la nueva matriz.
     * @param columnas Columnas de la nueva matriz.
     */
    public MatrizForma1(int filas, int columnas) {
        Tripleta t = new Tripleta(filas, columnas, 0);
        mat = new NodoDoble(t);
        t.asignaValor(mat);
        mat.asignaDato(t);
    }

    /**
     * @return Nodo que contiene las dimensiones de la matriz y la dirección del
     * primer nodo.
     */
    public NodoDoble cabeza() {
        return mat;
    }

    /**
     * Primer nodo para hacer el recorrido. Si este nodo es igual a cabeza(), la
     * matriz está vacía.
     *
     * @return Nodo conectado a la cabeza de la matriz.
     */
    public NodoDoble primero() {
        Tripleta tp = (Tripleta) mat.retornaDato();
        NodoDoble p = (NodoDoble) tp.retornaValor();
        return p;
    }

    /**
     * La matriz es vacía cuando primero() == cabeza().
     *
     * @return Si la matriz es vacía o no.
     */
    public boolean esVacia() {
        NodoDoble p = primero();
        return (p == mat);
    }

    /**
     * Verifica si el nodo p, avanzando, ha llegado a ser de nuevo la cabeza. Se
     * utiliza para hacer recorridos dentro de la matriz. Se verifica si se le
     * ha dado la vuelta completamente a la matriz.
     *
     * @param p Nodo al que se le realizará la verificación de si se ha llegado
     * a la cabeza o no.
     * @return Verdadero/Falso si se ha llegado a la cabeza de nuevo o no.
     */
    public boolean finRecorrido(NodoDoble p) {
        return (p == mat);
    }

    /**
     * Método para mostrar la matriz por la consola.
     *
     */
    public void muestraMatriz() {
        int qf, qc;
        Double qv;
        NodoDoble p, q;
        Tripleta tp, tq;
        // Se inicializa el nodo doble p como primero para comenzar el recorrido.
        p = primero();
        while (!finRecorrido(p)) {
            //En cada iteración del ciclo se avanza con q por la liga derecha de p.
            //La liga derecha de p es el primer elemento de la lista de la fila
            //de p.
            q = p.retornaLD();
            while (q != p) {
                //Avanza con q por toda la lista, mostrando cada uno de los
                //datos contenidos en q.
                tq = (Tripleta) q.retornaDato();
                qf = tq.retornaFila();
                qc = tq.retornaColumna();
                qv = (Double) tq.retornaValor();
                System.out.println(qf + ", " + qc + ", " + qv);
                q = q.retornaLD();
            }
            //Luego de avanzar toda la lista de p, se procede a avanzar con p.
            //p avanza por su campo de dato, allí contiene a la cabeza de la
            //siguiente fila.
            tp = (Tripleta) p.retornaDato();
            p = (NodoDoble) tp.retornaValor();
        }
    }

    /**
     * Método para construir los nodos cabeza de la matriz.
     * Construye el número de nodos cabeza según las dimensiones determinadas
     * por el usuario en pasos anteriores.
     */
    public void construyeCabezas() {
        int mayor, i;
        NodoDoble x, ultimo;
        Tripleta t;
        ultimo = cabeza();
        //Se comienza extrayendo las dimensiones de la matriz del nodo cabeza.
        t = (Tripleta) ultimo.retornaDato();
        m = t.retornaFila();
        n = t.retornaColumna();
        //Se verifica cuál de las dimensiones es mayor, este será el número de
        //cabezas para las listas.
        mayor = m;
        if (n > m) {
            mayor = n;
        }
        //Se recorre hasta ese mayor (Número de cabezas a construir).
        for (i = 1; i <= mayor; i++) {
            //Se inicializa un nodo con el dato siendo una tripleta con 
            //fila y columna i para ser la cabeza. El dato conectará a la cabeza
            //dado que este, hasta el momento, es la última cabeza.
            t = new Tripleta(i, i, cabeza());
            x = new NodoDoble(t);
            //Se conecta a si mismo por la izquierda y la derecha.
            x.asignaLD(x);
            x.asignaLI(x);
            //Se crea otra tripleta con el dato de la última cabeza y se le cambia
            //el dato al nuevo nodo cabeza (Se inserta). Luego último pasa a ser
            //la nueva cabeza.
            t = (Tripleta) ultimo.retornaDato();
            t.asignaValor(x);
            ultimo.asignaDato(t);
            ultimo = x;
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
    public void conectaPorFilas(NodoDoble x) {
        NodoDoble p, q, anterior;
        Tripleta tp, tq, tx;
        int i;
        //Se extrae la tripleta de x para luego poder buscar las posiciones para
        //insertarlo.
        tx = (Tripleta) x.retornaDato();
        //Se inicializa p como el primer nodo.
        p = primero();
        //Se avanza por las cabezas hasta encontrar la cabeza de la fila que le
        //corresponde al nodo a insertar.
        for (i = 1; i < tx.retornaFila(); i++) {
            tp = (Tripleta) p.retornaDato();
            p = (NodoDoble) tp.retornaValor();
        }
        //Se guarda dicha posición como anterior y se inicializa q para comenzar
        //a avanzar por la lista de esta cabeza.
        anterior = p;
        q = p.retornaLD();
        tq = (Tripleta) q.retornaDato();
        //Se recorre la lista buscando la columna a insertar el nodo, guardando
        //anterior.
        while ((q != p) && (tq.retornaColumna() < tx.retornaColumna())) {
            anterior = q;
            q = q.retornaLD();
            tq = (Tripleta) q.retornaDato();
        }
        //Se le pega a anterior por la derecha el nuevo nodo y al nuevo nodo por
        //la derecha el nodo q, que es donde se detuvo el ciclo para buscar la
        //posición en columna.
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
    public void conectaPorColumnas(NodoDoble x) {
        NodoDoble p, q, anterior;
        Tripleta tp, tq, tx;
        int i;
        //Se extraen los datos del nodo que se quiere insertar.
        tx = (Tripleta) x.retornaDato();
        //Se inicializa el nodo p para comenzar el recorrido.
        p = primero();
        //Aquí se busca la cabeza de la columna en la que se debería insertar.
        for (i = 1; i < tx.retornaColumna(); i++) {
            tp = (Tripleta) p.retornaDato();
            p = (NodoDoble) tp.retornaValor();
        }
        anterior = p;
        //Se inicializa q para comenzar a hacer el recorrido por la lista de p.
        q = p.retornaLI();
        tq = (Tripleta) q.retornaDato();
        //Se busca la posición en filas, con respecto a esta lista, en donde se
        //insertará el nuevo nodo.
        while (q != p && tq.retornaFila() < tx.retornaFila()) {
            anterior = q;
            q = q.retornaLI();
            tq = (Tripleta) q.retornaDato();
        }
        //Se inserta entre anterior y q.
        anterior.asignaLI(x);
        x.asignaLI(q);
    }
    
    /*
     * Prototipo para leer e insertar datos:
     * 
     * read (m,n);
     * MatrizForma1 a = new MatrizForma1 (m,n);
     * mientras haya datos por leer haga
     *      read (f,c,v);
     *      t = new Tripleta(f,c,v);
     *      x = new NodoDoble(t);
     *      a.conectaPorFilas(x);
     *      a.conectaPorColumnas(x);
     * fin(mientras)
     */
}
