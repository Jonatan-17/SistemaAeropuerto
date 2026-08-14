/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import exceptions.VueloLlenoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un vuelo del sistema.
 * Relaciona aeropuertos (origen y destino) y una lista de pasajeros.
 */
public class Vuelo {

    private String codigo;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private int capacidad;
    private EstadoVuelo estado;
    private List<Pasajero> pasajeros;

    public Vuelo(String codigo, Aeropuerto origen, Aeropuerto destino, int capacidad) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.capacidad = capacidad;
        this.estado = EstadoVuelo.PROGRAMADO;   // Todo vuelo nuevo inicia PROGRAMADO
        this.pasajeros = new ArrayList<>();     // Inicializar la lista correctamente
    }

    // ---------- Getters y Setters ----------

    public String getCodigo() {
        return codigo;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVuelo estado) {
        this.estado = estado;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    // ---------- Agregar pasajero (ACTUALIZADO CON EXCEPCIÓN) ----------

    public void agregarPasajero(Pasajero pasajero) throws VueloLlenoException {
        if (estaLleno()) {
            throw new VueloLlenoException("No existen asientos disponibles.");
        }
        pasajeros.add(pasajero);
    }

    // ---------- Consultar cupos ----------

    public int cuposDisponibles() {
        return capacidad - pasajeros.size();
    }

    public boolean estaLleno() {
        return pasajeros.size() >= capacidad;
    }

    // ---------- Validar si un pasajero ya está registrado ----------

    public boolean tienePasajero(String documento) {
        for (Pasajero p : pasajeros) {
            if (p.getDocumento().equalsIgnoreCase(documento)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vuelo: " + codigo
                + " | " + origen.getCiudad() + " -> " + destino.getCiudad()
                + " | Estado: " + estado;
    }
}