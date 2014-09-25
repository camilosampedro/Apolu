/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matricesDispersas;

/**
 *
 * @author camilo
 */
public class Tripleta {
    
    private int fila;
    private int columna;
    private Object valor;

    /**
     * Método constructor. Inicializa una tripleta no vacía con los valores.
     *
     * @param f valor de la fila que contendrá la tripleta.
     * @param c valor de la columna que contendrá la tripleta.
     * @param val valor contenido dentro de la tripleta.
     */
    public Tripleta(int f, int c, Object val) {
        fila = f;
        columna = c;
        valor = val;
    }

    /**
     * @return Retorna la fila de la tripleta.
     */
    public int retornaFila() {
        return fila;
    }

    /**
     * @return Retorna la columna de la tripleta.
     */
    public int retornaColumna() {
        return columna;
    }

    /**
     * @return Retorna el valor de la tripleta.
     */
    public Object retornaValor() {
        return valor;
    }

    /**
     * Asigna un nuevo valor a la fila de la tripleta.
     * @param f valor de la fila nueva de la tripleta.
     */
    public void asignaFila(int f) {
        fila = f;
    }

    /**
     * Asigna un nuevo valor a la columna de la tripleta.
     * @param c valor de la columna nueva de la tripleta.
     */
    public void asignaColumna(int c) {
        columna = c;
    }

    /**
     * Asigna un nuevo valor a la tripleta.
     * @param val valor de la fila nueva de la tripleta.
     */
    public void asignaValor(Object val) {
        valor = val;
    }
}
