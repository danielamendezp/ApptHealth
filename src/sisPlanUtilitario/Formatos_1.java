/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanUtilitario;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que expone un conjunto de métodos para aplicar formatos númericos a
 * hileras de caracteres y valores númericos. Dichas hileras formateadas
 * pueden se utilizados en objeto gráficos de entradas de usuario
 * @author Ing. Daniela Mendez
 * @version 3.0
 * @since Agosto 2023
 */
public class Formatos_1 {
        /**
     * Aplicar formato numérico tipo entero a una hilera con separadores
     * Formato ###,###,###,##0
     * @param pHilera Hilera a aplicar formato 
     * @return Hilera con formato
     */
    public static String conFormatoEntero(String pHilera){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###,###,###,##0", caracteresSeparadoresDecimal);
        
        //Validar que el contenido sea de tipo Entero
        if (ValidadorFormatos.validarEntero(pHilera)){
            vResultado = formatoNumero.format(Integer.parseInt(pHilera));
        }else{
            //Si el contenido no es númerico debe limpiar
            vResultado = "";
        }
        
        return vResultado;
    }

    /**
     * Aplicar formato numérico tipo entero a una hilera con separadores
     * Formato ###,###,###,##0
     * @param pValor Valor Integer a aplicar formato 
     * @return Hilera con formato
     */
    public static String conFormatoEntero(Integer pValor){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###,###,###,##0", caracteresSeparadoresDecimal);
        
        vResultado = formatoNumero.format(pValor);
        
        return vResultado;
    }
    
    /**
     * Aplicar formato numérico tipo entero a una hilera sin separadores
     * Formato ###########0
     * @param pHilera Hilera a aplicar formato 
     * @return Hilera con formato
     */
    public static String sinFormatoEntero(String pHilera){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###########0", caracteresSeparadoresDecimal);
        
        //Quitar el separador numérico de la hilera antes de hacer la 
        //Conversión del tipo del dato
        vResultado = quitarSeparadorNumerico(pHilera);

        //Validar que el contenido sea de tipo Entero
        if (ValidadorFormatos.validarEntero(vResultado)){
            vResultado = formatoNumero.format(Integer.parseInt(vResultado));
        }else{
            //Si el contenido no es númerico debe limpiar
            vResultado = "";
        }
        
        return vResultado;
    }

    /**
     * Aplicar formato numérico tipo entero a una hilera sin separadores
     * Formato ###########0
     * @param pValor Valor Integer a aplicar formato 
     * @return Hilera con formato
     */
    public static String sinFormatoEntero(Integer pValor){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###########0", caracteresSeparadoresDecimal);
        
        vResultado = formatoNumero.format(pValor);
        
        return vResultado;
    }
    
    /**
     * Aplicar formato numérico tipo entero largo a una hilera con separadores
     * Formato ###,###,###,###,###,##0
     * @param pHilera Hilera a aplicar formato 
     * @return Hilera con formato
     */
    public static String conFormatoEnteroLargo(String pHilera){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###,###,###,###,###,##0", caracteresSeparadoresDecimal);
        
        //Validar que el contenido sea de tipo Entero
        if (ValidadorFormatos.validarLong(pHilera)){
            vResultado = formatoNumero.format(Integer.parseInt(pHilera));
        }else{
            //Si el contenido no es númerico debe limpiar
            vResultado = "";
        }
        
        return vResultado;
    }

    /**
     * Aplicar formato numérico tipo entero largo a una hilera con separadores
     * Formato ###,###,###,###,###,##0
     * @param pValor Valor Integer a aplicar formato 
     * @return Hilera con formato
     */
    public static String conFormatoEnteroLargo(Long pValor){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###,###,###,###,###,##0", caracteresSeparadoresDecimal);
        
        vResultado = formatoNumero.format(pValor);
        
        return vResultado;
    }
    
