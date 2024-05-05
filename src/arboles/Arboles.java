package arboles;

import javax.swing.JOptionPane;

public class Arboles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 0, numero, valor;
        String nombre;

        ArbolBinario arbolito = new ArbolBinario();

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Agregar un Nodo \n"
                        + "2.Recorrer el Arbol InOrden \n"
                        + "3.Recorrer el Arbol PreOrden \n"
                        + "4.Recorrer el Arbol PostOrden \n"
                        + "5. Buscar un Nodo \n"
                        + "6. Eliminar un Nodo en el arbol \n"
                        + "7.Salir \n"
                        + "Elige una opcion...", "Arboles Binarios UMG", JOptionPane.QUESTION_MESSAGE));

                switch (opcion) {
                    case 1:
                        numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el numero del Nodo...", "Agregando un nodo", JOptionPane.QUESTION_MESSAGE));
                        nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre del Nodo...", "Agregando un nodo", JOptionPane.QUESTION_MESSAGE);
                        arbolito.AgregarNodo(numero, nombre);
                        break;
                    case 2:
                        if (!arbolito.EstaVacio()) {
                            System.out.println();
                            arbolito.InOrden(arbolito.raiz);
                        }
                        break;
                    case 3:
                        if (!arbolito.EstaVacio()) {
                            System.out.println("");
                            arbolito.PreOrden(arbolito.raiz);
                        }
                        break;
                    case 4:
                        if (!arbolito.EstaVacio()) {
                            System.out.println("");
                            arbolito.PostOrden(arbolito.raiz);
                        }
                        break;
                    case 5:
                        if (!arbolito.EstaVacio()) {
                            numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero del nodo a buscar", "Buscando un nodo", JOptionPane.QUESTION_MESSAGE));
                            NodoArbol tmp;
                            tmp = arbolito.BuscarNodo(numero);
                            if (tmp == null) {
                                JOptionPane.showMessageDialog(null, "No se encontro el nodo", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                System.out.println("Nodo encontrado, Su contenido es: " + tmp.dato);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El Arbol esta vacio", "Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 6:
                        if (!arbolito.EstaVacio()) {
                            numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nodo a eliminar"));
                            if (arbolito.EliminarNodo(numero) == false) {
                                JOptionPane.showMessageDialog(null, "El Nodo a eliminar no se encontro");
                            } else {
                                JOptionPane.showMessageDialog(null, "El Nodo ha sido eliminado");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El Arbol esta vacio");
                        }
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Aplicacion finalizada", "Fin", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion incorrecta", "Error", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Error" + n.getMessage());
            }
        } while (opcion != 7);

    }

}
