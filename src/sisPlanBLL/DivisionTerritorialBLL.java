/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.util.List;
import sisPlanDAL.DR_DivisionTerritorialDAL;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanEntidades.Canton;
import sisPlanEntidades.Distrito;
import sisPlanEntidades.DivisionTerritorial;
import sisPlanEntidades.Provincia;
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
public class DivisionTerritorialBLL {

    //Objeto DAL para la persistencia de datos
    private DR_DivisionTerritorialDAL oDivisionTerritorialDAL;

    /**
     * Constructor de la clase
     */
    public DivisionTerritorialBLL() {
        this.oDivisionTerritorialDAL = new DR_DivisionTerritorialDAL();
    }

    /*
      ---------------------------------------------------------------------------
      Sección de comportamientos para lectura de Provincias, Cantones y Distritos
      en objetos Entidades respectivos
      ---------------------------------------------------------------------------
    */
    
    /**
     * Obtener la lista completa de las Provincias
     * @return Objeto List<> con todos los objetos Provincia
     */
    public List<Provincia> getListaProvincias() {
        List<Provincia> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDivisionTerritorialDAL.getListaProvincias();

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    /**
     * Obtener la lista completa de los Cantones de una Provincia
     * @param pNumeroProvincia Número de provincia de los cantones a listar
     * @return Objeto List<> con todos los objetos Cantones
     */
    public List<Canton> getListaCantones(int pNumeroProvincia) {
        List<Canton> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDivisionTerritorialDAL.getListaCantones(pNumeroProvincia);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    /**
     * Obtener la lista completa de los Distritos de un Cantón
     * @param pNumeroProvincia Número de Provincia
     * @param pNumeroCanton Número de Cantón
     * @return Objeto List<> con todos los objetos Distrito
     */
    public List<Distrito> getListaDistritos(int pNumeroProvincia, int pNumeroCanton) {
        List<Distrito> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDivisionTerritorialDAL.getListaDistritos(pNumeroProvincia, pNumeroCanton);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo provincia
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de provincia
     */
    public Provincia buscarProvincia(int pIdentificador) {
        return this.oDivisionTerritorialDAL.buscarProvincia(pIdentificador);
    }

    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo cantón
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de cantón
     */
    public Canton buscarCanton(int pIdentificador) {
        return this.oDivisionTerritorialDAL.buscarCanton(pIdentificador);
    }

    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo distrito
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de distrito
     */
    public Distrito buscarDistrito(int pIdentificador) {
        return this.oDivisionTerritorialDAL.buscarDistrito(pIdentificador);
    }    

    /*
     ---------------------------------------------------------------------------
     Sección de comportamientos para el mantenimiento de DivisionTerritorial.dat
     ---------------------------------------------------------------------------    
    */

    /**
     * Agregar una nueva División Territorial al medio de almacenamiento
     * @param pDivisionTerritorial Objeto DivisionTerritorial a agregar
     */
    public void agregarDivisionTerritorial(DivisionTerritorial pDivisionTerritorial) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pDivisionTerritorial.getNombre())){
            ManejadorErrorSistemaDAL.registrarError("SP-2601", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del lugar es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oDivisionTerritorialDAL.existeDivisionTerritorial(pDivisionTerritorial.getIdentificador())) {
            ManejadorErrorSistemaDAL.registrarError("SP-2602", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite el registro con el mismo identificador: " + pDivisionTerritorial.getIdentificador(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oDivisionTerritorialDAL.agregarDivisionTerritorial(pDivisionTerritorial);
    }

    /**
     * Modificar una División Territorial
     * @param pDivisionTerritorial Objeto DivisionTerritorial
     */
    public void modificarDivisionTerritorial(DivisionTerritorial pDivisionTerritorial) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pDivisionTerritorial.getNombre())){
            ManejadorErrorSistemaDAL.registrarError("SP-2603", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del lugar es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oDivisionTerritorialDAL.modificarDivisionTerritorial(pDivisionTerritorial);
    }

    /**
     * Eliminar un departamento
     * @param pIdentificador Identificador de la división territorial
     */
    public void eliminarDivisionTerritorial(Long pIdentificador) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (pIdentificador <= 0){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El identificador no puede ser igual o menor a cero", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oDivisionTerritorialDAL.eliminarDivisionTerritorial(pIdentificador);
    }

    /**
     * Buscar un elemento llave en los datos de la División Territorial
     * @param pIdentificador Valor llave a buscar
     * @return Objeto DivisionTerritorial
     */
    public DivisionTerritorial buscarDivisionTerritorial(Long pIdentificador) {
        DivisionTerritorial oDivisionTerritorial;
        
        //Invocar método del DAL
        oDivisionTerritorial = this.oDivisionTerritorialDAL.buscarDivisionTerritorial(pIdentificador);
        
        //Retornar el objeto
        return oDivisionTerritorial;
    }
    
    /**
     * Obtener la lista completa de la división territorial
     * por valores de aproximación
     * @param pValores Vector con los datos a buscar
     * @return List con los datos que concordaron
     */
    public List<DivisionTerritorial> getListaDivisionTerritorialPorAproximacion(String[] pValores) {
        List<DivisionTerritorial> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDivisionTerritorialDAL.getListaDivisionTerritorialPorAproximacion(pValores);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    /**
     * Obtener la lista completa de la división territorial
     * @return Objeto ArrayList con todos los objetos DivisionTerritorial
     */
    public List<DivisionTerritorial> getListaDivisionTerritorial() {
        List<DivisionTerritorial> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDivisionTerritorialDAL.getListaDivisionTerritorial();
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }    
}
