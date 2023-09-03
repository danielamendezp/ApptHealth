/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.util.List;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanDAL.S_ServiciosMedicosDAL;
import sisPlanEntidades.ServiciosMedicos;
import sisPlanUtilitario.ValidadorFormatos;

/**
 * Clase DivisionTerritorialBLL: Clase de la capa de Lógica de Negocio que se conecta
 * con la capa DAL que contiene la persistencia de Datos con el archivo físico 
 * donde se almacena, actualiza y consulta los registros.
 * Referencia: Material de Curso
 * @author Ing.Daniela Mendez
 * @version 3.0
 * @since Julio 2023
 */
public class ServiciosMedicosBLL {

    //Objeto DAL para la persistencia de datos
    private S_ServiciosMedicosDAL oS_ServiciosMedicosDAL;

    /**
     * Constructor de la clase
     */
    public ServiciosMedicosBLL() {
        this.oS_ServiciosMedicosDAL = new S_ServiciosMedicosDAL();
    }

    public List<ServiciosMedicos> getListaServiciosMedicos(String categoria) {
        List<ServiciosMedicos> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oS_ServiciosMedicosDAL.getListaServiciosMedicos(categoria);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public ServiciosMedicos buscarServiciosMedicos(int pIdentificador) {
        return this.oS_ServiciosMedicosDAL.buscarServiciosMedicos(pIdentificador);
    }

     public void agregarServiciosMedicos(ServiciosMedicos pServiciosMedicos) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pServiciosMedicos.getIdServicio()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del ServiciosMedicos es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pServiciosMedicos.getIdServicio()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del ServiciosMedicos es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oS_ServiciosMedicosDAL.existeServiciosMedicos(String.valueOf(pServiciosMedicos.getIdServicio()))) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un ServiciosMedicos con el mismo código: " + pServiciosMedicos.getIdServicio(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oS_ServiciosMedicosDAL.agregarServiciosMedicos(pServiciosMedicos);
    }

    public void modificarServiciosMedicos(ServiciosMedicos pServiciosMedicos) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pServiciosMedicos.getIdServicio()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del ServiciosMedicos es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pServiciosMedicos.getIdServicio()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del ServiciosMedicos es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oS_ServiciosMedicosDAL.modificarServiciosMedicos(pServiciosMedicos);
    }

    public void eliminarServiciosMedicos(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del ServiciosMedicos es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oS_ServiciosMedicosDAL.eliminarServiciosMedicos(pCodigo);
    }
    
    public int consecutivo(){
        int num=1;
        if(this.oS_ServiciosMedicosDAL.getConsecutivo()==0){
            num=1;
        }else{
            num=this.oS_ServiciosMedicosDAL.getConsecutivo();
        }
        return num+1;
    }
    
    public ServiciosMedicos buscarServiciosMedicosPorNombre(String id) {
        ServiciosMedicos vServiciosMedicos;
        
        //Invocar método del DAL
        vServiciosMedicos = this.oS_ServiciosMedicosDAL.getServiciosMedicosPorNombre(id);
        
        //Retornar el objeto
        return vServiciosMedicos;
    }
}
