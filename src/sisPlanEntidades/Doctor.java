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
public class Doctor extends Persona{
    private int numColegiado;
    private Especialidad especialidad;
    private String horaAlmuerzo;

    public Doctor(int numColegiado, String cedula, String nombreApellidos, Date fechaNacimiento, char genero, String telefonoCelular, String telefonoCasa, String telefonoOficina, Provincia provincia, Canton canton, Distrito distrito, String sennas,Especialidad especialidad, String horaAlmuerzo) {
        super(cedula, nombreApellidos, fechaNacimiento, genero, telefonoCelular, telefonoCasa, telefonoOficina, provincia, canton, distrito, sennas);
        this.numColegiado = numColegiado;
        this.especialidad = especialidad;
        this.horaAlmuerzo = horaAlmuerzo;
    }

    public Doctor(String horaAlmuerzo, String cedula, String nombreApellidos, Date fechaNacimiento, char genero, String telefonoCelular, String telefonoCasa, String telefonoOficina, Provincia provincia, Canton canton, Distrito distrito, String sennas) {
        super(cedula, nombreApellidos, fechaNacimiento, genero, telefonoCelular, telefonoCasa, telefonoOficina, provincia, canton, distrito, sennas);
        this.horaAlmuerzo = horaAlmuerzo;
    }

    @Override
    public int edad() {
        LocalDate fechaNacimiento = this.fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now(); // Fecha actual
        Period periodo = Period.between(fechaNacimiento, fechaActual); // Calcular período entre las dos fechas
        int edad = periodo.getYears(); // Obtener la cantidad de años
        return edad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    
    @Override
    public String toString() {
       return nombreApellidos + " - " +especialidad.getNombre();
    }
    
    @Override
    public String toStringArchivo() {
        return  numColegiado+ ";" + cedula+ ";" + nombreApellidos + ";" + Formatos.conFormatoFecha(fechaNacimiento) + ";" + genero + ";" + telefonoCelular + ";" + telefonoCasa + ";" + telefonoOficina
                + ";" + provincia.getIdentificador() + ";" + canton.getIdentificador() + ";" + distrito.getIdentificador() + ";" + sennas + ";" 
                +  especialidad.getEspecialidadID()+";"+horaAlmuerzo;
    }

    public int getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(int numColegiado) {
        this.numColegiado = numColegiado;
    }

    public String getHoraAlmuerzo() {
        return horaAlmuerzo;
    }

    public void setHoraAlmuerzo(String horaAlmuerzo) {
        this.horaAlmuerzo = horaAlmuerzo;
    }
    
}
