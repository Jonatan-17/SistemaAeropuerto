/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Representa un pasajero del sistema.
 * Hereda de la clase abstracta Persona.
 */
public class Pasajero extends Persona {

    private int edad;

    public Pasajero(String documento, String nombre, int edad) {
        super(documento, nombre);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String obtenerDescripcion() {
        return "Pasajero: " + getNombre();
    }
}