/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

import java.util.Date;
import sisPlanUtilitario.Formatos;

/**
 *
 * @author Daniela Fabiola
 */
public class Cita {
    private int codigo;
    private Date fecha;
    private String hora;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(int codigo, Date fecha, String hora, Doctor doctor, Paciente paciente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String toStringArchivo() {
        return codigo + ";" + Formatos.conFormatoFecha(fecha) + ";" + hora + ";" + doctor.getNumColegiado() + ";" + paciente.getCedula();
    }

    @Override
    public String toString() {
        return codigo + " - [ " + paciente.cedula +" - "+ paciente.nombreApellidos+" ] "+ Formatos.conFormatoFecha(fecha) + " - " + hora + ". Especialista: "+doctor.toString();
    }
    
    
    
    
}
