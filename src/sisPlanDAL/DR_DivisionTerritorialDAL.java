/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.util.ArrayList;
import java.util.List;
import sisPlanEntidades.Canton;
import sisPlanEntidades.Distrito;
import sisPlanEntidades.DivisionTerritorial;
import sisPlanEntidades.Provincia;
public class DR_DivisionTerritorialDAL extends BaseDAL {
    
    /**
     * Constructor de la clase
     */
    public DR_DivisionTerritorialDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\DIVISION_TERRITORIAL_CR.dat";
        //Definición del número de columna que representa la llave primaria
        //del archivo de datos
        this.numeroColumnaLlavePrimaria = 0;
        //Crear la instancia del manejador de archivos
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();
    }

    /*
      ---------------------------------------------------------------------------
      Sección de comportamientos para lectura de Provincias, Cantones y Distritos
      en objetos Entidades respectivos
      ---------------------------------------------------------------------------
    */
        
    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo provincia
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de provincia
     */
    public Provincia buscarProvincia(int pIdentificador){
        Provincia oProvincia= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        //Crear la instancia de Provincia
        oProvincia = new Provincia(Integer.parseInt(vDatosRegistro[0]), 
                                   Integer.parseInt(vDatosRegistro[1]), 
                                   vDatosRegistro[4]);
        return oProvincia;
    }

    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo cantón
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de cantón
     */
    public Canton buscarCanton(int pIdentificador) {
        Canton oCanton= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oCanton = new Canton(Integer.parseInt(vDatosRegistro[0]), 
                             Integer.parseInt(vDatosRegistro[1]), 
                             Integer.parseInt(vDatosRegistro[2]),
                             vDatosRegistro[4]);
        return oCanton;
    }

    /**
     * Método que busca en archivo el registro que concuerde con el valor
     * llave y retorna una instancia de tipo distrito
     * @param pIdentificador Valor llave a buscar dentro del archivo
     * @return Instancia de distrito
     */
    public Distrito buscarDistrito(int pIdentificador) {
        Distrito oDistrito= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oDistrito = new Distrito(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 Integer.parseInt(vDatosRegistro[2]),
                                 Integer.parseInt(vDatosRegistro[3]),
                                 vDatosRegistro[4]);
        return oDistrito;
    }
    
    /**
     * Obtener la lista completa de las provincias
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    public List<Provincia> getListaProvincias() {
        //Definición de variable entidad
        Provincia oProvincia= null;

        //Definir objeto ArrayList para cargar los objetos
        List<Provincia> oArrayListProvincias = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[2];
        
        //Todos los registros donde canton = 0 y distrito = 0
        //son las provincias
        vDatos[0] = "0";
        vDatos[1] = "0";
        
        //Posiciones dentro del archivo de DivisionTerritorial.dat
        //Canton = 2
        //Distrito = 3
        int[] vIndices = new int[2];
        vIndices[0] = 2;
        vIndices[1] = 3;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia de Departamento
            oProvincia = new Provincia(Integer.parseInt(vDatosRegistro[0]), 
                                       Integer.parseInt(vDatosRegistro[1]),
                                       vDatosRegistro[4]);
            
            //Agregar la instancia a la colección
            oArrayListProvincias.add(oProvincia);
        }

        return oArrayListProvincias;
    }
    
    /**
     * Obtener la lista completa de los cantones de una provincia
     * @param pNumeroProvincia Número de provincia para extraer los cantonoes
     * @return Lista con los cantones de la provincia
     */
    public List<Canton> getListaCantones(int pNumeroProvincia) {
        //Definición de variable entidad
        Canton oCanton= null;

        //Definir objeto ArrayList para cargar los objetos
        List<Canton> oArrayListCantones = new ArrayList<>();

        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[2];
        
        //Todos los registros donde provincia = pNumeroProvincia y distrito = 0
        //son los cantones de la provincia indicado
        vDatos[0] = pNumeroProvincia + "";
        vDatos[1] = "0";

        //Posiciones dentro del archivo datos
        //provincia = 1
        //distrito = 3
        int[] vIndices = new int[2];
        vIndices[0] = 1;
        vIndices[1] = 3;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia de Cantón
            oCanton = new Canton(Integer.parseInt(vDatosRegistro[0]), 
                                 Integer.parseInt(vDatosRegistro[1]), 
                                 Integer.parseInt(vDatosRegistro[2]),
                                 vDatosRegistro[4]);

            //Agregar la instancia a la colección
            oArrayListCantones.add(oCanton);
        }

        return oArrayListCantones;
    }

    /**
     * Obtener la lista completa de los distritos de una provincia y de un cantón particular
     * @param pNumeroProvincia Número de provincia para extraer los distritos
     * @param pNumeroCanton Número de cantón para extraer los distritos
     * @return Lista con los distritos del cantón y provincia dados
     */
    public List<Distrito> getListaDistritos(int pNumeroProvincia, int pNumeroCanton) {
        //Definición de variable entidad
        Distrito oDistrito= null;

        //Definir objeto ArrayList para cargar los objetos
        List<Distrito> oArrayListDistritos = new ArrayList<>();

        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[2];
        
        //Todos los registros donde provincia = pNumeroProvincia y canton = pNumeroCanton
        //son los distritos de la provincia y cantón indicados
        vDatos[0] = pNumeroProvincia + "";
        vDatos[1] = pNumeroCanton + "";

        //Posiciones dentro del archivo de datos
        //provincia = 1
        //canton = 2
        int[] vIndices = new int[2];
        vIndices[0] = 1;
        vIndices[1] = 2;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia de Departamento
            oDistrito = new Distrito(Integer.parseInt(vDatosRegistro[0]), 
                                     Integer.parseInt(vDatosRegistro[1]), 
                                     Integer.parseInt(vDatosRegistro[2]),
                                     Integer.parseInt(vDatosRegistro[3]),
                                     vDatosRegistro[4]);

            //Agregar la instancia a la colección
            oArrayListDistritos.add(oDistrito);
        }

        return oArrayListDistritos;
    }
    
    /*
     ---------------------------------------------------------------------------
     Sección de comportamientos para el mantenimiento de DivisionTerritorial.dat
     ---------------------------------------------------------------------------    
    */
    
    /**
     * Agregar una nueva División Territorial al medio de almacenamiento
     * @param pDivisionTerritorial Objeto DivisionTerritorial a agregar
     */
    public void agregarDivisionTerritorial(DivisionTerritorial pDivisionTerritorial) {
        this.agregarLinea(pDivisionTerritorial.toStringArchivo());
    }

    /**
     * Modificar una División Territorial
     * @param pDivisionTerritorial Objeto DivisionTerritorial
     */
    public void modificarDivisionTerritorial(DivisionTerritorial pDivisionTerritorial) {
        this.modificarLinea(pDivisionTerritorial.getIdentificador() + "", 
                            this.numeroColumnaLlavePrimaria, 
                            pDivisionTerritorial.toStringArchivo());
    }

    /**
     * Eliminar un departamento
     * @param pIdentificador Identificador de la división territorial
     */
    public void eliminarDivisionTerritorial(Long pIdentificador) {
        this.eliminarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);
    }

    /**
     * Buscar un elemento llave en los datos de la División Territorial
     * @param pIdentificador Valor llave a buscar
     * @return Objeto DivisionTerritorial
     */
    public DivisionTerritorial buscarDivisionTerritorial(Long pIdentificador) {
        //Crear la variable de la DivisionTerritorial
        DivisionTerritorial oDivisionTerritorial = null;
        
        //Variable para almacenar el valor del registro a buscar
        String vLineaRegistro;
        
        //Invocar método de la clase base que busca
        //un dato dentro del archivo de datos
        vLineaRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);
        
        //Evaluar que la línea tenga registros
        if (vLineaRegistro != null) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vLineaRegistro.split(";");
            
            //Crear la instancia
            oDivisionTerritorial = new DivisionTerritorial( Long.parseLong(vDatosRegistro[0]), 
                                                            Integer.parseInt(vDatosRegistro[1]),
                                                            Integer.parseInt(vDatosRegistro[2]),
                                                            Integer.parseInt(vDatosRegistro[3]),
                                                            vDatosRegistro[4] );
        }
        
        //Retornar la instancia
        return oDivisionTerritorial;
    }
    
    /**
     * Obtener la lista completa de la división territorial
     * por valores de aproximación
     * @param pValores Vector con los datos a buscar
     * @return List con los datos que concordaron
     */
    public List<DivisionTerritorial> getListaDivisionTerritorialPorAproximacion(String[] pValores) {
        //Variable para la instancia Entidad
        DivisionTerritorial oDivisionTerritorial= null;
        
        //Definir objeto ArrayList para cargar los objetos
        List<DivisionTerritorial> oListaDivisionTerritorial = new ArrayList<>();
        
        //Estructura con el orden a buscar en las columnas
        //dentro del archivo de datos para pValores
        //Cada DAL es responsable de saber en que columnas buscar
        //para este método
        int[] vIndices = new int[5];
        vIndices[0] = 0;
        vIndices[1] = 1;
        vIndices[2] = 2;
        vIndices[3] = 3;
        vIndices[4] = 4;
        
        //Validar las dimensiones de las estructuras
        if (pValores.length != vIndices.length) {
            ManejadorErrorSistemaDAL.registrarError("SP-1501",
                                                    "Error de tamaños de estructuras",
                                                    "Los tamaños de los vectores de datos e índices son diferentes, debe verificar.", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());            
        }else {
            //Definir objeto ArrayList para la lista de los Registros en formato String
            List<String> oListaRegistros = this.buscarLineasPorAproximacion(pValores, 
                                                                            vIndices);

            //Iterar la colección con un foreach de Java
            for (String vRegistro : oListaRegistros) {
                //Descompone al línea en una estructura vectorizada
                //donde cada elemento del vector son los datos
                //de la línea separados por ";"
                String[] vDatosRegistro = vRegistro.split(";");
                //Crear la instancia de DivisionTerritorial
                oDivisionTerritorial = new DivisionTerritorial( Long.parseLong(vDatosRegistro[0]), 
                                                                Integer.parseInt(vDatosRegistro[1]),
                                                                Integer.parseInt(vDatosRegistro[2]),
                                                                Integer.parseInt(vDatosRegistro[3]),
                                                                vDatosRegistro[4] );
                //Agregar la instancia a la colección
                oListaDivisionTerritorial.add(oDivisionTerritorial);
            }
        }
        //Retornar la colección de Entidades
        return oListaDivisionTerritorial;
    }
    
    /**
     * Determinar si un identificador existe en división territorial
     * @param pIdentificacion Identificación de la división territorial a a buscar
     * @return True el código existe en los registros de división territorial
     */
    public boolean existeDivisionTerritorial(Long pIdentificacion) {
        return this.existeLinea(pIdentificacion + "", this.numeroColumnaLlavePrimaria);
    }

    /**
     * Obtener la lista completa de la división territorial
     * @return Objeto ArrayList con todos los objetos DivisionTerritorial
     */
    public List<DivisionTerritorial> getListaDivisionTerritorial() {
        DivisionTerritorial oDivisionTerritorial= null;
        
        //Definir objeto ArrayList para cargar los objetos
        List<DivisionTerritorial> oListaDivisionTerritorial = new ArrayList<>();
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.getListaRegistros();
        
        //Iterar la colección con un foreach de Java
        for(String vRegistro : oListaRegistros){
            //Descompone al línea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            //Crear la instancia de Departamento
            oDivisionTerritorial = new DivisionTerritorial( Long.parseLong(vDatosRegistro[0]), 
                                                            Integer.parseInt(vDatosRegistro[1]),
                                                            Integer.parseInt(vDatosRegistro[2]),
                                                            Integer.parseInt(vDatosRegistro[3]),
                                                            vDatosRegistro[4] );
            //Agregar la instancia a la colección
            oListaDivisionTerritorial.add(oDivisionTerritorial);
        }
        
        return oListaDivisionTerritorial;
    }    
}
