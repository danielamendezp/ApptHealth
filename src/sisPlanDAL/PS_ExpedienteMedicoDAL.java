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
import sisPlanBLL.PacienteBLL;
import sisPlanBLL.UsuarioBLL;
import sisPlanEntidades.ExpedienteMedico;
import sisPlanUtilitario.Formatos;

public class PS_ExpedienteMedicoDAL  extends BaseDAL {

    public PS_ExpedienteMedicoDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\ExpedienteMedico.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public void agregarExpedienteMedico(ExpedienteMedico pExpedienteMedico) {
        if(existeExpedienteMedico(String.valueOf(pExpedienteMedico.getNumExpediente()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un ExpedienteMedico con estos datos!");
        }else{
            this.agregarLinea(pExpedienteMedico.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡ExpedienteMedico agregado con exito!");
        }
        
    }

    public void modificarExpedienteMedico(ExpedienteMedico pExpedienteMedico) {
        if(existeExpedienteMedico(String.valueOf(pExpedienteMedico.getNumExpediente()))){
            JOptionPane.showMessageDialog(null, "¡No existe un ExpedienteMedico con estos datos!");
        }else{
            this.modificarLinea(String.valueOf(pExpedienteMedico.getNumExpediente()), 
                            this.numeroColumnaLlavePrimaria, 
                            pExpedienteMedico.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarExpedienteMedico(String pCodigo) {
         if(!existeExpedienteMedico(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un ExpedienteMedico con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           this.numeroColumnaLlavePrimaria);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
         }
    }
    
    
    public ExpedienteMedico buscarExpedienteMedico(String pCodigo) {
        ExpedienteMedico oExpedienteMedico = null;
        String vLineaRegistro=null;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
         if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            UsuarioBLL usuario= new UsuarioBLL();
            PacienteBLL paciente=new PacienteBLL();
            oExpedienteMedico = new ExpedienteMedico(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), usuario.buscarUsuario(vDatosRegistro[2]), paciente.buscarPaciente(vDatosRegistro[3]));
        }
        return oExpedienteMedico;
    }
    
    
    public ExpedienteMedico buscarExpedienteMedicoPorCedula(String cedula) {
        ExpedienteMedico oExpedienteMedico = null;
        String vLineaRegistro=null;
        vLineaRegistro = this.buscarLinea(cedula, 
                                          3);
        if (!vLineaRegistro.equals("")) {
            
            String[] vDatosRegistro = vLineaRegistro.split(";");
            UsuarioBLL usuario= new UsuarioBLL();
            PacienteBLL paciente=new PacienteBLL();
            oExpedienteMedico = new ExpedienteMedico(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), usuario.buscarUsuario(vDatosRegistro[2]), paciente.buscarPaciente(vDatosRegistro[3]));
        }
        return oExpedienteMedico;
    }
    
    
    public List<ExpedienteMedico> getListaExpedienteMedicosPorAproximacion(String[] pValores) throws ParseException {
        //Variable para la instancia Entidad
        ExpedienteMedico oExpedienteMedico= null;
        
        List<ExpedienteMedico> oListaExpedienteMedicos = new ArrayList<>();
        
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
                UsuarioBLL usuario= new UsuarioBLL();
                PacienteBLL paciente=new PacienteBLL();
                oExpedienteMedico = new ExpedienteMedico(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), usuario.buscarUsuario(vDatosRegistro[2]), paciente.buscarPaciente(vDatosRegistro[3]));
                oListaExpedienteMedicos.add(oExpedienteMedico);
            }
        }
        return oListaExpedienteMedicos;
    }
    
    public boolean existeExpedienteMedico(String pCodigo) {
        return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }

    public List<ExpedienteMedico> getListaExpedienteMedicos() throws ParseException {
        ExpedienteMedico oExpedienteMedico= null;
        
        List<ExpedienteMedico> oListaExpedienteMedicos = new ArrayList<>();
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            UsuarioBLL usuario= new UsuarioBLL();
            PacienteBLL paciente=new PacienteBLL();
            oExpedienteMedico = new ExpedienteMedico(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), usuario.buscarUsuario(vDatosRegistro[2]), paciente.buscarPaciente(vDatosRegistro[3]));
            oListaExpedienteMedicos.add(oExpedienteMedico);
        }
        
        return oListaExpedienteMedicos;
    }    
    
    public int getConsecutivoExpediente(){
        ExpedienteMedico oExpedienteMedico= null;
        int maxExpedienteMedico = 0;
        List<ExpedienteMedico> oListaExpedienteMedicos = new ArrayList<>();
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            UsuarioBLL usuario= new UsuarioBLL();
            PacienteBLL paciente=new PacienteBLL();
            oExpedienteMedico = new ExpedienteMedico(Integer.parseInt(vDatosRegistro[0]), Formatos.obtenerFecha(vDatosRegistro[1]), usuario.buscarUsuario(vDatosRegistro[2]), paciente.buscarPaciente(vDatosRegistro[3]));
            oListaExpedienteMedicos.add(oExpedienteMedico);
            // Encontrar el número más grande de ExpedienteMedico
            int expedienteMedicoActual = oExpedienteMedico.getNumExpediente();
            if (expedienteMedicoActual > maxExpedienteMedico) {
                maxExpedienteMedico = expedienteMedicoActual;
            }else{
                maxExpedienteMedico=maxExpedienteMedico;
            }
        }
        
        return maxExpedienteMedico;
    }  
    
    
}
