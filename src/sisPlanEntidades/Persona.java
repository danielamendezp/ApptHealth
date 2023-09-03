/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Daniela Fabiola
 */
public abstract class Persona {
    protected String cedula;
    protected String nombreApellidos;
    protected Date fechaNacimiento;
    protected char genero;
    protected String telefonoCelular;
    protected String telefonoCasa;
    protected String telefonoOficina;
    protected Provincia provincia;
    protected Canton canton;
    protected Distrito distrito;
    protected String sennas;

    public Persona(String cedula, String nombreApellidos, Date fechaNacimiento, char genero, String telefonoCelular, String telefonoCasa, String telefonoOficina, Provincia provincia, Canton canton, Distrito distrito, String sennas) {
        this.cedula = cedula;
        this.nombreApellidos = nombreApellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefonoCelular = telefonoCelular;
        this.telefonoCasa = telefonoCasa;
        this.telefonoOficina = telefonoOficina;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.sennas = sennas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getSennas() {
        return sennas;
    }

    public void setSennas(String sennas) {
        this.sennas = sennas;
    }

   

   
    public abstract int edad();
    
    public abstract String toString();
    
    public abstract String toStringArchivo();
    
}
