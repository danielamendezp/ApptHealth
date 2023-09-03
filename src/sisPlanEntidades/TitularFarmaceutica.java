/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public class TitularFarmaceutica {
    private int idTitular;
    private String descripcionTitular;

    public TitularFarmaceutica(int idTitular, String descripcionTitular) {
        this.idTitular = idTitular;
        this.descripcionTitular = descripcionTitular;
    }

    public int getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(int idTitular) {
        this.idTitular = idTitular;
    }

    public String getDescripcionTitular() {
        return descripcionTitular;
    }

    public void setDescripcionTitular(String descripcionTitular) {
        this.descripcionTitular = descripcionTitular;
    }

    @Override
    public String toString() {
        return idTitular + " - " + descripcionTitular;
    }

    
    
}
