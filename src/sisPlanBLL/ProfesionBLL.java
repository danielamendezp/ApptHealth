/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanBLL;
/**
 *
 * @author Daniela Fabiola
 */
import java.util.List;
import sisPlanDAL.P_ProfesionesDAL;
import sisPlanEntidades.Profesion;

public class ProfesionBLL {
   
     private P_ProfesionesDAL oProfesionsDAL;
     
    public ProfesionBLL() {
         this.oProfesionsDAL = new P_ProfesionesDAL();
    }
    
    public List<Profesion> getListaProfesion() {
        List<Profesion> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oProfesionsDAL.getListaProfesion();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public Profesion buscarProfesion(int pIdentificador) {
        return this.oProfesionsDAL.buscarProfesion(pIdentificador);
    }
    
}
