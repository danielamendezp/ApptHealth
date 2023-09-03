/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import sisPlanUtilitario.Formatos;

public class ManejadorErrorSistemaDAL {
    private static boolean hayError = false;
    private static String identificadorError = "";
    private static String mensajeGeneral = "";
    private static String mensajeDetallado = "";
    private static String nombreClase = "";
    private static String nombreMetodo = "";
    private static String rutaArchivoDatos = ".\\src\\SisPlanDatos\\SP-Log.dat";

    public static void registrarError(String identificadorError, 
                                      String mensajeGeneral, 
                                      String mensajeDetallado,
                                      String nombreClase, 
                                      String nombreMetodo) {
        ManejadorErrorSistemaDAL.hayError = true;
        ManejadorErrorSistemaDAL.identificadorError = identificadorError;
        ManejadorErrorSistemaDAL.mensajeGeneral = mensajeGeneral;
        ManejadorErrorSistemaDAL.mensajeDetallado = mensajeDetallado;
        ManejadorErrorSistemaDAL.nombreClase = nombreClase;
        ManejadorErrorSistemaDAL.nombreMetodo = nombreMetodo;
        //Invocación de método a nivel de clase que registra en el archivo Log de Errores
        ManejadorErrorSistemaDAL.registrarLogError();
    }
 
    private static void registrarLogError(){
        //Creación de puntero de archivo tipo FileOutputStream
        FileOutputStream oArchivoDatos = null;
        
        try{
            oArchivoDatos = new FileOutputStream(rutaArchivoDatos, true);
            oArchivoDatos.write( getMensajeErrorCompletoLog().getBytes()  );

            if (oArchivoDatos != null) {
                oArchivoDatos.close();
                oArchivoDatos = null;
            }
            
        } catch (IOException vError) {
            ManejadorErrorSistemaDAL.hayError = true;
            ManejadorErrorSistemaDAL.identificadorError = "SP-1201";
            ManejadorErrorSistemaDAL.mensajeGeneral = "Error de lectura/escritura en el registro del Log de Errores.";
            ManejadorErrorSistemaDAL.mensajeDetallado = vError.toString();
            ManejadorErrorSistemaDAL.nombreClase = Thread.currentThread().getStackTrace()[2].getClassName();
            ManejadorErrorSistemaDAL.nombreMetodo = Thread.currentThread().getStackTrace()[2].getMethodName();
        }
        
    }

    public static void limpiarError() {
        ManejadorErrorSistemaDAL.hayError = false;
        ManejadorErrorSistemaDAL.identificadorError = "";
        ManejadorErrorSistemaDAL.mensajeGeneral = "";
        ManejadorErrorSistemaDAL.mensajeDetallado = "";
        ManejadorErrorSistemaDAL.nombreClase = "";
        ManejadorErrorSistemaDAL.nombreMetodo = "";
    }

    public static String getIdentificadorError() {
        return ManejadorErrorSistemaDAL.identificadorError;
    }

    public static boolean getHayError() {
        return ManejadorErrorSistemaDAL.hayError;
    }

    public static String getMensajeGeneral() {
        return ManejadorErrorSistemaDAL.mensajeGeneral;
    }

    public static String getMensajeDetallado() {
        return ManejadorErrorSistemaDAL.mensajeDetallado;
    }

    public static String getNombreClase() {
        return ManejadorErrorSistemaDAL.nombreClase;
    }

    public static String getNombreMetodo() {
        return nombreMetodo;
    }

    public static String getMensajeErrorCompleto(){
        String vHilera = "";
        
        if (ManejadorErrorSistemaDAL.hayError){
            vHilera = "Fecha/Hora : " + Formatos.conFormatoFechaHoraMinutosSegundos( Calendar.getInstance().getTime() ) +
                      "\nNo. de Error : " + ManejadorErrorSistemaDAL.identificadorError +
                      "\nDescripción del error : " + ManejadorErrorSistemaDAL.mensajeGeneral +
                      "\nDetalle del error : " + ManejadorErrorSistemaDAL.mensajeDetallado +
                      "\nClase : [" + ManejadorErrorSistemaDAL.nombreClase + "]" + 
                      "\nMétodo : [" + ManejadorErrorSistemaDAL.nombreMetodo + "]\n\n";                   
        }else {
            vHilera = "No hay errores registrados.";
        }
        
        return vHilera;    
    }

    public static String getMensajeErrorCompletoLog(){
        String vHilera = "";
        
        if (ManejadorErrorSistemaDAL.hayError){
            vHilera = "Fecha/Hora            : " + Formatos.conFormatoFechaHoraMinutosSegundos( Calendar.getInstance().getTime() ) +
                      "\nNo. de Error          : " + ManejadorErrorSistemaDAL.identificadorError +
                      "\nDescripción del error : " + ManejadorErrorSistemaDAL.mensajeGeneral +
                      "\nDetalle del error     : " + ManejadorErrorSistemaDAL.mensajeDetallado +
                      "\nClase                 : [" + ManejadorErrorSistemaDAL.nombreClase + "]" + 
                      "\nMétodo                : [" + ManejadorErrorSistemaDAL.nombreMetodo + "]\n\n";                   
        }else {
            vHilera = "No hay errores registrados.";
        }
        
        return vHilera;    
    }    
}
