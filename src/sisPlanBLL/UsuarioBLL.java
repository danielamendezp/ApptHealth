/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanBLL;

import java.text.ParseException;
import java.util.List;
import sisPlanDAL.UsuarioDAL;
import sisPlanEntidades.Usuario;
import sisPlanDAL.ManejadorErrorSistemaDAL;
import sisPlanUtilitario.ValidadorFormatos;

public class UsuarioBLL {
    //Objeto DAL para la persistencia de datos
    private UsuarioDAL oUsuarioDAL;
    
    public UsuarioBLL() {
        this.oUsuarioDAL = new UsuarioDAL();
    }

    public void agregarUsuario(Usuario pUsuario) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(String.valueOf(pUsuario.getCodigo()))){
            ManejadorErrorSistemaDAL.registrarError("SP-2101", 
                                                    "Error de Tipos de Datos", 
                                                    "El código del Usuario es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(pUsuario.getIdUsuario())){
            ManejadorErrorSistemaDAL.registrarError("SP-2102", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Usuario es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Verificar que el nuevo código NO exista en la fuente de datos
        if (this.oUsuarioDAL.existeUsuario(pUsuario.getNombreUsuario())) {
            ManejadorErrorSistemaDAL.registrarError("SP-2103", 
                                                    "Error de Tipos de Datos",
                                                    "Ya exite un Usuario con el mismo código: " + pUsuario.getIdUsuario(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
                
        //Invocar lógica del DAL
        this.oUsuarioDAL.agregarUsuario(pUsuario);
    }

    public void modificarUsuario(Usuario pUsuario) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pUsuario.getIdUsuario())){
            ManejadorErrorSistemaDAL.registrarError("SP-2104", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Usuario es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        if (ValidadorFormatos.validarVacio(pUsuario.getNombreUsuario())){
            ManejadorErrorSistemaDAL.registrarError("SP-2105", 
                                                    "Error de Tipos de Datos",
                                                    "El nombre del Usuario es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
        
        //Invocar lógica del DAL
        this.oUsuarioDAL.modificarUsuario(pUsuario);
    }

    public void eliminarUsuario(String pCodigo) {
        //Inicializar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        //Validar los datos de entrada
        if (ValidadorFormatos.validarVacio(pCodigo)){
            ManejadorErrorSistemaDAL.registrarError("SP-2106", 
                                                    "Error de Tipos de Datos",
                                                    "El código del Usuario es requerido", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
            return;
        }
    
        //Invocar lógica del DAL
        this.oUsuarioDAL.eliminarUsuario(pCodigo);
    }
    public Usuario buscarUsuario(String pCodigo) {
        Usuario vUsuario;
        
        //Invocar método del DAL
        vUsuario = this.oUsuarioDAL.buscarUsuario(pCodigo);
        
        //Retornar el objeto
        return vUsuario;
    }
    
    public boolean buscarUsuarioEstado(String pCodigo) {
        //Retornar
        return this.oUsuarioDAL.buscarUsuarioEstado(pCodigo);
    }
    
    public Usuario buscarUsuarioUserName(String pCodigo) throws ParseException {
        Usuario vUsuario;
        
        //Invocar método del DAL
        vUsuario = this.oUsuarioDAL.getUsuarioUsername(pCodigo);
        
        //Retornar el objeto
        return vUsuario;
    }
    
    public Usuario buscarUsuarioContrasenna(String pCodigo) throws ParseException {
        Usuario vUsuario;
        
        //Invocar método del DAL
        vUsuario = this.oUsuarioDAL.getUsuarioPassword(pCodigo);
        
        //Retornar el objeto
        return vUsuario;
    }
    
    public Usuario buscarUsuarioUltimoIngreso() {
        Usuario vUsuario;
        
        //Invocar método del DAL
        vUsuario = this.oUsuarioDAL.buscarUsuarioIngreso();
        
        //Retornar el objeto
        return vUsuario;
    }

    public List<Usuario> getListaUsuariosPorAproximacion(String[] pValores) throws ParseException {
        List<Usuario> vListaArreglo;

        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oUsuarioDAL.getListaUsuarioPorAproximacion(pValores);

        //Retornar el objeto ArrayList
        return vListaArreglo;
    }
    
    public List<Usuario> getListaUsuarios() throws ParseException{
        List<Usuario> vListaArreglo;
        
        //Invocar el método del DAL para obtener la lista
        vListaArreglo = this.oUsuarioDAL.getListaUsuario();
        
        //Retornar el objeto ArrayList
        return vListaArreglo;
    }

    public int consecutivo() throws ParseException{
        int num=1;
        if(this.oUsuarioDAL.getConsecutivo()==0){
            num=1;
        }else{
            num=this.oUsuarioDAL.getConsecutivo();
        }
        return num+1;
    }
}
