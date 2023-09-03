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
import sisPlanBLL.EspecialidadBLL;
import sisPlanEntidades.Doctor;
import sisPlanUtilitario.Formatos;

public class PS_DoctorDAL  extends BaseDAL {

    public PS_DoctorDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\Doctor.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public void agregarDoctor(Doctor pDoctor) {
        if(existeDoctor(String.valueOf(pDoctor.getNumColegiado()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Doctor con estos datos!");
        }else{
            this.agregarLinea(pDoctor.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Doctor agregado con exito!");
        }
    }

    public void modificarDoctor(Doctor pDoctor) {
        if(!existeDoctor(String.valueOf(pDoctor.getNumColegiado()))){
            JOptionPane.showMessageDialog(null, "¡Esta persona no existe!");
        }else{
            this.modificarLinea(String.valueOf(pDoctor.getNumColegiado()), 
                            this.numeroColumnaLlavePrimaria, 
                            pDoctor.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarDoctor(String pCodigo) {
         if(!existeDoctor(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Doctor con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           1);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }

    public Doctor buscarDoctor(String pCodigo) {
        Doctor oDoctor = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            EspecialidadBLL especialidad=new EspecialidadBLL();
            DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
            oDoctor = new Doctor(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], Formatos.obtenerFecha(vDatosRegistro[3]), vDatosRegistro[4].charAt(0), vDatosRegistro[5], vDatosRegistro[6], vDatosRegistro[7],
                    divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[8])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[9])),
                    divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[10])), vDatosRegistro[11],especialidad.buscarEspecialidad(Integer.parseInt(vDatosRegistro[12])),  vDatosRegistro[13]);
        }
        return oDoctor;
    }
    
    public Doctor buscarDoctorCedula(String pCodigo) {
        Doctor oDoctor = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          1);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            EspecialidadBLL especialidad=new EspecialidadBLL();
            DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
            oDoctor = new Doctor(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], Formatos.obtenerFecha(vDatosRegistro[3]), vDatosRegistro[4].charAt(0), vDatosRegistro[5], vDatosRegistro[6], vDatosRegistro[7],
                    divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[8])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[9])),
                    divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[10])), vDatosRegistro[11],especialidad.buscarEspecialidad(Integer.parseInt(vDatosRegistro[12])),  vDatosRegistro[13]);
               }
        return oDoctor;
    }
    
    public List<Doctor> getListaDoctoresPorAproximacion(String[] pValores) throws ParseException {
        //Variable para la instancia Entidad
        Doctor oDoctor= null;
        
        List<Doctor> oListaDoctores = new ArrayList<>();
        
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
                EspecialidadBLL especialidad=new EspecialidadBLL();
                DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
                oDoctor = new Doctor(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], Formatos.obtenerFecha(vDatosRegistro[3]), vDatosRegistro[4].charAt(0), vDatosRegistro[5], vDatosRegistro[6], vDatosRegistro[7],
                    divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[8])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[9])),
                    divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[10])), vDatosRegistro[11],especialidad.buscarEspecialidad(Integer.parseInt(vDatosRegistro[12])),  vDatosRegistro[13]);
                oListaDoctores.add(oDoctor);
            }
        }
        return oListaDoctores;
    }
    
    public boolean existeDoctor(String pCodigo) {
        return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }

    public List<Doctor> getListaDoctor() throws ParseException {
        Doctor oDoctor=null;
        
        List<Doctor> oListaDoctores = new ArrayList<>();
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            EspecialidadBLL especialidad=new EspecialidadBLL();
            DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
            oDoctor = new Doctor(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], Formatos.obtenerFecha(vDatosRegistro[3]), vDatosRegistro[4].charAt(0), vDatosRegistro[5], vDatosRegistro[6], vDatosRegistro[7],
                    divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[8])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[9])),
                    divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[10])), vDatosRegistro[11],especialidad.buscarEspecialidad(Integer.parseInt(vDatosRegistro[12])),  vDatosRegistro[13]);
            oListaDoctores.add(oDoctor);
        }
        
        return oListaDoctores;
    }    
    
    public List<Doctor> getListaDoctorEspecialidad(String id) {
        //Definición de variable entidad
        Doctor oDoctor=null;

        //Definir objeto ArrayList para cargar los objetos
        List<Doctor> oArrayListDoctor = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = id;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 12;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);
        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            EspecialidadBLL especialidad=new EspecialidadBLL();
                DivisionTerritorialBLL divisionTerritorial=new DivisionTerritorialBLL();
                oDoctor = new Doctor(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], Formatos.obtenerFecha(vDatosRegistro[3]), vDatosRegistro[4].charAt(0), vDatosRegistro[5], vDatosRegistro[6], vDatosRegistro[7],
                    divisionTerritorial.buscarProvincia(Integer.parseInt(vDatosRegistro[8])), divisionTerritorial.buscarCanton(Integer.parseInt(vDatosRegistro[9])),
                    divisionTerritorial.buscarDistrito(Integer.parseInt(vDatosRegistro[10])), vDatosRegistro[11],especialidad.buscarEspecialidad(Integer.parseInt(vDatosRegistro[12])),  vDatosRegistro[13]);
            //Agregar la instancia a la colección
            oArrayListDoctor.add(oDoctor);
        
        }
        
        if(oArrayListDoctor.isEmpty()){
            oArrayListDoctor.add(null);
        }
        return oArrayListDoctor;
    }
}
