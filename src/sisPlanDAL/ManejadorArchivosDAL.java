/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ManejadorArchivosDAL {

    public ManejadorArchivosDAL() {

    }
    
    public File abrirArchivoFile(String pRutaArchivo) {
        //Definir variable tipo File
        File vArchivo = null;

        //Limpiar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        try {
            //Abrir el archivo
            vArchivo = new File(pRutaArchivo);

        } catch (SecurityException vErrorSeguridadArchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1201", 
                                                    "Error de seguridad abriendo archivo.",
                                                    vErrorSeguridadArchivo.toString(),
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vErrorAchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1102", 
                                                    "Error general abriendo el archivo.",
                                                    vErrorAchivo.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }

        return vArchivo;
    }
    public RandomAccessFile abrirArchivoRandomAccessFile(String pRutaArchivo) {
        //Definir archivo tipo RanddomAccessFile
        RandomAccessFile vArchivo = null;
        //Limpiar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();

        try {
            //Abrir el archivo de Acceso Aleatorio para lectura y escritura
            vArchivo = new RandomAccessFile(pRutaArchivo, "rw");

        } catch (SecurityException vErrorSeguridadArchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1103",
                                                    "Error de seguridad abriendo el archivo de " +
                                                    "acceso aleatorio.",
                                                    vErrorSeguridadArchivo.toString(),
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (FileNotFoundException vErrorAchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1104", "El archivo no existe.",
                                                    vErrorAchivo.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vErrorAchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1105", "Error general abriendo archivo " +
                                                    "de acceso aleatorio.",
                                                    vErrorAchivo.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        
        return vArchivo;
    }

    public void cerrarArchivoFile(File pArchivo) {
        //Limpiar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        try {
            if (pArchivo != null) {
                pArchivo = null;
            }
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1106", 
                                                    "Error cerrando archivo.",
                                                    vError.toString(),
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }

    public void cerrarArchivoRandomAccessFile(RandomAccessFile pArchivo) {
        //Limpiar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        
        try {
            if (pArchivo != null) {
                pArchivo.close();
                pArchivo = null;
            }
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1007", 
                                                    "Error de escritura/lectura en el archivo de acceso aleatorio.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vError) {
            ManejadorErrorSistemaDAL.registrarError("SP-1108", 
                                                    "Error cerrando archivo de acceso aleatorio.",
                                                    vError.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }

    public void borrarArchivoFile(String pRutaArchivo) {
        //Limpiar el control de errores
        ManejadorErrorSistemaDAL.limpiarError();
        try {
            File vArchivo = new File(pRutaArchivo);
            vArchivo.delete();
            vArchivo = null;
        } catch (SecurityException vErrorSeguridadArchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1109", 
                                                    "Error de seguridad borrando el archivo.",
                                                    vErrorSeguridadArchivo.toString(), 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception vErrorAchivo) {
            ManejadorErrorSistemaDAL.registrarError("SP-1110", 
                                                    "Error borrando el archivo.",
                                                    vErrorAchivo.toString(),
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }    
}
