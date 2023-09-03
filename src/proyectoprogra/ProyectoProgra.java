/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoprogra;

import appSisPlan.jDlgUsuario;
import appSisPlan.jFrmMenuPrincipal;
import sisPlanUtilitario.UtilitarioVentana;

/**
 *
 * @author Daniela Fabiola
 */
public class ProyectoProgra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        jFrmMenuPrincipal menu= new jFrmMenuPrincipal();
        menu.setVisible(true);
         jDlgUsuario oJDlgUsuario=null;
        oJDlgUsuario = new jDlgUsuario(null, true);
        UtilitarioVentana.centrarVentanaJDialog(oJDlgUsuario);
    }
    
}
