/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 * Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.PS_PacienteDAL;
import sisPlanEntidades.Paciente;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanUtilitario.ValidadorFormatos;

public class PacienteBLL {
    //Objeto DAL para la persistencia de datos
    private PS_PacienteDAL oPacienteDAL;
    
    public PacienteBLL() {
        this.oPacienteDAL = new PS_PacienteDAL();
    }

    public void agregarPaciente(Paciente pPaciente) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pPaciente.getCedula())){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Paciente es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(pPaciente.getNombreApellidos())){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Paciente es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oPacienteDAL.existePaciente(pPaciente.getCedula())) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Paciente con el mismo código: " + pPaciente.getCedula(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oPacienteDAL.agregarPaciente(pPaciente);
    }

    public void modificarPaciente(Paciente pPaciente) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pPaciente.getCedula())){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Paciente es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(pPaciente.getNombreApellidos())){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Paciente es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oPacienteDAL.modificarPaciente(pPaciente);
    }

    public void eliminarPaciente(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Paciente es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oPacienteDAL.eliminarPaciente(pCodigo);
    }
    public Paciente buscarPaciente(String pCodigo) {
        Paciente vPaciente;
        
        //Invocar método del DAL
        vPaciente = this.oPacienteDAL.buscarPaciente(pCodigo);
        
        //Retornar el objeto
        return vPaciente;
    }

    public List<Paciente> getListaPacientesPorAproximacion(String[] pValores) throws ParseException {
        List<Paciente> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oPacienteDAL.getListaPacientesPorAproximacion(pValores);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public List<Paciente> getListaPacientes() throws ParseException{
        List<Paciente> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oPacienteDAL.getListaPacientes();
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }    
}
