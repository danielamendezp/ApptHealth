/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanDAL.ProductosFarmaceuticosDAL;
import sisPlanEntidades.Producto;
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
public class ProductoBLL {

    //Objeto DAL para la persistencia de datos
    private ProductosFarmaceuticosDAL oProductoDAL;

    /**
     * Constructor de la clase
     */
    public ProductoBLL() {
        this.oProductoDAL= new  ProductosFarmaceuticosDAL();
    }

    /**
     * Obtener la lista completa de los Cantones de una Provincia
     * @param catProducto
     * @return Objeto List<> con todos los objetos Cantones
     * @throws java.text.ParseException
     */
    public List<Producto> getListaProductos(String catForma) throws ParseException {
        List<Producto> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oProductoDAL.getListaProducto(catForma);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public Producto buscarProducto(int pIdentificador) throws ParseException {
        return this.oProductoDAL.buscarProducto(pIdentificador);
    }
    
    public void agregarProducto(Producto pProducto) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pProducto.getIdProducto()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Producto es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pProducto.getIdProducto()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Producto es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oProductoDAL.existeProducto(String.valueOf(pProducto.getIdProducto()))) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Producto con el mismo código: " + pProducto.getIdProducto(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oProductoDAL.agregarProducto(pProducto);
    }

    public void modificarProducto(Producto pProducto) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pProducto.getIdProducto()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Producto es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pProducto.getIdProducto()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Producto es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oProductoDAL.modificarProducto(pProducto);
    }

    public void eliminarProducto(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Producto es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oProductoDAL.eliminarProducto(pCodigo);
    }
    public Producto buscarProducto(String pCodigo) {
        Producto vProducto;
        
        //Invocar método del DAL
        vProducto = this.oProductoDAL.buscarProducto(pCodigo);
        
        //Retornar el objeto
        return vProducto;
    }
    
    public Producto buscarProductoPorNombre(String id) {
        Producto vProducto;
        
        //Invocar método del DAL
        vProducto = this.oProductoDAL.getProductoPorNombre(id);
        
        //Retornar el objeto
        return vProducto;
    }
    
    public int consecutivo(){
        int num=1;
        if(this.oProductoDAL.getConsecutivo()==0){
            num=1;
        }else{
            num=this.oProductoDAL.getConsecutivo();
        }
        return num+1;
    }
}
