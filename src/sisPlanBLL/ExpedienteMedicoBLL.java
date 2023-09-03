/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 * Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.PS_ExpedienteMedicoDAL;
import sisPlanEntidades.ExpedienteMedico;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanUtilitario.ValidadorFormatos;

public class ExpedienteMedicoBLL {
    //Objeto DAL para la persistencia de datos
    private PS_ExpedienteMedicoDAL oExpedienteMedicoDAL;
    
    public ExpedienteMedicoBLL() {
        this.oExpedienteMedicoDAL = new PS_ExpedienteMedicoDAL();
    }

    public void agregarExpedienteMedico(ExpedienteMedico pExpedienteMedico) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        //Validar los datos de entrada
       if (ValidadorFormatos.validarVacio(String.valueOf(pExpedienteMedico.getNumExpediente()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del ExpedienteMedico es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
             
        //Invocar lógica del DAL
        this.oExpedienteMedicoDAL.agregarExpedienteMedico(pExpedienteMedico);
    }

    public void modificarExpedienteMedico(ExpedienteMedico pExpedienteMedico) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pExpedienteMedico.getNumExpediente()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del ExpedienteMedico es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oExpedienteMedicoDAL.modificarExpedienteMedico(pExpedienteMedico);
    }

    public void eliminarExpedienteMedico(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del ExpedienteMedico es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oExpedienteMedicoDAL.eliminarExpedienteMedico(pCodigo);
    }
    public ExpedienteMedico buscarExpedienteMedico(String pCodigo) throws ParseException {
        ExpedienteMedico vExpedienteMedico=null;
        
        //Invocar método del DAL
        vExpedienteMedico = this.oExpedienteMedicoDAL.buscarExpedienteMedico(pCodigo);
        
        //Retornar el objeto
        return vExpedienteMedico;
    }
    
    public ExpedienteMedico buscarExpedienteMedicoPorCedula(String pCodigo) throws ParseException {
        ExpedienteMedico vExpedienteMedico=null;
        
        //Invocar método del DAL
        vExpedienteMedico = this.oExpedienteMedicoDAL.buscarExpedienteMedicoPorCedula(pCodigo);
        
        //Retornar el objeto
        return vExpedienteMedico;
    }

    public List<ExpedienteMedico> getListaExpedienteMedicosPorAproximacion(String[] pValores) throws ParseException {
        List<ExpedienteMedico> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oExpedienteMedicoDAL.getListaExpedienteMedicosPorAproximacion(pValores);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public List<ExpedienteMedico> getListaExpedienteMedicos() throws ParseException {
        List<ExpedienteMedico> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oExpedienteMedicoDAL.getListaExpedienteMedicos();
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }  
    
    public int consecutivoExpedienteMedico(){
        int num=1;
        if(this.oExpedienteMedicoDAL.getConsecutivoExpediente()==0){
            num=1;
        }else{
            num=this.oExpedienteMedicoDAL.getConsecutivoExpediente();
        }
        return num+1;
    }
}
