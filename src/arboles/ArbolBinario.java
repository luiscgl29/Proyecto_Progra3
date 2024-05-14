package arboles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ArbolBinario {

    public NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    //Metodo para insertar nodo en el arbol
    public void AgregarNodo(String nom, long d) {
        NodoArbol nuevo = new NodoArbol(nom, d);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoArbol auxiliar = raiz;
            NodoArbol padre;

            while (true) {
                padre = auxiliar;
                if (d < auxiliar.dpi) {
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

    //Metodo para metodo PreOrden
    public void PreOrden(NodoArbol r) {
        if (r != null) {
            System.out.print(r.dpi + ", ");
            PreOrden(r.HijoIzquierdo);
            PreOrden(r.HijoDerecho);
        }
    }

    //Metodo para metodo InOrden
    public void InOrden(NodoArbol r) {
        if (r != null) {
            InOrden(r.HijoIzquierdo);
            System.out.print(r.dpi + ", ");
            InOrden(r.HijoDerecho);
        }
    }

    public void PostOrden(NodoArbol r) {
        if (r != null) {
            PostOrden(r.HijoIzquierdo);
            PostOrden(r.HijoDerecho);
            System.out.print(r.nombre + "\t" + r.dpi + "  ");
        }
    }

    //Metodo para buscar un Nodo en el arbol
    public NodoArbol BuscarNodo(long d) {
        NodoArbol aux = raiz;
        while (aux.dpi != d) {
            if (d < aux.dpi) {
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

    public boolean EliminarNodo(long d) {
        NodoArbol auxiliar = raiz;
        NodoArbol padre = raiz;
        boolean EsHijoIzq = true;

        while (auxiliar.dpi != d) {
            padre = auxiliar;
            if (d < auxiliar.dpi) {
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
        System.out.println("\nEl nodo reemplazo es: " + reemplazo.dpi);
        return reemplazo;
    }

    public void cargarArchivo() {
        try {
            BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\luis-\\Documents\\NetBeansProjects\\Proyecto_Progra3\\src\\Archivo\\infovacuna.txt"));
            //BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\luis-\\Downloads\\Vacunados.txt"));
            leer.readLine();
            String linea;
            while ((linea = leer.readLine()) != null) {
                String[] partes = linea.split("\t");
                String nombre = partes[0];
                String dpi = partes[1];

                Pattern pt = Pattern.compile("\\d+");
                Matcher mt = pt.matcher(dpi);

                String ndpi = "";
                
                while (mt.find()) {
                    ndpi += mt.group();
                }

                Long dpiEntero = Long.parseLong(ndpi);
                AgregarNodo(nombre, dpiEntero);
            }
            leer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en lectura del archivo");
        }
    }

    public void imprimir(NodoArbol r, FileWriter guardar) {
        try {
            if (r != null) {
                imprimir(r.HijoIzquierdo, guardar);
                imprimir(r.HijoDerecho, guardar);
                String datos = r.nombre + "\t" + r.dpi + "\n";
                guardar.write(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en recorrido");
        }
    }

    public void guardarArchivo() {
        try {
            FileWriter guardar = new FileWriter("C:\\Users\\luis-\\Documents\\NetBeansProjects\\Proyecto_Progra3\\src\\Archivo\\PacientesGuardados.txt", true);
            guardar.write("NOMBRE DE PERSONA VACUNADA" + "\t" + "NÚMERO DE IDENTIFICACIÓN" + "\n");
            imprimir(raiz, guardar);
            guardar.close();
            JOptionPane.showMessageDialog(null, "Lineas guardads");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en escritura del archivo");
        }
    }
}
