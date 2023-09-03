/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public class Especialidad {
    private int especialidadID;
    private int categoriaEspecialidades;
    private String nombre;

    public Especialidad(int especialidadID, int categoriaEspecialidades, String nombre) {
        this.especialidadID = especialidadID;
        this.categoriaEspecialidades = categoriaEspecialidades;
        this.nombre = nombre;
    }

    public int getEspecialidadID() {
        return especialidadID;
    }

    public void setEspecialidadID(int especialidadID) {
        this.especialidadID = especialidadID;
    }

    public int getCategoriaEspecialidades() {
        return categoriaEspecialidades;
    }

    public void setCategoriaEspecialidades(int categoriaEspecialidades) {
        this.categoriaEspecialidades = categoriaEspecialidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return especialidadID + " - " + nombre ;
    }
    
    public String toStringArchivo() {
        return especialidadID + ";" + categoriaEspecialidades + ";" + nombre;
    }
    
    
    
}
