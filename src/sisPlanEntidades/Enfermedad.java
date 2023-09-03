/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public class Enfermedad {
    private String id_Enfermedad;
    private String id_Categoria;
    private String enfermedad;

    public Enfermedad(String id_Enfermedad, String id_Categoria, String enfermedad) {
        this.id_Enfermedad = id_Enfermedad;
        this.id_Categoria = id_Categoria;
        this.enfermedad = enfermedad;
    }

    public String getId_Enfermedad() {
        return id_Enfermedad;
    }

    public void setId_Enfermedad(String id_Enfermedad) {
        this.id_Enfermedad = id_Enfermedad;
    }

    
    public String getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(String id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        String hilera="";
        if(!id_Enfermedad.equals("")){
            hilera=id_Enfermedad + " - " + enfermedad ;
        }
        return hilera ;
    }
    
    public String toStringArchivo() {
        return id_Enfermedad + ";" + id_Categoria + ";" + enfermedad;
    }
    

    
}
