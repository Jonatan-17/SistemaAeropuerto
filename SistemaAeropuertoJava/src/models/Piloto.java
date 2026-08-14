/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Representa un piloto del sistema.
 * Hereda de la clase abstracta Persona.
 */
public class Piloto extends Persona {

    private String licencia;
    private int horasVuelo;

    public Piloto(String documento, String nombre, String licencia, int horasVuelo) {
        super(documento, nombre);
        this.licencia = licencia;
        this.horasVuelo = horasVuelo;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public int getHorasVuelo() {
        return horasVuelo;
    }

    public void setHorasVuelo(int horasVuelo) {
        this.horasVuelo = horasVuelo;
    }

    @Override
    public String obtenerDescripcion() {
        return "Piloto: " + getNombre() + " - " + horasVuelo + " horas de vuelo";
    }
}