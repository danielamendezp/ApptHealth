/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public enum ENivelEscolaridad {
    NINGUNA("NA", "Ninguna"),
    PRIMARIA("PR", "Primaria"),
    SECUNDARIA("SE", "Secundaria"),
    BACHILLETAO_LICENCIATURA("BL","Bachillerato y/o Licenciatura Universitario"),
    POSTGRADO("PS","Postgrado Universitario");
    
    
    private String codigo;
    private String descripcion;

    private ENivelEscolaridad(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public static ENivelEscolaridad buscsarNivel(String pCodigo){
        ENivelEscolaridad vnivel= null; 
        
        switch (pCodigo) {
            case "NA":
                vnivel = vnivel.NINGUNA;
                break;
            case "PR":
                vnivel = vnivel.PRIMARIA;
                break;
            case "SE":
                vnivel = vnivel.SECUNDARIA;
                break;   
            case "BL":
                vnivel = vnivel.BACHILLETAO_LICENCIATURA;
                break;  
            case "PS":
                vnivel = vnivel.POSTGRADO;
                break;    
            default:
                break;
        }
        return vnivel;
    }
    
    public String toString(){
        return codigo+" - "+descripcion;
    }
}
