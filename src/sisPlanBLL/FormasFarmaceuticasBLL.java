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
import sisPlanDAL.PR_FormasFarmaceuticasDAL;
import sisPlanEntidades.FormasFarmaceuticas;

public class FormasFarmaceuticasBLL {
   
     private PR_FormasFarmaceuticasDAL oFormasFarmaceuticos;
     
    public FormasFarmaceuticasBLL() {
         this.oFormasFarmaceuticos = new PR_FormasFarmaceuticasDAL();
    }
    
    public List<FormasFarmaceuticas> getListaFormaFarmaceutica() {
        List<FormasFarmaceuticas> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this. oFormasFarmaceuticos.getListaFormasFarmaceuticas();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public FormasFarmaceuticas buscarFormaFarmaceutica(int pIdentificador) {
        return this. oFormasFarmaceuticos.buscarFormasFarmaceuticas(pIdentificador);
    }
    
}
