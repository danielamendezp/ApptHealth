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
import sisPlanDAL.CS_CategoriaEspecialidadesDAL;
import sisPlanEntidades.CategoriaEspecialidades;

public class CategoriaEspecialidadesBLL {
   
     private CS_CategoriaEspecialidadesDAL oCategoriaEspecialidadesDAL;
     
    public CategoriaEspecialidadesBLL() {
         this.oCategoriaEspecialidadesDAL = new CS_CategoriaEspecialidadesDAL();
    }
    
    public List<CategoriaEspecialidades> getListaCategoriaEspecialidades() {
        List<CategoriaEspecialidades> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oCategoriaEspecialidadesDAL.getListaCategoriaEspecialidades();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public CategoriaEspecialidades buscarCategoriaEspecialidades(int pIdentificador) {
        return this.oCategoriaEspecialidadesDAL.buscarCategoriaEspecialidades(pIdentificador);
    }
    
}
