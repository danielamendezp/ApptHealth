/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.util.ArrayList;
import java.util.List;
import sisPlanEntidades.CategoriaEnfermedades;

public class EN_Categoria_EnfermedadesDAL  extends BaseDAL {

    public EN_Categoria_EnfermedadesDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\CATEGORIAS_ENFERMEDADES.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public CategoriaEnfermedades buscarCategoriaEnfermedades(String pIdentificador) {
        CategoriaEnfermedades oCategoriaEnfermedades= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oCategoriaEnfermedades = new CategoriaEnfermedades(vDatosRegistro[0], 
                                    vDatosRegistro[1]);
        return oCategoriaEnfermedades;
    }
    
    /**
     * Obtener la lista completa de las CategoriaEnfermedadess
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    
    public List<CategoriaEnfermedades> getListaCategoriaEnfermedades() {
        // Definición de variable entidad
        CategoriaEnfermedades oCategoriaEnfermedades= null;

        // Definir objeto ArrayList para cargar los objetos
        List<CategoriaEnfermedades> oArrayListCategoriaEnfermedadess = new ArrayList<>();

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
                    oCategoriaEnfermedades = new CategoriaEnfermedades(vDatosRegistro[0], vDatosRegistro[1]);

                    // Agregar la instancia a la colección
                    oArrayListCategoriaEnfermedadess.add(oCategoriaEnfermedades);
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

        return oArrayListCategoriaEnfermedadess;
    }
    
    public List<CategoriaEnfermedades> getListaCategoriaEnfermedadesORDENADO(char[] caracter) {
        
        // Definición de variable entidad
        CategoriaEnfermedades oCategoriaEnfermedades= null;

        // Definir objeto ArrayList para cargar los objetos
        List<CategoriaEnfermedades> oArrayListCategoriaEnfermedadess = new ArrayList<>();

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
                    //Para ordenar por orden alfabetico de los codigos
                    if(vDatosRegistro[0].charAt(0)==caracter[0]||vDatosRegistro[0].charAt(0)==caracter[1]){ //Solo los codigos que en su primera letra que son iguales a los caracteres, 
                        // Crear la instancia de Departamento
                        oCategoriaEnfermedades = new CategoriaEnfermedades(vDatosRegistro[0], vDatosRegistro[1]);

                        // Agregar la instancia a la colección
                        oArrayListCategoriaEnfermedadess.add(oCategoriaEnfermedades);
                    }
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

        return oArrayListCategoriaEnfermedadess;
    }
}
