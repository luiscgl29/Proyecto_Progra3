package arboles;

public class ArbolBinario {

    NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    //Metodo para insertar nodo en el arbol
    public void AgregarNodo(int d, String nom) {
        NodoArbol nuevo = new NodoArbol(d, nom);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoArbol auxiliar = raiz;
            NodoArbol padre;

            while (true) {
                padre = auxiliar;
                if (d < auxiliar.dato) {
                    auxiliar = auxiliar.HijoIzquierdo;
                    if (auxiliar == null) {
                        padre.HijoIzquierdo = nuevo;
                        return;
                    }
                } else {
                    auxiliar = auxiliar.HijoDerecho;
                    if (auxiliar == null) {
                        padre.HijoDerecho = nuevo;
                        return;
                    }
                }
            }
        }
    }

    public boolean EstaVacio() {
        return raiz == null;
    }

    //Metodo para metodo InOrden
    public void InOrden(NodoArbol r) {
        if (r != null) {
            InOrden(r.HijoIzquierdo);
            System.out.print(r.dato + ", ");
            InOrden(r.HijoDerecho);
        }
    }

    //Metodo para metodo PreOrden
    public void PreOrden(NodoArbol r) {
        if (r != null) {
            System.out.print(r.dato + ", ");
            PreOrden(r.HijoIzquierdo);
            PreOrden(r.HijoDerecho);
        }
    }

    public void PostOrden(NodoArbol r) {
        if (r != null) {
            PostOrden(r.HijoIzquierdo);
            PostOrden(r.HijoDerecho);
            System.out.print(r.dato + ", ");
        }
    }

    //Metodo para buscar un Nodo en el arbol
    public NodoArbol BuscarNodo(int d) {
        NodoArbol aux = raiz;
        while (aux.dato != d) {
            if (d < aux.dato) {
                aux = aux.HijoIzquierdo;
            } else {
                aux = aux.HijoDerecho;
            }
            if (aux == null) {
                return null;
            }
        }

        return aux;
    }

    public boolean EliminarNodo(int d) {
        NodoArbol auxiliar = raiz;
        NodoArbol padre = raiz;
        boolean EsHijoIzq = true;

        while (auxiliar.dato != d) {
            padre = auxiliar;
            if (d < auxiliar.dato) {
                EsHijoIzq = true;
                auxiliar = auxiliar.HijoIzquierdo;
            } else {
                EsHijoIzq = false;
                auxiliar = auxiliar.HijoDerecho;
            }
            if (auxiliar == null) {
                return false; //nunca lo encontro
            }
        }//fin del while

        if (auxiliar.HijoIzquierdo == null && auxiliar.HijoDerecho == null) { //caso para una hoja
            if (auxiliar == raiz) {
                raiz = null;
            } else if (EsHijoIzq) {
                padre.HijoIzquierdo = null;
            } else {
                padre.HijoDerecho = null;
            }
        } else if (auxiliar.HijoDerecho == null) {
            if (auxiliar == raiz) {
                raiz = auxiliar.HijoIzquierdo;
            } else if (EsHijoIzq) {
                padre.HijoIzquierdo = auxiliar.HijoIzquierdo;
            } else {
                padre.HijoDerecho = auxiliar.HijoIzquierdo;
            }
        } else if (auxiliar.HijoIzquierdo == null) {
            if (auxiliar == raiz) {
                raiz = auxiliar.HijoDerecho;
            } else if (EsHijoIzq) {
                padre.HijoIzquierdo = auxiliar.HijoDerecho;
            } else {
                padre.HijoDerecho = auxiliar.HijoDerecho;
            }
        } else {
            NodoArbol reemplazo = ObtenerNodoReemplazo(auxiliar);
            if (auxiliar == raiz) {
                raiz = reemplazo;
            } else if (EsHijoIzq) {
                padre.HijoIzquierdo = reemplazo;
            } else {
                padre.HijoDerecho = reemplazo;
            }
            reemplazo.HijoIzquierdo = auxiliar.HijoIzquierdo;
        }
        return true;
    }

    public NodoArbol ObtenerNodoReemplazo(NodoArbol nodoreemp) {
        NodoArbol reemplazopadre = nodoreemp;
        NodoArbol reemplazo = nodoreemp;
        NodoArbol auxiliar = nodoreemp.HijoDerecho;

        while (auxiliar != null) {
            reemplazopadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.HijoIzquierdo;
        }
        if (reemplazo != nodoreemp.HijoDerecho) {
            reemplazopadre.HijoIzquierdo = reemplazo.HijoDerecho;
            reemplazo.HijoDerecho = nodoreemp.HijoDerecho;

        }
        System.out.println("El nodo reemplazo es: " + reemplazo.dato);
        return reemplazo;
    }
}
