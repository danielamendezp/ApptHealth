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
import sisPlanDAL.S_Categoria_Servicio_MedicoDAL;
import sisPlanEntidades.CategoriaServicioMedico;

public class CategoriaServicioMedicoBLL {
   
     private S_Categoria_Servicio_MedicoDAL oCategoriaServicioMedicoDAL;
     
    public CategoriaServicioMedicoBLL() {
         this.oCategoriaServicioMedicoDAL = new S_Categoria_Servicio_MedicoDAL();
    }
    
    public List<CategoriaServicioMedico> getListaCategoriaServicioMedico() {
        List<CategoriaServicioMedico> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this. oCategoriaServicioMedicoDAL.getListaCategoriaServicioMedico();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public CategoriaServicioMedico buscarCategoriaServicioMedico(int pIdentificador) {
        return this. oCategoriaServicioMedicoDAL.buscarCategoriaServicioMedico(pIdentificador);
    }
    
}
