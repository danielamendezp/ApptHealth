/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.util.ArrayList;
import java.util.List;
import sisPlanEntidades.FormasFarmaceuticas;

public class PR_FormasFarmaceuticasDAL  extends BaseDAL {

    public PR_FormasFarmaceuticasDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos =  ".\\src\\sisPlanDatos\\FORMAS_FARMACEUTICAS.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

     public FormasFarmaceuticas buscarFormasFarmaceuticas(int pIdentificador) {
        FormasFarmaceuticas oFormasFarmaceuticas= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oFormasFarmaceuticas = new FormasFarmaceuticas(Integer.parseInt(vDatosRegistro[0]), 
                                    vDatosRegistro[1]);
        return oFormasFarmaceuticas;
    }
    
    /**
     * Obtener la lista completa de las FormasFarmaceuticass
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    
    public List<FormasFarmaceuticas> getListaFormasFarmaceuticas() {
        // Definición de variable entidad
        FormasFarmaceuticas oFormasFarmaceuticas= null;

        // Definir objeto ArrayList para cargar los objetos
        List<FormasFarmaceuticas> oArrayListFormasFarmaceuticass = new ArrayList<>();

        // Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.getListaRegistros();

        // Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            // Descomponer la línea en una estructura vectorizada
            // donde cada elemento del vector son los datos
            // de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");

            if (vDatosRegistro.length == 2) {
                try {
                    // Crear la instancia de Departamento
                    oFormasFarmaceuticas = new FormasFarmaceuticas(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1]);

                    // Agregar la instancia a la colección
                    oArrayListFormasFarmaceuticass.add(oFormasFarmaceuticas);
                } catch (NumberFormatException e) {
                    // Manejar la excepción si la conversión a entero falla
                    // Puedes registrar el error o tomar alguna acción específica aquí
                    ManejadorErrorSistemaDAL.registrarError("SP-1117",
                            "Error al convertir el dato a entero en la línea: " + vRegistro,
                            e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            } else {
                // Manejar la situación donde los datos de la línea no tienen el formato esperado
                ManejadorErrorSistemaDAL.registrarError("SP-1118",
                        "Formato de datos incorrecto en la línea: " + vRegistro,
                        "",
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName());
            }
        }

        return oArrayListFormasFarmaceuticass;
    }
}
