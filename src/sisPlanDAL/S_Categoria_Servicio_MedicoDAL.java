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
import sisPlanEntidades.CategoriaServicioMedico;

public class S_Categoria_Servicio_MedicoDAL  extends BaseDAL {

    public S_Categoria_Servicio_MedicoDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\CATEGORIAS_SERVICIOS_MEDICOS.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

     public CategoriaServicioMedico buscarCategoriaServicioMedico(int pIdentificador) {
        CategoriaServicioMedico oCategoriaServicioMedico= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oCategoriaServicioMedico = new CategoriaServicioMedico(Integer.parseInt(vDatosRegistro[0]), 
                                    vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]));
        return oCategoriaServicioMedico;
    }
    
    /**
     * Obtener la lista completa de las CategoriaServicioMedicos
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    
    public List<CategoriaServicioMedico> getListaCategoriaServicioMedico() {
        // Definición de variable entidad
        CategoriaServicioMedico oCategoriaServicioMedico= null;

        // Definir objeto ArrayList para cargar los objetos
        List<CategoriaServicioMedico> oArrayListCategoriaServicioMedicos = new ArrayList<>();

        // Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.getListaRegistros();

        // Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            // Descomponer la línea en una estructura vectorizada
            // donde cada elemento del vector son los datos
            // de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");

            if (vDatosRegistro.length == 3) {
                try {
                    // Crear la instancia de Departamento
                    oCategoriaServicioMedico = new CategoriaServicioMedico(Integer.parseInt(vDatosRegistro[0]), 
                                    vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]));

                    // Agregar la instancia a la colección
                    oArrayListCategoriaServicioMedicos.add(oCategoriaServicioMedico);
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

        return oArrayListCategoriaServicioMedicos;
    }
}
