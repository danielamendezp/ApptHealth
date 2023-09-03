/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public class CategoriaServicioMedico {
    private int idCategoria;
    private String descripcion;
    private double costoFactor;

    public CategoriaServicioMedico(int idCategoria, String descripcion, double costoFactor) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.costoFactor = costoFactor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoFactor() {
        return costoFactor;
    }

    public void setCostoFactor(double costoFactor) {
        this.costoFactor = costoFactor;
    }

    @Override
    public String toString() {
        return idCategoria + " - " + descripcion ;
    }
    
    
    
    
}
