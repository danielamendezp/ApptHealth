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
import sisPlanDAL.PR_TitularesFarmaceuticosDAL;
import sisPlanEntidades.TitularFarmaceutica;

public class TitularFarmaceuticaBLL {
   
     private PR_TitularesFarmaceuticosDAL oTitularesFarmaceuticoDAL;
     
    public TitularFarmaceuticaBLL() {
         this.oTitularesFarmaceuticoDAL = new PR_TitularesFarmaceuticosDAL();
    }
    
    public List<TitularFarmaceutica> getListaTitularFarmaceuticas() {
        List<TitularFarmaceutica> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this. oTitularesFarmaceuticoDAL.getListaTitularFarmaceutica();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public TitularFarmaceutica buscarTitularFarmaceuticas(int pIdentificador) {
        return this. oTitularesFarmaceuticoDAL.buscarTitularFarmaceutica(pIdentificador);
    }
    
}
