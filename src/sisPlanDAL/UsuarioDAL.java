/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanEntidades.ERolUsuario;
import sisPlanEntidades.Usuario;
import sisPlanUtilitario.Formatos;

public class UsuarioDAL  extends BaseDAL {

    public UsuarioDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\Usuario.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public void agregarUsuario(Usuario pUsuario) {
        if(existeUsuario(String.valueOf(pUsuario.getCodigo()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Usuario con estos datos!");
        }else{
            this.agregarLinea(pUsuario.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Usuario agregado con exito!");
        }
    }

    public void modificarUsuario(Usuario pUsuario) {
        if(!existeUsuario(String.valueOf(pUsuario.getCodigo()))){
            JOptionPane.showMessageDialog(null, "¡Este Usuario no existe!");
        }else{
            this.modificarLinea(String.valueOf(pUsuario.getCodigo()), 
                            this.numeroColumnaLlavePrimaria, 
                            pUsuario.toStringArchivo());
            
        }
    }

    public void eliminarUsuario(String pCodigo) {
         if(!existeUsuario(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Usuario con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           this.numeroColumnaLlavePrimaria);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }

    public Usuario buscarUsuarioIngreso() {
        Usuario oUsuario= null;
        Usuario usuarioIngresoReciente = null;
        Date fechaMasReciente = null;
        Date fechaComparacion = null;

        List<Usuario> oListaUsuarios = new ArrayList<>();

        List<String> oListaRegistros = this.getListaRegistros();
        for (String vRegistro : oListaRegistros) {
            String[] vDatosRegistro = vRegistro.split(";");
            ERolUsuario rol = ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3], rol, vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            oListaUsuarios.add(oUsuario);
        }

        for (Usuario usuario : oListaUsuarios) {
            // Comprobar si esta es la fecha más reciente
            fechaComparacion = usuario.getIngreso();
            if (fechaMasReciente == null || fechaComparacion.after(fechaMasReciente)) {
                fechaMasReciente = fechaComparacion;
                usuarioIngresoReciente = usuario;
            }
        }

        return usuarioIngresoReciente;
    }
    
    public Usuario buscarUsuario(String pCodigo) {
        Usuario oUsuario = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
         if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            
        }
         
        return oUsuario;
    }
    
    public Usuario buscarUsuarioUserName(String pCodigo) {
        Usuario oUsuario = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          0);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            
        }
        return oUsuario;
    }
    
    public boolean buscarUsuarioEstado(String pCodigo) {
        Usuario oUsuario = null;
        String vLineaRegistro;
        boolean estado=false;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          1);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            
        }
        if(oUsuario.getEstadoUsuario().equals("Activo")){
            estado=true;
        }
        return estado;
    }
    
    public Usuario buscarUsuarioContrasena(String pCodigo) {
        Usuario oUsuario = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          3);
         if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            
        }
        return oUsuario;
    }
    
    public List<Usuario> getListaUsuarioPorAproximacion(String[] pValores) throws ParseException {
        //Variable para la instancia Entidad
        Usuario oUsuario= null;
        
        List<Usuario> oListaUsuarioes = new ArrayList<>();
        
        int[] vIndices = new int[1];
        vIndices[0] = 0;
        
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
                ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
                oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
                oListaUsuarioes.add(oUsuario);
            }
        }
        return oListaUsuarioes;
    }
    
    public boolean existeUsuario(String pCodigo) {
        return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }

    public List<Usuario> getListaUsuario() throws ParseException {
        Usuario oUsuario= null;
        
        List<Usuario> oListaUsuarioes = new ArrayList<>();
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            oListaUsuarioes.add(oUsuario);
        }
        
        return oListaUsuarioes;
    }

 public int getConsecutivo() throws ParseException{
        Usuario oUsuario= null;
        List<Usuario> oListaUsuario = new ArrayList<>();
        int max = 0;
        List<String> oListaRegistros = this.getListaRegistros();
        
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            oListaUsuario.add(oUsuario);
            
            // Encontrar el número más grande 
            int min = oUsuario.getCodigo();
            if (min > max) {
                max = min;
            }else{
                max=max;
            }
        }
        
        return max;
    }     
 
    /**
     *
     * @param id
     * @return
     * @throws ParseException
     */
    public Usuario getUsuarioUsername(String id) throws ParseException {
        //Definición de variable entidad
        Usuario oUsuario = null;

        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = id;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 1;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia 
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            
        }

        return oUsuario;
    }
    
    public Usuario getUsuarioPassword(String id) throws ParseException {
        //Definición de variable entidad
        Usuario oUsuario = null;

        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = id;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 3;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia 
            ERolUsuario rol=ERolUsuario.buscsarNivel(vDatosRegistro[4]);
            oUsuario = new Usuario(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], vDatosRegistro[2], vDatosRegistro[3],rol,vDatosRegistro[5], Formatos.obtenerFecha(vDatosRegistro[6]));
            
        }

        return oUsuario;
    }
}
