/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public enum ERolUsuario {
    ADMINISTRADOR("AD", "Usuario Administrador"),
    MANTENIMIENTO("MN", "Usuario Mantenimiento"),
    CITAS("CT", "Usuario Citas"),
    DOCTOR("DC","Usuario Doctor");
    
    
    private String codigo;
    private String descripcion;

    private ERolUsuario(String codigo, String descripcion) {
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
    
    public static ERolUsuario buscsarNivel(String pCodigo){
        ERolUsuario vnivel= null; 
        
        switch (pCodigo) {
            case "AD":
                vnivel = vnivel.ADMINISTRADOR;
                break;
            case "MN":
                vnivel = vnivel.MANTENIMIENTO;
                break;
            case "CT":
                vnivel = vnivel.CITAS;
                break;   
            case "DC":
                vnivel = vnivel.DOCTOR;
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
