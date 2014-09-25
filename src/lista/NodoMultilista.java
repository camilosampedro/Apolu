/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

/**
 *
 * @author camilo
 */
public class NodoMultilista {

    NodoMultilista ligaVi, ligaVj;
    int vi, vj;
    boolean sw;

    public NodoMultilista(int nuevoVi, int nuevoVj) {
        vi = nuevoVi;
        vj = nuevoVj;
    }

    public void asignaVi(int nuevoVi) {
        vi = nuevoVi;
    }

    public void asignaVj(int nuevoVj) {
        vj = nuevoVj;
    }
    
    public void asignaLVi(NodoMultilista LigaVi){
        ligaVi = LigaVi;
    }
    
    public void asignaLVj(NodoMultilista LigaVj){
        ligaVj = LigaVj;
    }

    public void asignaSW(boolean nuevoSW) {
        sw = nuevoSW;
    }

    public NodoMultilista retornaLVi() {
        return ligaVi;
    }

    public NodoMultilista retornaLVj() {
        return ligaVj;
    }

    public int retornaVi() {
        return vi;
    }

    public int retornaVj() {
        return vj;
    }

    public boolean retornaSW() {
        return sw;
    }
}
