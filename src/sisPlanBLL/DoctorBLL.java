
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.PS_DoctorDAL;
import sisPlanEntidades.Doctor;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanUtilitario.ValidadorFormatos;

public class DoctorBLL {
    //Objeto DAL para la persistencia de datos
    private PS_DoctorDAL oDoctorDAL;
    
    public DoctorBLL() {
        this.oDoctorDAL = new PS_DoctorDAL();
    }

    public void agregarDoctor(Doctor pDoctor) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pDoctor.getNumColegiado()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Doctor es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(pDoctor.getNombreApellidos())){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Doctor es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oDoctorDAL.existeDoctor(String.valueOf(pDoctor.getNumColegiado()))) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Doctor con el mismo código: " + pDoctor.getCedula(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oDoctorDAL.agregarDoctor(pDoctor);
    }

    public void modificarDoctor(Doctor pDoctor) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pDoctor.getNumColegiado()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Doctor es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(pDoctor.getNombreApellidos())){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Doctor es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oDoctorDAL.modificarDoctor(pDoctor);
    }

    public void eliminarDoctor(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Doctor es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oDoctorDAL.eliminarDoctor(pCodigo);
    }
    public Doctor buscarDoctor(String pCodigo) {
        Doctor vDoctor;
        
        //Invocar método del DAL
        vDoctor = this.oDoctorDAL.buscarDoctor(pCodigo);
        
        //Retornar el objeto
        return vDoctor;
    }
    
    public Doctor buscarDoctorCedula(String pCodigo) {
        Doctor vDoctor;
        
        //Invocar método del DAL
        vDoctor = this.oDoctorDAL.buscarDoctorCedula(pCodigo);
        
        //Retornar el objeto
        return vDoctor;
    }

    public List<Doctor> getListaDoctorsPorAproximacion(String[] pValores) throws ParseException {
        List<Doctor> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDoctorDAL.getListaDoctoresPorAproximacion(pValores);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public List<Doctor> getListaDoctors() throws ParseException{
        List<Doctor> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDoctorDAL.getListaDoctor();
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }    
    
    public List<Doctor> getListaDoctorEspecialidad(String id){
        List<Doctor> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oDoctorDAL.getListaDoctorEspecialidad(id);
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }  
}
