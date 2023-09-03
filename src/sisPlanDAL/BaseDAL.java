/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase BaseDAL: Base para la lectura y escritura de información en archivos
 * Referencia: Material de Curso
 * @author Ing. Daniela Mendez
 * @version 3.0
 * @since Julio 2019
 */
public abstract class BaseDAL {
    //para ruta del archivo de datos
    protected String rutaArchivoDatos = "";
    //definición del número de columna que representa la 
    //llave primaria del archivo de datos
    protected int numeroColumnaLlavePrimaria;
    //puntero del archivo de datos
    protected RandomAccessFile oArchivoDatos;
    //objeto manejador de archivos
    protected ManejadorArchivosDAL oManejadorArchivosDAL;

    /**
     * Método para abrir el Archivo de Datos por medio del componente DAL
     * @return True si el archivo se abrió en forma satisfactoria
     */
    public boolean abrirArchivoDatos() {
        boolean vResultado = true;

        //Invocar rutina que abre el archivo
        this.oArchivoDatos = 
            this.oManejadorArchivosDAL.abrirArchivoRandomAccessFile(this.rutaArchivoDatos);
        
        //Verificar si hay un error registrado en el 
        //Manejador de Errores del sistema
        if (ManejadorErrorSistemaDAL.getHayError()){
            vResultado = false;
        }  
        
        return vResultado;
    }

