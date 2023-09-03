/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.util.List;
import sisPlanDAL.EN_EnfermedadesDAL;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanEntidades.Enfermedad;
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
public class EnfermedadBLL {

    //Objeto DAL para la persistencia de datos
    private EN_EnfermedadesDAL oEN_EnfermedadesDAL;

    /**
     * Constructor de la clase
     */
    public EnfermedadBLL() {
        this.oEN_EnfermedadesDAL = new EN_EnfermedadesDAL();
    }

    public List<Enfermedad> getListaEnfermedades(String catEnfermedad) {
        List<Enfermedad> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oEN_EnfermedadesDAL.getListaEnfermedad(catEnfermedad);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public Enfermedad buscarEnfermedad(String pIdentificador) {
        return this.oEN_EnfermedadesDAL.buscarEnfermedad(pIdentificador);
    }
    
    public void agregarEnfermedad(Enfermedad pEnfermedad) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pEnfermedad.getId_Enfermedad()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Enfermedad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pEnfermedad.getId_Enfermedad()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Enfermedad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oEN_EnfermedadesDAL.existeEnfermedad(String.valueOf(pEnfermedad.getId_Enfermedad()))) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Enfermedad con el mismo código: " + pEnfermedad.getId_Enfermedad(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oEN_EnfermedadesDAL.agregarEnfermedad(pEnfermedad);
    }

    public void modificarEnfermedad(Enfermedad pEnfermedad) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pEnfermedad.getId_Enfermedad()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Enfermedad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pEnfermedad.getId_Enfermedad()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Enfermedad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oEN_EnfermedadesDAL.modificarEnfermedad(pEnfermedad);
    }

    public void eliminarEnfermedad(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Enfermedad es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oEN_EnfermedadesDAL.eliminarEnfermedad(pCodigo);
    }
    
    public Enfermedad buscarEnfermedadPorNombre(String id) {
        Enfermedad vEnfermedad;
        
        //Invocar método del DAL
        vEnfermedad = this.oEN_EnfermedadesDAL.getEnfermedadPorNombre(id);
        
        //Retornar el objeto
        return vEnfermedad;
    }
    
    public int consecutivo(){
        int num=1;
        if(this.oEN_EnfermedadesDAL.getConsecutivo()==0){
            num=1;
        }else{
            num=this.oEN_EnfermedadesDAL.getConsecutivo();
        }
        return num+1;
    }
}
