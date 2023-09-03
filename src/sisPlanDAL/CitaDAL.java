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
import sisPlanBLL.DoctorBLL;
import sisPlanBLL.PacienteBLL;
import sisPlanEntidades.Cita;
import sisPlanUtilitario.Formatos;

public class CitaDAL  extends BaseDAL {

    public CitaDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\Cita.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public void agregarCita(Cita pCita) {
        if(existeCita(String.valueOf(pCita.getCodigo()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Cita con estos datos!");
        }else{
            this.agregarLinea(pCita.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Cita agregado con exito!");
        }
    }

    public void modificarCita(Cita pCita) {
        if(!existeCita(String.valueOf(pCita.getCodigo()))){
            JOptionPane.showMessageDialog(null, "¡Esta persona no existe!");
        }else{
            this.modificarLinea(String.valueOf(pCita.getCodigo()), 
                            this.numeroColumnaLlavePrimaria, 
                            pCita.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarCita(String pCodigo) {
         if(!existeCita(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Cita con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           0);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }

    public Cita buscarCita(String pCodigo) {
        Cita oCita = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            DoctorBLL doctor= new DoctorBLL();
            PacienteBLL paciente=new PacienteBLL();
            oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                    vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
        }
        return oCita;
    }
    
    public Cita buscarCitaCedula(String pCodigo) {
        Cita oCita = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          4);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            DoctorBLL doctor= new DoctorBLL();
            PacienteBLL paciente=new PacienteBLL();
            oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                    vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
               }
        return oCita;
    }
    
    public List<Cita> getListaCitaesPorAproximacion(String[] pValores) throws ParseException {
        //Variable para la instancia Entidad
         Cita oCita=null;
        
        List<Cita> oListaCitaes = new ArrayList<>();
        
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
                DoctorBLL doctor= new DoctorBLL();
                PacienteBLL paciente=new PacienteBLL();
                oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                        vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
                oListaCitaes.add(oCita);
            }
        }
        return oListaCitaes;
    }
    
    public boolean existeCita(String pCodigo) {
        return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }
    
    public boolean existeCitaFechaHoraDoctor(String pHora, String pDoctor, String pFecha) {
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[3];
        vDatos[0] =pFecha;
        vDatos[1] = pHora;
        vDatos[2] = pDoctor;
        //Posiciones dentro del archivo 
        int[] vIndices = new int[3];
        vIndices[0] = 1;
        vIndices[1] = 2;
        vIndices[2] = 3;
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);
        if(oListaRegistros.size()==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existeCitaFechaPaciente(String pPaciente, String pFecha) {
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[2];
        vDatos[0] =pFecha;
        vDatos[1] = pPaciente;
        //Posiciones dentro del archivo 
        int[] vIndices = new int[2];
        vIndices[0] = 1;
        vIndices[1] = 4;
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);
        if(oListaRegistros.size()==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existeCitaFecha(String pCodigo) {
        return this.existeLinea(pCodigo, 1);
    }
    
    public boolean existeCitaDoctor(String pCodigo) {
        return this.existeLinea(pCodigo, 3);
    }
    
    public List<Cita> getListaCita() throws ParseException {
         Cita oCita=null;
        
        List<Cita> oListaCitaes = new ArrayList<>();
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            DoctorBLL doctor= new DoctorBLL();
            PacienteBLL paciente=new PacienteBLL();
            oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                    vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
            oListaCitaes.add(oCita);
        }
        
        return oListaCitaes;
    }    
    
    public int getConsecutivo() throws ParseException{
         Cita oCita=null;
        List<Cita> oListaCitas = new ArrayList<>();
        int max = 0;
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            DoctorBLL doctor= new DoctorBLL();
            PacienteBLL paciente=new PacienteBLL();
            oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                    vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
                
                oListaCitas.add(oCita);
             
            // Encontrar el número más grande 
            int min = oCita.getCodigo();
            if (min > max) {
                max = min;
            }else{
                max=max;
            }
        }
        
        return max;
    } 
    
    public List<Cita> getListaCitaFechaDoctor(String idFecha, String idDoctor) throws ParseException {
        //Definición de variable entidad
        Cita oCita=null;

        //Definir objeto ArrayList para cargar los objetos
        List<Cita> oArrayListCita = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[2];
        vDatos[0] = idFecha;
        vDatos[1] = idDoctor;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[2];
        vIndices[0] = 1;
        vIndices[1] = 3;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            DoctorBLL doctor= new DoctorBLL();
            PacienteBLL paciente=new PacienteBLL();
            oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                    vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
            //Agregar la instancia a la colección
            oArrayListCita.add(oCita);
        }

        return oArrayListCita;
    }
    
    public List<Cita> getListaCitaCedula(String idCedula) throws ParseException {
        //Definición de variable entidad
         Cita oCita=null;

        //Definir objeto ArrayList para cargar los objetos
        List<Cita> oArrayListCita = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        vDatos[0] = idCedula;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 4;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            DoctorBLL doctor= new DoctorBLL();
            PacienteBLL paciente=new PacienteBLL();
            oCita = new Cita(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), 
                    vDatosRegistro[2],doctor.buscarDoctor( vDatosRegistro[3]), paciente.buscarPaciente( vDatosRegistro[4]));
            //Agregar la instancia a la colección
            oArrayListCita.add(oCita);
        }

        return oArrayListCita;
    }
}
