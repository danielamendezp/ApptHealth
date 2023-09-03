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
import sisPlanEntidades.CategoriaEspecialidades;

public class CS_CategoriaEspecialidadesDAL extends BaseDAL {
    
    /**
     * Constructor de la clase
     */
    public CS_CategoriaEspecialidadesDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\CategoriasEspecialidadesMedicas.dat";
       
        //Definición del número de columna que representa la llave primaria
        //del archivo de datos
        this.numeroColumnaLlavePrimaria = 0;
        //Crear la instancia del manejador de archivos
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();
    }
  
    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo CategoriaEspecialidades
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de CategoriaEspecialidades
     */
    public CategoriaEspecialidades buscarCategoriaEspecialidades(int pIdentificador){
        CategoriaEspecialidades oCategoriaEspecialidades;

        //Definir objeto ArrayList para la lista de los Registros en formato String
         String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);
        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        //Crear la instancia de CategoriaEspecialidades
        oCategoriaEspecialidades = new CategoriaEspecialidades(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1]);
        return oCategoriaEspecialidades;
    }
    /**
     * Obtener la lista completa de las CategoriaEspecialidadess
     * @return Objeto ArrayList con todos los objetos 
     */
    public List<CategoriaEspecialidades> getListaCategoriaEspecialidades() {
        // Definición de variable entidad
        CategoriaEspecialidades oCategoriaEspecialidades;

        // Definir objeto ArrayList para cargar los objetos
        List<CategoriaEspecialidades> oArrayListCategoriaEspecialidadess = new ArrayList<>();

        // Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.getListaRegistros();

        // Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            // Descomponer la línea en una estructura vectorizada
            // donde cada elemento del vector son los datos
            // de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");


                try {
                    // Crear la instancia de Departamento
                    oCategoriaEspecialidades = new CategoriaEspecialidades(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1]);

                    // Agregar la instancia a la colección
                    oArrayListCategoriaEspecialidadess.add(oCategoriaEspecialidades);
                } catch (NumberFormatException e) {
                    // Manejar la excepción si la conversión a entero falla
                    // Puedes registrar el error o tomar alguna acción específica aquí
                    ManejadorErrorSistemaDAL.registrarError("SP-1117",
                            "Error al convertir el dato a entero en la línea: " + vRegistro,
                            e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName());
                }
        }

        return oArrayListCategoriaEspecialidadess;
    }
    
}
