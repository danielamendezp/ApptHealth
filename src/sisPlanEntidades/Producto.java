/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

import java.util.Date;

/**
 *
 * @author Daniela Fabiola
 */
public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String concentracion;
    private int formasFarmaceuticas;
    private String presentacion;
    private int fracciones;
    private Date fechaRegistro;
    private String numeroRegistro;
    private int titularFarmaceutica;

    public Producto(int idProducto, String nombreProducto, String concentracion, int formasFarmaceuticas, String presentacion, int fracciones, Date fechaRegistro, String numeroRegistro, int titularFarmaceutica) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.concentracion = concentracion;
        this.formasFarmaceuticas = formasFarmaceuticas;
        this.presentacion = presentacion;
        this.fracciones = fracciones;
        this.fechaRegistro = fechaRegistro;
        this.numeroRegistro = numeroRegistro;
        this.titularFarmaceutica = titularFarmaceutica;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public int getFormasFarmaceuticas() {
        return formasFarmaceuticas;
    }

    public void setFormasFarmaceuticas(int formasFarmaceuticas) {
        this.formasFarmaceuticas = formasFarmaceuticas;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getFracciones() {
        return fracciones;
    }

    public void setFracciones(int fracciones) {
        this.fracciones = fracciones;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public int getTitularFarmaceutica() {
        return titularFarmaceutica;
    }

    public void setTitularFarmaceutica(int titularFarmaceutica) {
        this.titularFarmaceutica = titularFarmaceutica;
    }

    @Override
    public String toString() {
        String hilera=" ";
        if(idProducto>0){
            hilera=nombreProducto +" - "+concentracion+" - "+presentacion;
        }
        return hilera ;
        
    }
    
    public String toStringArchivo() {
        return idProducto + ";" + nombreProducto + ";" + concentracion + ";" + formasFarmaceuticas + ";" + presentacion + ";" + fracciones + ";" + fechaRegistro + ";" + numeroRegistro + ";" + titularFarmaceutica ;
    }
   

    
}