    /**
     * Cerrar el archivo de datos
     * Siempre que se abre el archivo debe invocarse este 
     * método que permite el cierre del archivo
     */
    public void cerrarArchivoDatos() {
        //Invoca del Manejador de Archivos rutina que cierra el archivo
        this.oManejadorArchivosDAL.cerrarArchivoRandomAccessFile(this.oArchivoDatos);
    }

    
    /**
     * Agregar un nueva línea al final del archivo de datos
     * @param pNuevaLinea Nueva línea de datos
     */
    public void agregarLinea(String pNuevaLinea){
        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            //Invocar lógia privada que permite abrir el archivo
            //desde el objeto ManejadorArchivosDAL
            if (this.abrirArchivoDatos()) {

                //Ubicar al final del archivo
                this.oArchivoDatos.seek(oArchivoDatos.length());

                //Crear archivo StringBuilder
                StringBuilder oSalida = new StringBuilder();

                //Agrega al objeto StringBuffer la hilera 
                //de la nueva línea para el archivo
                oSalida.append(pNuevaLinea);
                
                //Toda nueva línea debe agregar el enter
                oSalida.append("\n");

                //Ejecutar la escritura en el archivo
                this.oArchivoDatos.writeBytes(oSalida.toString());

                //Invocar lógica privada que cierra el archivo
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1101",
                                                    "Error de lectura/escritura registrando la nueva " +
                                                    "línea en el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1102",
                                                    "Error registrando la nueva " +
                                                    "línea en el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }
    
    /**
     * Modificar una línea existente en el archivo de datos
     * @param pValorLlave Valor llave a buscar para ubicar el registro a reemplazar
     * @param pIndiceValorLlave Posición del indice del valor llave
     * @param pLineaActualizar Nueva información que reemplazará la línea
     */
    public void modificarLinea(String pValorLlave, 
                               int pIndiceValorLlave, 
                               String pLineaActualizar){
        String vLineaRegistro;
        StringBuilder oSalida = new StringBuilder();

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                
                while (vLineaRegistro != null) {
                    //Descompone la línea en una estructura vectorizada
                    //donde cada elemento del vector son los datos
                    //de la línea separados por ";"
                    String[] vDatosRegistro = vLineaRegistro.split(";");

                    //Evaluar que valor llave no corresponda
                    //al registro a borrar
                    if (!vDatosRegistro[pIndiceValorLlave].trim().toUpperCase().equals(pValorLlave.trim().toUpperCase())) {
                        //Cada lectura del registro con readLinea no devuelve
                        //el caracter Enter
                        oSalida.append(vLineaRegistro);
                        oSalida.append("\n");
                    }else{
                        //Al encontrar el registro a modificar, debe registrar el nuevo registro
                        //Esto se hace así, ya que al reemplazar una fila existente, el contenido
                        //la nueva fila puede ser menor en tamaño que la anterior, dejando información
                        //a la derecha de la información del nuevo registro, dejando datos incorectos.
                        oSalida.append(pLineaActualizar);
                        oSalida.append("\n");                        
                    }
                    //Leer el siguiente registro
                    vLineaRegistro = this.oArchivoDatos.readLine();
                }//while
                //Limpia el contenido del archivo de datos
                //a cero bytes
                this.oArchivoDatos.setLength(0);
                //Agrega la nueva información almacenada
                this.oArchivoDatos.writeBytes(oSalida.toString());
                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1103", 
                                                    "Error de lectura/escritura al modificadar la línea en " +
                                                    "el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1104", 
                                                    "Error al modificadar la línea en " +
                                                    "el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }        
    }

    /**
     * Eliminar un registro del archivo de datos
     * @param pValorLlave Valor llave del registro a eliminar
     * @param pIndiceValorLlave Indice del valor llave
     */
    public void eliminarLinea(String pValorLlave, int pIndiceValorLlave) {
        String vLineaRegistro;
        StringBuilder oSalida = new StringBuilder(); 

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                while (vLineaRegistro != null) {
                    //Descompone al linea en una estructura vectorizada
                    //donde cada elemento del vector son los datos
                    //de la línea separados por ";"
                    String[] vDatosRegistro = vLineaRegistro.split(";");

                    //Evaluar que valor llave no corresponda 
                    //al registro a borrar
                    if (!vDatosRegistro[pIndiceValorLlave].trim().toUpperCase().equals(pValorLlave.trim().toUpperCase())) {
                        //Cada lectura del registro con readLinea no devuelve 
                        //el caracter Enter
                        oSalida.append(vLineaRegistro);
                        oSalida.append("\n");
                    }
                    //Leer el siguiente registro
                    vLineaRegistro = this.oArchivoDatos.readLine();
                }//while
                //Limpia el contenido del archivo de datos 
                //a cero bytes
                this.oArchivoDatos.setLength(0);
                //Agrega la nueva información almacenada
                this.oArchivoDatos.writeBytes(oSalida.toString()); 
                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1105", 
                                                    "Error de escritura/lectura al eliminar la línea en " +
                                                    "el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1106", 
                                                    "Error al eliminar la línea en " +
                                                    "el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }

    /**
     * Buscar una línea existente en el archivo de datos
     * @param pValorLlave Valor llave a buscar para ubicar el registro
     * @param pIndiceValorLlave Indice del valor llave
     * @return Información del registro
     */
    public String buscarLinea(String pValorLlave, int pIndiceValorLlave) {
        String vLineaRegistro;
        String vLineaRegistroEncontrado = "";

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                
                while (vLineaRegistro != null) {
                    //Descompone al linea en una estructura vectorizada
                    //donde cada elemento del vector son los datos
                    //de la línea separados por ";"
                    String[] vDatosRegistro = vLineaRegistro.split(";");

                    //Evaluar si el valor llave es el buscado
                    if (vDatosRegistro[pIndiceValorLlave].trim().toUpperCase().equals(pValorLlave.trim().toUpperCase())) {
                        //Si el valor concuerda con el valor llave
                        //implica que el registro fue encontrado
                        vLineaRegistroEncontrado = vLineaRegistro;
                        //No es necesario continuar con la lectura de 
                        //más registros, por eso se hace una salida
                        //forzada del método
                        break;
                    } else {
                        //Leer la siguiente línea
                        vLineaRegistro = this.oArchivoDatos.readLine();
                    }
                }//Fin while

                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1107", 
                                                    "Error de lectura/escritura al buscar la línea en el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1108", 
                                                    "Error al buscar la línea en el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        
        //Retornar el resultado
        return vLineaRegistroEncontrado;
    }

    /**
     * Permite realizar busquedas en cualquier archivo por valores llaves exactos
     * @param pValores Vector con los valores a buscar
     * @param pIndices Vector con los indices de las ubicaciones dentro del archivo de datos
     * @return List con los registros que concordaron con los valores
     */
    public List<String> buscarLineasPorLlaves(String[] pValores, int[] pIndices) {
        String vLineaRegistro;
        boolean vRegistroEncontrado;
        List<String> oListaRegistros = new ArrayList<>();

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                
                while (vLineaRegistro != null) {
                    //Descompone al linea en una estructura vectorizada
                    //donde cada elemento del vector son los datos
                    //de la línea separados por ";"
                    String[] vDatosRegistro = vLineaRegistro.split(";");
                    
                    //Banderar de registro encontrado  en false
                    vRegistroEncontrado = false;
                    
                    //Hacer ciclo por la cantidad de elementos del vector de pIndices
                    for (int vIndice = 0; vIndice < pIndices.length; vIndice++) {
                        //Evaluar que venga valores
                        if (!pValores[vIndice].trim().equals("")) {
                            //Evaluar si el valor llave es el buscado
                            if (vDatosRegistro[ pIndices[vIndice] ].trim().toUpperCase().equals(pValores[vIndice].trim().toUpperCase())) {
                                vRegistroEncontrado = true;
                            }else{
                                vRegistroEncontrado = false;
                                //hacer quiebre del ciclo ya que encontró un valor
                                //diferente, lo que hace que el registro no cumpla
                                //con todos los condicionales
                                break;
                            }
                        }else{
                            //Si llega aquí, implica que el vector de valores que
                            //se mando viene con un dato vacío, lo que hace incorrecto
                            //la busqueda
                            vRegistroEncontrado = false;
                            //hacer quiebre del ciclo ya que encontró un valor
                            //diferente, lo que hace que el registro no cumpla
                            //con todos los condicionales
                            break;                            
                        }
                    }//fin del for

                    //Evaluar si el registro era el correcto
                    //con todos los valores llave para retornar el registro
                    //Cualquier valor incorrecto no lo retorna
                    if (vRegistroEncontrado == true) {
                        //Agregar el registro al List<>
                        oListaRegistros.add(vLineaRegistro);
                    }
                    
                    //Leer la siguiente línea
                    vLineaRegistro = this.oArchivoDatos.readLine();
                
                }//fin del while

                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }//fin del if
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1109", 
                                                    "Error de lectura/escritura al buscar las líneas en el " +
                                                    "archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1110", 
                                                    "Error al buscar las líneas en el " +
                                                    "archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        //Retornar el resultado
        return oListaRegistros;
    }
    
    /**
     * Permite realizar busquedas en cualquier archivo por aproximación de valores
     * @param pValores Vector con los valores a buscar
     * @param pIndices Vector con los indices de las ubicaciones dentro del archivo de datos
     * @return List con los registros que concordaron con los valores
     */
    public List<String> buscarLineasPorAproximacion(String[] pValores, int[] pIndices) {
        String vLineaRegistro;
        List<String> oListaRegistros = new ArrayList<>();

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                
                while (vLineaRegistro != null) {
                    //Descompone al linea en una estructura vectorizada
                    //donde cada elemento del vector son los datos
                    //de la línea separados por ";"
                    String[] vDatosRegistro = vLineaRegistro.split(";");
                    
                    //Hacer ciclo por la cantidad de indeces a buscar
                    for(int vIndice = 0; vIndice < pIndices.length; vIndice++){
                        //Evaluar que venga valores
                        if (!pValores[vIndice].trim().equals("")){
                            //Evaluar si el valor llave es el buscado
                            if ( vDatosRegistro[ pIndices[vIndice] ].trim().toUpperCase().contains(pValores[vIndice].trim().toUpperCase() ) ) {
                                //Agregar el registro al List
                                oListaRegistros.add(vLineaRegistro);
                                //Hacer una salida forzada del For ya que 
                                //la evaluación hizo que el valor del registro
                                //ya fuera considerado y no hace falta evaluar
                                //los otros campos
                                break;
                            }
                        }
                    }//Fin for
                    //Leer la siguiente línea
                    vLineaRegistro = this.oArchivoDatos.readLine();
                }//fin while

                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1111", 
                                                    "Error de lectura/escritura al buscar las líneas en el archivo " + 
                                                    "de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1112", 
                                                    "Error al buscar las líneas en el archivo " + 
                                                    "de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        
        //Retornar el resultado
        return oListaRegistros;
    }
    
    /**
     * Determina si un valor llave existe dentro del archivo de datos
     * @param pValorLlave Valor llave a buscar para ubicar el registro
     * @param pIndiceValorLlave Indice del valor llave
     * @return True si el valor llave existe dentro del arhivo
     */
    public boolean existeLinea(String pValorLlave, int pIndiceValorLlave) {
        String vLineaRegistro;
        boolean vResultado = false;

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                while (vLineaRegistro != null) {
                    //Descompone al linea en una estructura vectorizada
                    //donde cada elemento del vector son los datos
                    //de la línea separados por ";"
                    String[] vDatosRegistro = vLineaRegistro.split(";");

                    //Evaluar si el valor llave es el buscado
                    if ( vDatosRegistro[pIndiceValorLlave].trim().toUpperCase().equals(pValorLlave.trim().toUpperCase()) ) {
                        //Si el valor concuerda con la llave
                        //el registro fue encontrado
                        vResultado = true;
                        break;
                    } else {
                        //Leer la siguiente línea
                        vLineaRegistro = this.oArchivoDatos.readLine();
                    }
                }//fin while

                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1113", 
                                                    "Error de lectura/escritura al buscar la línea en el " +
                                                    "archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1114", 
                                                    "Error al buscar la línea en el " +
                                                    "archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        
        //Retornar el resultado
        return vResultado;        
    }

    public List<String> getListaRegistros() {
        String vLineaRegistro;
        List<String> oListaRegistros = new ArrayList<>();

        try {
            //Inicializar el control de errores
            ManejadorErrorSistemaDAL.limpiarError();

            if (this.abrirArchivoDatos()) {
                //Leer la primera línea, si hay datos inicia el ciclo
                vLineaRegistro = this.oArchivoDatos.readLine();
                while (vLineaRegistro != null) {
                    //Agregar el registro al List
                    oListaRegistros.add(vLineaRegistro);
                    //Leer la siguiente línea
                    vLineaRegistro = this.oArchivoDatos.readLine();
                }

                //Cerrar el archivo de datos
                this.cerrarArchivoDatos();
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1115", 
                                                    "Error de lectura/escritura al buscar las líneas en " +
                                                    "el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }  catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1116", 
                                                    "Error al buscar las líneas en " +
                                                    "el archivo de datos.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        
        //Retornar el resultado
        return oListaRegistros;
    }    
}
