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
public class ExpedienteMedico {
    private int numExpediente;
    private Date fechaCeacion;
    private Usuario usuario;
    private Paciente paciente;

    public ExpedienteMedico(int numExpediente, Date fechaCeacion, Usuario usuario, Paciente paciente) {
        this.numExpediente = numExpediente;
        this.fechaCeacion = fechaCeacion;
        this.usuario = usuario;
        this.paciente = paciente;
    }

    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public Date getFechaCeacion() {
        return fechaCeacion;
    }

    public void setFechaCeacion(Date fechaCeacion) {
        this.fechaCeacion = fechaCeacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    public String toStringArchivo(){
        return numExpediente+";"+Formatos.conFormatoFecha(fechaCeacion)+";"+usuario.getCodigo()+";"+paciente.cedula;
        
    }
    
}
