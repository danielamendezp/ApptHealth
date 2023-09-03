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
public class Usuario {
    private int codigo;
    private String idUsuario;
    private String nombreUsuario;
    private String claveUsuario;
    private ERolUsuario rolUsuario;
    private String estadoUsuario;
    private Date ingreso;

    public Usuario(int codigo, String idUsuario, String nombreUsuario, String claveUsuario, ERolUsuario rolUsuario, String estadoUsuario, Date ingreso) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.claveUsuario = claveUsuario;
        this.rolUsuario = rolUsuario;
        this.estadoUsuario = estadoUsuario;
        this.ingreso = ingreso;
    }

    

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public ERolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(ERolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String toStringArchivo() {
        return codigo + ";" + idUsuario + ";" + nombreUsuario + ";" + claveUsuario + ";" + rolUsuario.getCodigo() + ";" + estadoUsuario+ ";"+Formatos.conFormatoFecha(ingreso);
    }
   
}