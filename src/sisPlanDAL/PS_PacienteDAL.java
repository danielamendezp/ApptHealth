/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanBLL.DivisionTerritorialBLL;
import sisPlanBLL.ProfesionBLL;
import sisPlanEntidades.EIngresos;
import sisPlanEntidades.Paciente;
import sisPlanEntidades.ENivelEscolaridad;
import sisPlanUtilitario.Formatos;

public class PS_PacienteDAL  extends BaseDAL {

    public PS_PacienteDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\Pacientes.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public void agregarPaciente(Paciente pPaciente) {
        if(existePaciente(pPaciente.getCedula())){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Paciente con estos datos!");
        }else{
            this.agregarLinea(pPaciente.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Paciente agregado con exito!");
        }
        
    }

    public void modificarPaciente(Paciente pPaciente) {
        if(!existePaciente(pPaciente.getCedula())){
            JOptionPane.showMessageDialog(null, "¡No existe un Paciente con estos datos!");
        }else{
            this.modificarLinea(pPaciente.getCedula(), 
                            this.numeroColumnaLlavePrimaria, 
                            pPaciente.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarPaciente(String pCodigo) {
         if(!existePaciente(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Paciente con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           this.numeroColumnaLlavePrimaria);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
         }
    }
    
    
    public Paciente buscarPaciente(String pCodigo) {
        Paciente oPaciente = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
                DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
                ProfesionBLL profesion=new ProfesionBLL();
                ENivelEscolaridad nivelEscolaridad =ENivelEscolaridad.buscsarNivel(vDatosRegistro[12]);
                
                EIngresos ingresos =EIngresos.buscarIngreso(vDatosRegistro[13]);
                oPaciente = new Paciente(vDatosRegistro[0], vDatosRegistro[1], Formatos.obtenerFecha(vDatosRegistro[2]), vDatosRegistro[3].charAt(0), vDatosRegistro[4], vDatosRegistro[5],
                        vDatosRegistro[6], divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[7])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[8])),
                        divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[9])), vDatosRegistro[10], nivelEscolaridad, ingresos, profesion.buscarProfesion(Integer.parseInt(vDatosRegistro[11])));
                
        }
        return oPaciente;
    }
    
    public List<Paciente> getListaPacientesPorAproximacion(String[] pValores) throws ParseException {
        //Variable para la instancia Entidad
        Paciente oPaciente= null;
        
        List<Paciente> oListaPacientes = new ArrayList<>();
        
        int[] vIndices = new int[2];
        vIndices[0] = 0;
        vIndices[1] = 1;
        
        //Validar las dimensiones de las estructuras
        if (pValores.length != vIndices.length) {
            ManejadorErrorSistemaDAL.registrarError("SP-1201",
                                                    "Error de tamaños de estructuras",
                                                    "Los tamaños de los vectores de datos e indices son diferentes, debe verificar.", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());            
        }else {
            List<String> oListaRegistros = this.buscarLineasPorAproximacion(pValores, 
                                                                            vIndices);
            for (String vRegistro : oListaRegistros) {
                String[] vDatosRegistro = vRegistro.split(";");
                DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
                ProfesionBLL profesion=new ProfesionBLL();
                ENivelEscolaridad nivelEscolaridad =ENivelEscolaridad.buscsarNivel(vDatosRegistro[12]);
                EIngresos ingresos =EIngresos.buscarIngreso(vDatosRegistro[13]);
                oPaciente = new Paciente(vDatosRegistro[0], vDatosRegistro[1], Formatos.obtenerFecha(vDatosRegistro[2]), vDatosRegistro[3].charAt(0), vDatosRegistro[4], vDatosRegistro[5],
                        vDatosRegistro[6], divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[7])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[8])),
                        divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[9])), vDatosRegistro[10], nivelEscolaridad, ingresos, profesion.buscarProfesion(Integer.parseInt(vDatosRegistro[11])));
                oListaPacientes.add(oPaciente);
            }
        }
        return oListaPacientes;
    }
    
    public boolean existePaciente(String pCodigo) {
        return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }

    public List<Paciente> getListaPacientes() throws ParseException {
        Paciente oPaciente= null;
        
        List<Paciente> oListaPacientes = new ArrayList<>();
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
                DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
                ProfesionBLL profesion=new ProfesionBLL();
                ENivelEscolaridad nivelEscolaridad =ENivelEscolaridad.buscsarNivel(vDatosRegistro[12]);
                EIngresos ingresos =EIngresos.buscarIngreso(vDatosRegistro[13]);
                oPaciente = new Paciente(vDatosRegistro[0], vDatosRegistro[1], Formatos.obtenerFecha(vDatosRegistro[2]), vDatosRegistro[3].charAt(0), vDatosRegistro[4], vDatosRegistro[5],
                        vDatosRegistro[6], divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[7])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[8])),
                        divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[9])), vDatosRegistro[10], nivelEscolaridad, ingresos, profesion.buscarProfesion(Integer.parseInt(vDatosRegistro[11])));
                oListaPacientes.add(oPaciente);
        }
        
        return oListaPacientes;
    }    
    
    
}
