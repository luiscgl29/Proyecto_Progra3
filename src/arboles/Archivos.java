/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author luis-
 */
public class Archivos {

    public String lectura(String ruta) {
        String texto = "";
        
        try {
            BufferedReader leer = new BufferedReader(new FileReader(ruta));
            String temp = "";
            String linea;
            while ((linea = leer.readLine()) != null) {
                temp = temp + linea + "\n";
            }
            
            texto = temp;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo");
        }

        return texto;
    }
}
