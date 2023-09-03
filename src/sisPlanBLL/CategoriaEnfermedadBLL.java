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
import sisPlanDAL.EN_Categoria_EnfermedadesDAL;
import sisPlanEntidades.CategoriaEnfermedades;

public class CategoriaEnfermedadBLL {
   
     private EN_Categoria_EnfermedadesDAL oCategoriaEnfermedadDAL;
     
    public CategoriaEnfermedadBLL() {
         this.oCategoriaEnfermedadDAL = new EN_Categoria_EnfermedadesDAL();
    }
    
    public List<CategoriaEnfermedades> getListaCategoriaEnfermedad() {
        List<CategoriaEnfermedades> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this. oCategoriaEnfermedadDAL.getListaCategoriaEnfermedades();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public List<CategoriaEnfermedades> getListaCategoriaEnfermedadesORDENADO(char[] caracter) {
        List<CategoriaEnfermedades> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this. oCategoriaEnfermedadDAL.getListaCategoriaEnfermedadesORDENADO(caracter);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public CategoriaEnfermedades buscarCategoriaEnfermedad(String pIdentificador) {
        return this. oCategoriaEnfermedadDAL.buscarCategoriaEnfermedades(pIdentificador);
    }
    
}
