package arboles;

public class NodoArbol {

    int dato;
    String nombre;
    NodoArbol HijoIzquierdo, HijoDerecho;

    public NodoArbol() {

    }

    public NodoArbol(int d, String nom) {
        this.dato = d;
        this.nombre = nom;
        this.HijoIzquierdo = null;
        this.HijoDerecho = null;
    }
}
