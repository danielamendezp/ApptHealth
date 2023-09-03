/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public class ServiciosMedicos {
    private int idServicio;
    private String descripcion;
    private double factorCosto;
    private int categoriaServiciosMedicos;

    public ServiciosMedicos(int idServicio, String descripcion, double factorCosto, int categoriaServiciosMedicos) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.factorCosto = factorCosto;
        this.categoriaServiciosMedicos = categoriaServiciosMedicos;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getFactorCosto() {
        return factorCosto;
    }

    public void setFactorCosto(double factorCosto) {
        this.factorCosto = factorCosto;
    }

    public int getCategoriaServiciosMedicos() {
        return categoriaServiciosMedicos;
    }

    public void setCategoriaServiciosMedicos(int categoriaServiciosMedicos) {
        this.categoriaServiciosMedicos = categoriaServiciosMedicos;
    }

    @Override
    public String toString() {
        
        String hilera="";
        if(idServicio>0){
            hilera=descripcion;
        }
        return hilera ;
    }
    
    public String toStringArchivo() {
        return idServicio+";"+descripcion+";"+factorCosto+";"+categoriaServiciosMedicos;
    }
    
}
