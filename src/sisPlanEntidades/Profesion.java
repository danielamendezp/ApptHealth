/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public class Profesion {
    private int id_Profesion;
    private String nombre;

    public Profesion(int id_Profesion, String nombre) {
        this.id_Profesion = id_Profesion;
        this.nombre = nombre;
    }
    public int getId_Profesion() {
        return id_Profesion;
    }

    public void setId_Profesion(int id_Profesion) {
        this.id_Profesion = id_Profesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString(){
        return id_Profesion+ " - " + nombre;
    }   
}
