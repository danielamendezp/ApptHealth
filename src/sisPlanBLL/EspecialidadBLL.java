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
import sisPlanDAL.CS_EspecialidadesDAL;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanEntidades.Especialidad;
import sisPlanUtilitario.ValidadorFormatos;

public class EspecialidadBLL {
   
     private CS_EspecialidadesDAL oEspecialidadDAL;
     
    public EspecialidadBLL() {
         this.oEspecialidadDAL = new CS_EspecialidadesDAL();
    }
    
    public List<Especialidad> getListaEspecialidad(int pCategoria) {
        List<Especialidad> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oEspecialidadDAL.getListaEspecialidades(pCategoria);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public Especialidad buscarEspecialidad(int pIdentificador) {
        return this.oEspecialidadDAL.buscarEspecialidad(pIdentificador);
    }
    
    public void agregarEspecialidad(Especialidad pEspecialidad) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pEspecialidad.getEspecialidadID()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Especialidad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pEspecialidad.getEspecialidadID()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Especialidad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oEspecialidadDAL.existeEspecialidad(String.valueOf(pEspecialidad.getEspecialidadID()))) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Especialidad con el mismo código: " + pEspecialidad.getEspecialidadID(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oEspecialidadDAL.agregarEspecialidad(pEspecialidad);
    }

    public void modificarEspecialidad(Especialidad pEspecialidad) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pEspecialidad.getEspecialidadID()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Especialidad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pEspecialidad.getEspecialidadID()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Especialidad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oEspecialidadDAL.modificarEspecialidad(pEspecialidad);
    }

    public void eliminarEspecialidad(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Especialidad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oEspecialidadDAL.eliminarEspecialidad(pCodigo);
    }
    
    public int consecutivo(){
        int num=1;
        if(this.oEspecialidadDAL.getConsecutivo()==0){
            num=1;
        }else{
            num=this.oEspecialidadDAL.getConsecutivo();
        }
        return num+1;
    }
    
     public Especialidad buscarEspecialidadPorNombre(String id) {
        Especialidad vEspecialidad;
        
        //Invocar método del DAL
        vEspecialidad = this.oEspecialidadDAL.getEspecialidadPorNombre(id);
        
        //Retornar el objeto
        return vEspecialidad;
    }
}
