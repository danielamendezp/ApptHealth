/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanUtilitario;

/**
 * Clase ETipoEstiloVentana para los estilos del entorno de ventanas del OS
 * @author Ing. Daniela Mendez
 * @version 2.0
 * @since Junio 2020
 */
public enum ETipoEstiloVentana {
    
    WINDOWS("Windows", "Estilo por defecto"),
    WINDOWS_CLASSIC("Windows Classic", "Estilo Windows Classic"),
    METAL("Metal", "Estilo Metal"),
    NIMBUS("Nimbus", "Estilo Nimbus"),
    MOTIF("CDE/Motif", "Estilo CDE/Motif");
        
    private final String codigo;
    private final String descripcion;
    
    /**
     * Constructor
     * @param pCodigo Código del estilo
     * @param pDescripcion Descripción del estilo
     */
    private ETipoEstiloVentana(String pCodigo, String pDescripcion){
        this.codigo = pCodigo;
        this.descripcion = pDescripcion;
    }

    /**
     * @return Código del estilo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the Descripción del estilo
     */
    public String getDescripcion() {
        return descripcion;
    }

}
