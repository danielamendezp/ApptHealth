
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.CitaDAL;
import sisPlanEntidades.Cita;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanUtilitario.ValidadorFormatos;

public class CitaBLL {
    //Objeto DAL para la persistencia de datos
    private CitaDAL oCitaDAL;
    
    public CitaBLL() {
        this.oCitaDAL = new CitaDAL();
    }

    public void agregarCita(Cita pCita) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(String.valueOf(pCita.getCodigo())))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Cita es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pCita.getCodigo()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Cita es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oCitaDAL.existeCita(String.valueOf(pCita.getCodigo()))) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Cita con el mismo código: " + pCita.getCodigo(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oCitaDAL.agregarCita(pCita);
    }

    public void modificarCita(Cita pCita) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pCita.getCodigo()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Cita es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(String.valueOf(pCita.getCodigo()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Cita es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oCitaDAL.modificarCita(pCita);
    }

    public void eliminarCita(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Cita es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oCitaDAL.eliminarCita(pCodigo);
    }
    public Cita buscarCita(String pCodigo) {
        Cita vCita;
        
        //Invocar método del DAL
        vCita = this.oCitaDAL.buscarCita(pCodigo);
        
        //Retornar el objeto
        return vCita;
    }
    
    public Cita buscarCitaCedula(String pCodigo) {
        Cita vCita;
        
        //Invocar método del DAL
        vCita = this.oCitaDAL.buscarCitaCedula(pCodigo);
        
        //Retornar el objeto
        return vCita;
    }

    public List<Cita> getListaCitasPorAproximacion(String[] pValores) throws ParseException {
        List<Cita> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oCitaDAL.getListaCitaesPorAproximacion(pValores);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public List<Cita> getListaCitas() throws ParseException{
        List<Cita> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oCitaDAL.getListaCita();
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }    
    
     public List<Cita> getListaCitasCedula(String idCedula) throws ParseException{
        List<Cita> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oCitaDAL.getListaCitaCedula(idCedula);
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }    
    
     public int consecutivo() throws ParseException{
        return this.oCitaDAL.getConsecutivo()+1;
    }
     
     public boolean existeCita(String pCodigo){
         return oCitaDAL.existeCita(pCodigo);
     }
     
     public boolean existeCitaFechaHoraDoctor(String pHora, String pFecha, String pDoctor){
         return oCitaDAL.existeCitaFechaHoraDoctor(pHora, pDoctor, pFecha);
     }
     
     public boolean existeCitaFechaPaciente(String pFecha, String pPaciente){
         return oCitaDAL.existeCitaFechaPaciente(pPaciente, pFecha);
     }
     public List<Cita> getListaFechaDoctor(String idFecha, String idDoctor) throws ParseException{
        List<Cita> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oCitaDAL.getListaCitaFechaDoctor(idFecha, idDoctor);
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }    
     
}
