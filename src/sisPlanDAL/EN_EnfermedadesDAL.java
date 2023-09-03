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
import sisPlanEntidades.Enfermedad;

public class EN_EnfermedadesDAL  extends BaseDAL {

    public EN_EnfermedadesDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\ENFERMEDADES.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }
    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo Enfermedad
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de Enfermedad
     */
    public Enfermedad buscarEnfermedad(String pIdentificador) {
        Enfermedad oEnfermedad= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia 
        oEnfermedad = new Enfermedad(vDatosRegistro[0], vDatosRegistro[1],
                                       vDatosRegistro[2]);
        return oEnfermedad;
    }
    
    /**
     * Obtener la lista completa de las provincias
     * @param idCategoria
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    public List<Enfermedad> getListaEnfermedad(String idCategoria) {
        //Definición de variable entidad
        Enfermedad oEnfermedad= null;

        //Definir objeto ArrayList para cargar los objetos
        List<Enfermedad> oArrayListEnfermedad = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = idCategoria;
        
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
            oEnfermedad = new Enfermedad(vDatosRegistro[0], vDatosRegistro[1],
                                       vDatosRegistro[2]);
            
            //Agregar la instancia a la colección
            oArrayListEnfermedad.add(oEnfermedad);
        }

        return oArrayListEnfermedad;
    }
    
      
    public void agregarEnfermedad(Enfermedad pEnfermedad) {
        if(existeEnfermedad(String.valueOf(pEnfermedad.getId_Enfermedad()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Enfermedad con estos datos!");
        }else{
            this.agregarLinea(pEnfermedad.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Enfermedad agregado con exito!");
        }
    }

    public void modificarEnfermedad(Enfermedad pEnfermedad) {
        if(!existeEnfermedad(String.valueOf(pEnfermedad.getId_Enfermedad()))){
            JOptionPane.showMessageDialog(null, "¡Esta enfermedad no existe!");
        }else{
            this.modificarLinea(String.valueOf(pEnfermedad.getId_Enfermedad()), 
                            this.numeroColumnaLlavePrimaria, 
                            pEnfermedad.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarEnfermedad(String pCodigo) {
         if(!existeEnfermedad(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Enfermedadcon estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           0);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }
    
    public boolean existeEnfermedad(String pCodigo) {
          return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }
    
    public int getConsecutivo() {
        List<String> oListaRegistros = this.getListaRegistros();
        int max = 0;

        for (String vRegistro : oListaRegistros) {
            String[] vDatosRegistro = vRegistro.split(";");

            // Obtener el código completo y separar la parte inicial de los números
            String codigoCompleto = vDatosRegistro[0];
            String codigoNumerico = codigoCompleto.substring(1); // Excluir la primera letra

            try {
                int numero = Integer.parseInt(codigoNumerico);
                if (numero > max) {
                    max = numero;
                }
            } catch (NumberFormatException e) {
                // En caso de que el análisis de números falle, puedes manejar la excepción aquí
                // Por ejemplo, imprimir un mensaje de error o realizar otro manejo necesario.
            }
        }

        return max;
    }

    
     public Enfermedad getEnfermedadPorNombre(String id){
        //Definición de variable entidad
        Enfermedad oEnfermedad = null;

        
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
            oEnfermedad = new Enfermedad(vDatosRegistro[0], vDatosRegistro[1],
                                       vDatosRegistro[2]);
            
        }

        return oEnfermedad;
    }
  
}
