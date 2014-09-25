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
public class ArbolAVL {
    
    NodoDobleAVL raiz;
    
    public ArbolAVL() {
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
        NodoDobleAVL pp = q;
        NodoDobleAVL pivote = p;


        /*
         * Aquí se buscará el lugar donde se ubicará en nuevo nodo y la
         * dirección de su padre (q).
         */
        while (p != null) {
            if ((Double) p.retornaDato() == d) {
                System.out.println("Dato ya existe");
                return;
            }
            if (p.retornaFB() != 0) {
                pivote = p;
                pp = q;
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
        
        int aux = pivote.retornaFB();
        if (d > (double) pivote.retornaDato()) {
            pivote.asignaFB(aux - 1);
            q = pivote.retornaLD();
        } else {
            pivote.asignaFB(aux + 1);
            q = pivote.retornaLI();
        }
        p = q;
        while (p != n) {
            if (d < (double) p.retornaDato()) {
                p.asignaFB(1);
                p = p.retornaLI();
            } else {
                p.asignaFB(-1);
                p = p.retornaLD();
            }
        }
        if (Math.abs(pivote.retornaFB()) < 2) {
            return;
        }
        if (pivote.retornaFB() == 2) {
            if (q.retornaFB() == 1) {
                q = unaRotacionALaDerecha(pivote, q);
            } else {
                q = dobleRotacionALaDerecha(pivote, q);
            }
        } else {
            if (q.retornaFB() == 1) {
                q = dobleRotacionALaIzquierda(pivote, q);
            } else {
                q = unaRotacionALaIzquierda(pivote, q);
            }
        }
        if (pp == null) {
            raiz = q;
            return;
        }
        if (pivote == pp.retornaLI()) {
            pp.asignaLI(q);
        } else {
            pp.asignaLD(q);
        }
    }
    
    public NodoDobleAVL unaRotacionALaDerecha(NodoDobleAVL p, NodoDobleAVL q) {
        p.asignaLI(q.retornaLD());
        q.asignaLD(p);
        p.asignaFB(0);
        q.asignaFB(0);
        return q;
    }
    
    public NodoDobleAVL unaRotacionALaIzquierda(NodoDobleAVL p, NodoDobleAVL q) {
        p.asignaLD(q.retornaLI());
        q.asignaLI(p);
        p.asignaFB(0);
        q.asignaFB(0);
        return q;
    }
    
    public NodoDobleAVL dobleRotacionALaDerecha(NodoDobleAVL p, NodoDobleAVL q) {
        NodoDobleAVL r = q.retornaLD();
        q.asignaLD(r.retornaLI());
        r.asignaLI(q);
        p.asignaLI(r.retornaLD());
        r.asignaLD(p);
        switch (r.retornaFB()) {
            case -1:
                p.asignaFB(0);
                q.asignaFB(1);
                break;
            case 0:
                p.asignaFB(0);
                q.asignaFB(0);
                break;
            case 1:
                p.asignaFB(-1);
                q.asignaFB(0);
                break;
        }
        r.asignaFB(0);
        return r;
    }
    
    public NodoDobleAVL dobleRotacionALaIzquierda(NodoDobleAVL p, NodoDobleAVL q) {
        NodoDobleAVL r = q.retornaLI();
        q.asignaLI(r.retornaLD());
        r.asignaLD(q);
        p.asignaLD(r.retornaLI());
        r.asignaLI(p);
        switch (r.retornaFB()) {
            case -1:
                p.asignaFB(1);
                q.asignaFB(0);
            case 0:
                p.asignaFB(0);
                q.asignaFB(0);
            case 1:
                p.asignaFB(0);
                q.asignaFB(-1);
        }
        return r;
    }
}
