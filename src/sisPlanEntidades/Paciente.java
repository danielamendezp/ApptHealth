/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import sisPlanUtilitario.Formatos;


/**
 *
 * @author Daniela Fabiola
 */
public class Paciente extends Persona{
    private ENivelEscolaridad escolaridad;
    private EIngresos ingresos;
    private Profesion profesion;

    public Paciente(String cedula, String nombreApellidos, Date fechaNacimiento, char genero, String telefonoCelular, String telefonoCasa, String telefonoOficina, Provincia provincia, Canton canton, Distrito distrito, String sennas, ENivelEscolaridad escolaridad, EIngresos ingresos, Profesion profesion) {
        super(cedula, nombreApellidos, fechaNacimiento, genero, telefonoCelular, telefonoCasa, telefonoOficina, provincia, canton, distrito, sennas);
        this.escolaridad = escolaridad;
        this.ingresos = ingresos;
        this.profesion = profesion;
    }


    @Override
    public int edad() {
        LocalDate fechaNacimiento = this.fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now(); // Fecha actual
        Period periodo = Period.between(fechaNacimiento, fechaActual); // Calcular período entre las dos fechas
        int edad = periodo.getYears(); // Obtener la cantidad de años
        return edad;
    }

    @Override
    public String toString() {
         return cedula+" - "+nombreApellidos + " - " + edad() +" - " +genero +" - "+distrito.getNombre()+", "+canton.getNombre()+", "+provincia.getNombre()+", COSTA RICA";
    }
    @Override
    public String toStringArchivo() {
        return  cedula+ ";" + nombreApellidos + ";" + Formatos.conFormatoFecha(fechaNacimiento) + ";" + genero + ";" + telefonoCelular + ";" + telefonoCasa + ";" + telefonoOficina
                + ";" + provincia.getIdentificador() +  ";" + canton.getIdentificador() + ";" + distrito.getIdentificador() + ";" + sennas + ";" 
                +  profesion.getId_Profesion() + ";" + escolaridad.getCodigo()+ ";" + ingresos.getCodigo();
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }
    
    public ENivelEscolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(ENivelEscolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public EIngresos getIngresos() {
        return ingresos;
    }

    public void setIngresos(EIngresos ingresos) {
        this.ingresos = ingresos;
    }

    
    
    
}
