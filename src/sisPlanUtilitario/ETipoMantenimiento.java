/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanUtilitario;

/**
 * Clase ETipoMantenimiento para indicar a la aplicación el tipo
 * de acción en mantenimiento y procesos
 * @author Ing. Daniela Mendez
 * @version 3.0
 * @since Agosto 2023
 */
public enum ETipoMantenimiento {
    
    AGREGAR  ("A", "Agregar"),
    MODIFICAR("M", "Modificar"),
    CONSULTAR("C", "Consultar"),
    ELIMINAR ("E", "Eliminar"),
    PROCESAR ("P", "Procesar");
    
    private String tipoMantenimiento;
    private String descripcionMantenimiento;

    /**
     * Constructor con parámetros
     * @param pTipoMantenimiento Tipo de Mantenimiento
     * @param pDescripcionMantenimiento Descripción del Mantenimiento
     */
    private ETipoMantenimiento(String pTipoMantenimiento,
                               String pDescripcionMantenimiento){
        this.tipoMantenimiento = pTipoMantenimiento;
        this.descripcionMantenimiento = pDescripcionMantenimiento;
    }

    /**
     * @return the tipoMantenimiento
     */
    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    /**
     * @return the descripcionMantenimiento
     */
    public String getDescripcionMantenimiento() {
        return descripcionMantenimiento;
    }
    
    /**
     * Obtener el título para un mantenimiento de acuerdo al tipo de acción
     * @return Título para un mantenimiento
     */
    public String getTituloMantenimiento(){
        String vHilera = "";
        
        switch(this.tipoMantenimiento){
            case "A": 
                vHilera = " [Agregando un nuevo registro]";
                break;
            case "M":
                vHilera = " [Modificando un registro]";
                break;
            case "C":
                vHilera = " [Consultando un registro]";
                break;    
            case "E":
                vHilera = " [Eliminando un registro]";
                break;   
            case "P":
                vHilera = " [Proceso de Negocio]";
                break;
        }
        return vHilera;
    }
    
    /**
     * Obtener el tipo de mantenimiento
     * @param pTipoMantenimiento Código del tipo de mantenimiento
     * @return Objeto Enum tipo de mantenimiento
     */
    public static ETipoMantenimiento buscarTipoMantenimiento(String pTipoMantenimiento){
        ETipoMantenimiento oTipoMantenimiento = null;
        
        switch(pTipoMantenimiento){
            case "A": 
                oTipoMantenimiento = ETipoMantenimiento.AGREGAR;
                break;
            case "M":
                oTipoMantenimiento = ETipoMantenimiento.MODIFICAR;
                break;
            case "C":
                oTipoMantenimiento = ETipoMantenimiento.CONSULTAR;
                break;    
            case "E":
                oTipoMantenimiento = ETipoMantenimiento.ELIMINAR;
                break;
            case "P":
                oTipoMantenimiento = ETipoMantenimiento.PROCESAR;
                break;
        }
        
        return oTipoMantenimiento;
    }
    
    @Override
    public String toString(){
        return "[" + this.tipoMantenimiento + " - " + this.descripcionMantenimiento +"]";
    }
}
