/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanEntidades.Especialidad;

public class CS_EspecialidadesDAL extends BaseDAL {
    
    /**
     * Constructor de la clase
     */
    public CS_EspecialidadesDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\EspecialidadesMedicas (5).dat";
        //Definición del número de columna que representa la llave primaria
        //del archivo de datos
        this.numeroColumnaLlavePrimaria = 0;
        //Crear la instancia del manejador de archivos
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();
    }

    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo cantón
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de cantón
     */
    public Especialidad buscarEspecialidad(int pIdentificador) {
        Especialidad oEspecialidad;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oEspecialidad = new Especialidad(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 vDatosRegistro[2]);
        return oEspecialidad;
    }

    /**
     * Obtener la lista completa de las CategoriaEspecialidadess
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    /**
     * Obtener la lista completa de los Especialidades de una CategoriaEspecialidades
     * @param pNumeroCategoriaEspecialidades Número de CategoriaEspecialidades para extraer los Especialidadoes
     * @return Lista con los Especialidades de la CategoriaEspecialidades
     */
    public List<Especialidad> getListaEspecialidades(int pNumeroCategoriaEspecialidades) {
        //Definición de variable entidad
        Especialidad oEspecialidad;

        //Definir objeto ArrayList para cargar los objetos
        List<Especialidad> oArrayListEspecialidades = new ArrayList<>();

        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        //Todos los registros donde CategoriaEspecialidades = pNumeroCategoriaEspecialidades y distrito = 0
        //son los Especialidades de la CategoriaEspecialidades indicado
        vDatos[0] = pNumeroCategoriaEspecialidades + "";

        //Posiciones dentro del archivo datos
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
            
            //Crear la instancia de Cantón
            oEspecialidad = new Especialidad(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 vDatosRegistro[2]);

            //Agregar la instancia a la colección
            oArrayListEspecialidades.add(oEspecialidad);
        }

        return oArrayListEspecialidades;
    }

      
    public void agregarEspecialidad(Especialidad pEspecialidad) {
        if(existeEspecialidad(String.valueOf(pEspecialidad.getEspecialidadID()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Especialidad con estos datos!");
        }else{
            this.agregarLinea(pEspecialidad.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Especialidad agregado con exito!");
        }
    }

    public void modificarEspecialidad(Especialidad pEspecialidad) {
        if(!existeEspecialidad(String.valueOf(pEspecialidad.getEspecialidadID()))){
            JOptionPane.showMessageDialog(null, "¡Este Especialidad no existe!");
        }else{
            this.modificarLinea(String.valueOf(pEspecialidad.getEspecialidadID()), 
                            this.numeroColumnaLlavePrimaria, 
                            pEspecialidad.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarEspecialidad(String pCodigo) {
         if(!existeEspecialidad(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Especialidad con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           0);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }

    public Especialidad buscarEspecialidad(String pCodigo) {
        Especialidad oEspecialidad = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            //Crear la instancia de Especialidad
          oEspecialidad = new Especialidad(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 vDatosRegistro[2]);
        }
        return oEspecialidad;
    }
    
    public Especialidad buscarEspecialidadCedula(String pCodigo) {
        Especialidad oEspecialidad = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          1);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            //Crear la instancia de Especialidad
           oEspecialidad = new Especialidad(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 vDatosRegistro[2]);
               }
        return oEspecialidad;
    }
    
    public boolean existeEspecialidad(String pCodigo) {
          return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }
    
    public int getConsecutivo(){
        Especialidad oEspecialidad;
        List<Especialidad> oListaEspecialidad = new ArrayList<>();
        int max = 0;
        List<String> oListaRegistros = this.getListaRegistros();
        
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
             oEspecialidad = new Especialidad(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 vDatosRegistro[2]);
            oListaEspecialidad.add(oEspecialidad);
            
            // Encontrar el número más grande 
            int min = oEspecialidad.getEspecialidadID();
            if (min > max) {
                max = min;
            }else{
                max=max;
            }
        }
        
        return max;
    }   
    
    public Especialidad getEspecialidadPorNombre(String id){
        //Definición de variable entidad
        Especialidad oEspecialidad = null;

        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = id;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 2;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia 
            oEspecialidad = new Especialidad(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 vDatosRegistro[2]);
            
        }

        return oEspecialidad;
    }
}
