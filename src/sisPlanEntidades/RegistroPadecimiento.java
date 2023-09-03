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
public class RegistroPadecimiento {
 private int codigo;
 private int numExpedienteMedico;
 private Enfermedad enfermedad;
 private Producto producto;
 private ServiciosMedicos serviciosMedicos;
 private Doctor doctor;
 private Date fecha;

    public RegistroPadecimiento(int codigo, int numExpedienteMedico, Enfermedad enfermedad, Producto producto, ServiciosMedicos serviciosMedicos, Doctor doctor, Date fecha) {
        this.codigo = codigo;
        this.numExpedienteMedico = numExpedienteMedico;
        this.enfermedad = enfermedad;
        this.producto = producto;
        this.serviciosMedicos = serviciosMedicos;
        this.doctor = doctor;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumExpedienteMedico() {
        return numExpedienteMedico;
    }

    public void setNumExpedienteMedico(int numExpedienteMedico) {
        this.numExpedienteMedico = numExpedienteMedico;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getnumExpedienteMedico() {
        return numExpedienteMedico;
    }

    public void setnumExpedienteMedico(int numExpedienteMedico) {
        this.numExpedienteMedico = numExpedienteMedico;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ServiciosMedicos getServiciosMedicos() {
        return serviciosMedicos;
    }

    public void setServiciosMedicos(ServiciosMedicos serviciosMedicos) {
        this.serviciosMedicos = serviciosMedicos;
    }

    public String toStringArchivo() {
        String productoStr="null";
        String servicioStr="null";
        String enfermedadStr="null";
        
        if(producto==null){
            productoStr="null";
        }else{
            productoStr=String.valueOf(producto.getIdProducto());
        }
        if(serviciosMedicos==null){
            servicioStr="null";
        }else{
            servicioStr=String.valueOf(serviciosMedicos.getIdServicio());
        }
        if(enfermedad==null){
            enfermedadStr="null";
        }else{
            enfermedadStr=String.valueOf(enfermedad.getId_Enfermedad());
        }
        return codigo + ";" + numExpedienteMedico + ";" + enfermedadStr + ";" + productoStr + ";" + servicioStr+ ";"+doctor.getNumColegiado()+";"+Formatos.conFormatoFecha(fecha);
    }
 
 
}