    /**
     * Aplicar formato numérico tipo entero largo a una hilera sin separadores
     * Formato #################0
     * @param pHilera Hilera a aplicar formato 
     * @return Hilera con formato
     */
    public static String sinFormatoEnteroLargo(String pHilera){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("#################0", caracteresSeparadoresDecimal);
        
        //Quitar el separador numérico de la hilera antes de hacer la 
        //Conversión del tipo del dato
        vResultado = quitarSeparadorNumerico(pHilera);

        //Validar que el contenido sea de tipo Entero
        if (ValidadorFormatos.validarLong(vResultado)){
            vResultado = formatoNumero.format(Integer.parseInt(vResultado));
        }else{
            //Si el contenido no es númerico debe limpiar
            vResultado = "";
        }
        
        return vResultado;
    }

    /**
     * Aplicar formato numérico tipo entero largo a una hilera sin separadores
     * Formato ###########0
     * @param pValor Valor Long a aplicar formato 
     * @return Hilera con formato
     */
    public static String sinFormatoEnteroLargo(long pValor){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("#################0", caracteresSeparadoresDecimal);
        
        vResultado = formatoNumero.format(pValor);
        
        return vResultado;
    }     
    
    /**
     * Aplicar formato numérico tipo punto flotante a una hilera con separadores
     * Formato ###,###,###,##0.00
     * @param pHilera Hilera a aplicar formato 
     * @return Hilera con formato
     */
    public static String conFormatoPuntoFlotante(String pHilera){
        String vResultado = "";
        
        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###,###,###,##0.00", caracteresSeparadoresDecimal);
        
        //Validar que el contenido sea de tipo Double
        if (ValidadorFormatos.validarDouble(pHilera)){
            vResultado = formatoNumero.format( Double.parseDouble(pHilera) );
        }else{
            //Si el contenido no es númerico debe limpiar
            vResultado = "";
        }
        
        return vResultado;
    }

    /**
     * Aplicar formato numérico tipo punto flotante a una hilera con separadores
     * Formato ###,###,###,##0.00
     * @param pValor Valor Double a aplicar formato 
     * @return Hilera con formato
     */
    public static String conFormatoPuntoFlotante(Double pValor){
        String vResultado = "";
        
        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###,###,###,##0.00", caracteresSeparadoresDecimal);
        
        vResultado = formatoNumero.format( pValor );
        
        return vResultado;
    }
    
    /**
     * Aplicar formato numérico tipo punto flotante a una hilera sin separadores
     * Formato ###########0.00
     * @param pHilera Hilera a aplicar formato 
     * @return Hilera con formato
     */
    public static String sinFormatoPuntoFlotante(String pHilera){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###########0.00", caracteresSeparadoresDecimal);

        //Quitar el separador numérico de la hilera antes de hacer la 
        //Conversión del tipo del dato
        vResultado = quitarSeparadorNumerico(pHilera);

        //Validar que el contenido sea de tipo Double
        if (ValidadorFormatos.validarDouble(vResultado)){
            vResultado = formatoNumero.format( Double.parseDouble(vResultado) );
        }else{
            //Si el contenido no es númerico debe limpiar
            vResultado = "";
        }
        
        return vResultado;
    }

    /**
     * Aplicar formato numérico tipo punto flotante a una hilera sin separadores
     * Formato ###########0.00
     * @param pValor Valor Double a aplicar formato 
     * @return Hilera con formato
     */
    public static String sinFormatoPuntoFlotante(Double pValor){
        String vResultado = "";

        //Crear instancia que permite definir los caracteres de separación decimal y de
        //miles a valores númericos en el objeto DecimalFormat
        DecimalFormatSymbols caracteresSeparadoresDecimal = new DecimalFormatSymbols();
        caracteresSeparadoresDecimal.setDecimalSeparator('.');
        caracteresSeparadoresDecimal.setGroupingSeparator(',');
        
        DecimalFormat formatoNumero = new DecimalFormat("###########0.00", caracteresSeparadoresDecimal);

        vResultado = formatoNumero.format( pValor );

        return vResultado;
    }
    
    /**
     * Aplicar formato tipo Date dd/MM/yyyy
     * @param pFechaOrigen Hilera a convertir en Fecha al formato indicado
     * @return Fecha en formato dd/MM/yyyy
     */
    public static String conFormatoFecha(String pFechaOrigen) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        String vResultado = "";
        Date vFecha;

