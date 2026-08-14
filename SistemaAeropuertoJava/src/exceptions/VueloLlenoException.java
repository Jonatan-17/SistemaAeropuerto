/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción personalizada que se lanza cuando se intenta
 * reservar un vuelo que no tiene cupos disponibles.
 */
public class VueloLlenoException extends Exception {

    public VueloLlenoException(String mensaje) {
        super(mensaje);
    }
}