/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisPlanUtilitario;

/**
 * Clase ECodigosConsecutivos para indicar a la aplicación los códigos 
 de los consecutivos registrados
 * @author Ing. Daniela Mendez
 * @version 3.0
 * @since Agosto 2023
 */
public enum ECodigosConsecutivos {
    CLIENTES("CLIE", "Consecutivo de Clientes"),
    FACTURAS("FACT", "Consecutivo de Facturas");
    
    private String codigo;
    private String descripcion;

    /**
     * Constructor con parámetros
     * @param pCodigo Código del consecutivo
     * @param pDescripcion Descripción del consecutivo
     */
    private ECodigosConsecutivos(String pCodigo,
                               String pDescripcion){
        this.codigo = pCodigo;
        this.descripcion = pDescripcion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString(){
        return "[" + this.codigo + " - " + this.descripcion +"]";
    }    
}