        try {           
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pFechaOrigen );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( vFechaFormato.format(vFecha).equals(pFechaOrigen) ){
                vResultado = vFechaFormato.format(vFecha);  
            }
        } catch (ParseException e) {        
            vResultado = e.toString();
        } catch (Exception e) {        
            vResultado = e.toString();
        }
        return vResultado;
    }

    /**
     * Aplicar formato tipo Date dd/MM/yyyy hh:mm:ss aaa
     * HH --> Hora 24
     * @param pFechaOrigen Hilera a convertir en Fecha al formato indicado
     * @return Fecha en formato dd/MM/yyyy
     */
    public static String conFormatoFechaHoraMinutosSegundos(String pFechaOrigen) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String vResultado = "";
        Date vFecha;

        try {           
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pFechaOrigen );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( vFechaFormato.format(vFecha).equals(pFechaOrigen) ){
                vResultado = vFechaFormato.format(vFecha);  
            }
        } catch (ParseException e) {        
            vResultado = e.toString();
        } catch (Exception e){
            vResultado = e.toString();
        }
        return vResultado;
    }

    /**
     * Aplicar formato tipo Date dd/MM/yyyy
     * @param pFechaOrigen Date a convertir en Fecha al formato indicado
     * @return Fecha en formato dd/MM/yyyy
     */
    public static String conFormatoFecha(Date pFechaOrigen) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        String vResultado = "";

        try{
            
            vResultado = vFechaFormato.format(pFechaOrigen);  
        
        }catch (Exception oError) {
            vResultado = "";
        }

        return vResultado;
    }
        
    /**
     * Aplicar formato tipo Date dd/MM/yyyy  aaa
     * HH --> Hora 24
     * @param pFechaOrigen Date a convertir en Fecha al formato indicado
     * @return Fecha en formato dd/MM/yyyy
     */
    public static String conFormatoFechaHoraMinutosSegundos(Date pFechaOrigen) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String vResultado = "";
        
        try{
            
            vResultado = vFechaFormato.format(pFechaOrigen);  
        
        }catch (Exception oError) {
            vResultado = "";
        }

        return vResultado;
    }

    /**
     * Obtener una fecha Date en formato dd/MM/yyyy
     * @param pFechaOrigen Hilera a convertir en Fecha al formato indicado
     * @return Fecha en formato dd/MM/yyyy tipo Date
     */
    public static Date obtenerFecha(String pFechaOrigen) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        Date vFecha;

        try {           
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pFechaOrigen );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( !vFechaFormato.format(vFecha).equals(pFechaOrigen) ){
                vFecha = null;  
            }
        } catch (ParseException e) {        
            vFecha = null;
        } catch (Exception e) {
            vFecha = null;
        }            
        return vFecha;
    }    

    /**
     * Aplicar formato tipo Date dd/MM/yyyy HH:mm:ss
     * HH --> Hora 24
     * @param pFechaOrigen Hilera a convertir en Fecha al formato indicado
     * @return Fecha en formato dd/MM/yyyy
     */
    public static Date obtenerFechaHoraMinutosSegundos(String pFechaOrigen) {
        SimpleDateFormat vFechaFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date vFecha;

        try {           
            //Tomar el valor del jTxtValor y asignarlo a la variable tipo fecha con la mascara indicada
            vFecha = vFechaFormato.parse( pFechaOrigen );
            
            //Hacer una comparación del tipo de dato almacenado en la variable Date vFecha
            //Ya que si la misma es incorrecta va almacenar otra fecha.
            //Por ejemplo 30/02/2017 el vFechaFormato.parse(pValor) = 02/03/2017
            //Siendo una fecha invalida. 
            //Si la fecha la convierte y es correcta, al obtener el format de la fecha almacenada en vFecha
            //por medio del "parse" deberían ser iguala en la siguiente comparación
            if ( !vFechaFormato.format(vFecha).equals(pFechaOrigen) ){
                vFecha = null;  
            }
        } catch (ParseException e) {        
            vFecha = null;
        } catch (Exception e) {
            vFecha = null;
        }          
        return vFecha;
    }
    
    /**
     * Quitar el caracter de separador numérico en una hilera
     * @param pHilera Hilera a quitar el separador numérico
     * @return Hilera sin el separador numérico
     */
    public static String quitarSeparadorNumerico(String pHilera){
        String vHilera = "";
        
        vHilera = pHilera.replace(",", "");
        
        return vHilera;
    }    
}
