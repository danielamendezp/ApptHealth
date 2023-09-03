/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 * Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.PS_RegistroPadecimientoDAL;
import sisPlanEntidades.RegistroPadecimiento;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanUtilitario.ValidadorFormatos;

public class RegistroPadecimientoBLL {
    //Objeto DAL para la persistencia de datos
    private PS_RegistroPadecimientoDAL oRegistroPadecimientoDAL;
    
    public RegistroPadecimientoBLL() {
        this.oRegistroPadecimientoDAL = new PS_RegistroPadecimientoDAL();
    }

    public void agregarRegistroPadecimiento(RegistroPadecimiento pRegistroPadecimiento) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        //Validar los datos de entrada
       if (ValidadorFormatos.validarVacio(String.valueOf(pRegistroPadecimiento.getCodigo()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del RegistroPadecimiento es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
             
        //Invocar lógica del DAL
        this.oRegistroPadecimientoDAL.agregarRegistroPadecimiento(pRegistroPadecimiento);
    }

    public void modificarRegistroPadecimiento(RegistroPadecimiento pRegistroPadecimiento) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pRegistroPadecimiento.getCodigo()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del RegistroPadecimiento es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oRegistroPadecimientoDAL.modificarRegistroPadecimiento(pRegistroPadecimiento);
    }

    public void eliminarRegistroPadecimiento(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del RegistroPadecimiento es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oRegistroPadecimientoDAL.eliminarRegistroPadecimiento(pCodigo);
    }
    public RegistroPadecimiento buscarRegistroPadecimiento(String pCodigo) throws ParseException {
        RegistroPadecimiento vRegistroPadecimiento;
        
        //Invocar método del DAL
        vRegistroPadecimiento = this.oRegistroPadecimientoDAL.buscarRegistroPadecimiento(pCodigo);
        
        //Retornar el objeto
        return vRegistroPadecimiento;
    }
    
    public RegistroPadecimiento buscarRegistroPadecimientoPorExpediente(String pCodigo) throws ParseException {
        RegistroPadecimiento vRegistroPadecimiento;
        
        //Invocar método del DAL
        vRegistroPadecimiento = this.oRegistroPadecimientoDAL.buscarRegistroPadecimientoPorExpediente(pCodigo);
        
        //Retornar el objeto
        return vRegistroPadecimiento;
    }
    
    public RegistroPadecimiento buscarRegistroPadecimientoPorDoctor(String pCodigo) throws ParseException {
        RegistroPadecimiento vRegistroPadecimiento;
        
        //Invocar método del DAL
        vRegistroPadecimiento = this.oRegistroPadecimientoDAL.buscarRegistroPadecimientoPorDoctor(pCodigo);
        
        //Retornar el objeto
        return vRegistroPadecimiento;
    }

//    public List<RegistroPadecimiento> getListaRegistroPadecimientosPorAproximacion(String[] pValores) throws ParseException {
//        List<RegistroPadecimiento> vListaArreglo;
//
//        //Invocar el método del DAL para obtener la lista
//        vListaArreglo = this.oRegistroPadecimientoDAL.getListaRegistroPadecimientosPorAproximacion(pValores);
//
//        //Retornar el objeto ArrayList
//        return vListaArreglo;
//    }
    
    public List<RegistroPadecimiento> getListaRegistroPadecimientos(String id) throws ParseException{
        List<RegistroPadecimiento> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oRegistroPadecimientoDAL.getRegistroPadecimiento(id);
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }   
    
    public int consecutivo() throws ParseException{
        int num=1;
        if(this.oRegistroPadecimientoDAL.getConsecutivo()==0){
            num=1;
        }else{
            num=this.oRegistroPadecimientoDAL.getConsecutivo();
        }
        return num+1;
    }
}
