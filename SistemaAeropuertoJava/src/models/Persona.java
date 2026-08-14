/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Clase abstracta que representa una persona del sistema.
 * No puede instanciarse directamente.
 * Sirve como clase base para Pasajero y Piloto.
 */
public abstract class Persona {

    private String documento;
    private String nombre;

    public Persona(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método abstracto: no tiene cuerpo.
     * Cada clase hija (Pasajero, Piloto) lo implementará
     * a su manera (polimorfismo).
     */
    public abstract String obtenerDescripcion();
}