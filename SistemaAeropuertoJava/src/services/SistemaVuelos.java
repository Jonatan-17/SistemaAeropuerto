/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import exceptions.VueloLlenoException;
import models.*;
import repositories.Repositorio;
import java.util.*;

/**
 * Clase que centraliza la lógica y operaciones del sistema.
 */
public class SistemaVuelos {

    // Repositorios genéricos para administrar las entidades
    private Repositorio<Aeropuerto> repoAeropuertos;
    private Repositorio<Pasajero> repoPasajeros;
    private Repositorio<Vuelo> repoVuelos;

    public SistemaVuelos() {
        this.repoAeropuertos = new Repositorio<>();
        this.repoPasajeros = new Repositorio<>();
        this.repoVuelos = new Repositorio<>();
    }

    // ==========================================
    // MÉTODOS AUXILIARES (Búsquedas internas)
    // ==========================================

    private Aeropuerto buscarAeropuerto(String codigo) {
        for (Aeropuerto a : repoAeropuertos.obtenerTodos()) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }

    private Pasajero buscarPasajero(String documento) {
        for (Pasajero p : repoPasajeros.obtenerTodos()) {
            if (p.getDocumento().equals(documento)) {
                return p;
            }
        }
        return null;
    }

    private Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : repoVuelos.obtenerTodos()) {
            if (v.getCodigo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        return null;
    }

    // ==========================================
    // REGISTROS (Con sus respectivas validaciones)
    // ==========================================

    public boolean registrarAeropuerto(String codigo, String nombre, String ciudad, String pais) {
        if (buscarAeropuerto(codigo) != null) {
            return false; // Ya existe
        }
        Aeropuerto nuevo = new Aeropuerto(codigo, nombre, ciudad, pais);
        repoAeropuertos.agregar(nuevo);
        return true;
    }

    public boolean registrarPasajero(String documento, String nombre, int edad) {
        if (buscarPasajero(documento) != null) {
            return false; // Documento ya existe
        }
        if (edad <= 0) {
            return false; // Edad inválida
        }
        Pasajero nuevo = new Pasajero(documento, nombre, edad);
        repoPasajeros.agregar(nuevo);
        return true;
    }

    public boolean registrarVuelo(String codigoVuelo, String codOrigen, String codDestino, int capacidad) {
        if (buscarVuelo(codigoVuelo) != null) return false; // Código repetido
        if (capacidad <= 0) return false; // Capacidad inválida
        
        Aeropuerto origen = buscarAeropuerto(codOrigen);
        Aeropuerto destino = buscarAeropuerto(codDestino);
        
        if (origen == null || destino == null) return false; // Aeropuertos no existen
        if (origen.getCodigo().equalsIgnoreCase(destino.getCodigo())) return false; // Origen y destino iguales

        Vuelo nuevo = new Vuelo(codigoVuelo, origen, destino, capacidad);
        repoVuelos.agregar(nuevo);
        return true;
    }

    // ==========================================
    // CONSULTAS Y LISTADOS
    // ==========================================

    public List<Vuelo> obtenerTodosLosVuelos() {
        return repoVuelos.obtenerTodos();
    }

    public List<Vuelo> buscarVuelosPorDestino(String ciudad) {
        List<Vuelo> resultados = new ArrayList<>();
        String ciudadBusqueda = ciudad.trim().toLowerCase();
        
        for (Vuelo v : repoVuelos.obtenerTodos()) {
            if (v.getDestino().getCiudad().toLowerCase().contains(ciudadBusqueda)) {
                resultados.add(v);
            }
        }
        return resultados;
    }

    public List<Pasajero> obtenerPasajerosDeVuelo(String codigoVuelo) {
        Vuelo v = buscarVuelo(codigoVuelo);
        if (v != null) {
            return v.getPasajeros();
        }
        return new ArrayList<>();
    }

    // ==========================================
    // RESERVAR VUELO (Manejo de Excepciones)
    // ==========================================

    public String reservarVuelo(String documento, String codigoVuelo) throws VueloLlenoException {
        Pasajero p = buscarPasajero(documento);
        if (p == null) return "Error: El pasajero no existe.";

        Vuelo v = buscarVuelo(codigoVuelo);
        if (v == null) return "Error: El vuelo no existe.";

        if (v.getEstado() != EstadoVuelo.PROGRAMADO) {
            return "Error: El vuelo no está en estado PROGRAMADO.";
        }

        if (v.tienePasajero(documento)) {
            return "Error: El pasajero ya está registrado en este vuelo.";
        }

        // Si llega aquí, todo es válido. agregamos el pasajero.
        // Si el vuelo está lleno, el método agregarPasajero lanzará VueloLlenoException
        v.agregarPasajero(p);
        return "Reserva realizada correctamente.";
    }

    // ==========================================
    // CAMBIAR ESTADO
    // ==========================================

    public boolean cambiarEstadoVuelo(String codigoVuelo, EstadoVuelo nuevoEstado) {
        Vuelo v = buscarVuelo(codigoVuelo);
        if (v != null) {
            v.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    // ==========================================
    // POLIMORFISMO (Requisito del taller)
    // ==========================================

    public void demostrarPolimorfismo() {
        List<Persona> personas = new ArrayList<>();
        
        // Agregamos un pasajero del repositorio (si existe)
        if (!repoPasajeros.obtenerTodos().isEmpty()) {
            personas.add(repoPasajeros.obtenerTodos().get(0));
        }
        
        // Agregamos un piloto de ejemplo
        personas.add(new Piloto("9999", "Carlos Rodríguez", "LIC-123", 2500));
        
        System.out.println("\n--- DEMOSTRACIÓN DE POLIMORFISMO ---");
        for (Persona persona : personas) {
            // Aquí ocurre el polimorfismo: el mismo método se comporta distinto según el objeto
            System.out.println(persona.obtenerDescripcion());
        }
        System.out.println("------------------------------------\n");
    }

    // ==========================================
    // ESTADÍSTICAS (Uso de Set y Map)
    // ==========================================

    public void mostrarEstadisticas() {
        int totalAeropuertos = repoAeropuertos.obtenerTodos().size();
        int totalPasajeros = repoPasajeros.obtenerTodos().size();
        int totalVuelos = repoVuelos.obtenerTodos().size();
        
        int prog = 0, abord = 0, vuelo = 0, fin = 0, canc = 0;
        int totalReservas = 0;
        Vuelo mayorOcupacion = null;
        int maxPasajeros = -1;

        // Uso de SET para destinos únicos
        Set<String> ciudadesDestino = new HashSet<>();
        
        // Uso de MAP para contar vuelos por destino
        Map<String, Integer> vuelosPorDestino = new HashMap<>();

        for (Vuelo v : repoVuelos.obtenerTodos()) {
            // Contar estados
            switch (v.getEstado()) {
                case PROGRAMADO: prog++; break;
                case ABORDANDO: abord++; break;
                case EN_VUELO: vuelo++; break;
                case FINALIZADO: fin++; break;
                case CANCELADO: canc++; break;
            }
            
            // Contar reservas y buscar mayor ocupación
            int pasajerosVuelo = v.getPasajeros().size();
            totalReservas += pasajerosVuelo;
            if (pasajerosVuelo > maxPasajeros) {
                maxPasajeros = pasajerosVuelo;
                mayorOcupacion = v;
            }
            
            // Llenar Set
            ciudadesDestino.add(v.getDestino().getCiudad());
            
            // Llenar Map
            String ciudad = v.getDestino().getCiudad();
            vuelosPorDestino.put(ciudad, vuelosPorDestino.getOrDefault(ciudad, 0) + 1);
        }

        System.out.println("\n========= ESTADÍSTICAS =========");
        System.out.println("Aeropuertos registrados: " + totalAeropuertos);
        System.out.println("Vuelos registrados: " + totalVuelos);
        System.out.println("Pasajeros registrados: " + totalPasajeros);
        System.out.println("Vuelos programados: " + prog);
        System.out.println("Vuelos abordando: " + abord);
        System.out.println("Vuelos en vuelo: " + vuelo);
        System.out.println("Vuelos finalizados: " + fin);
        System.out.println("Vuelos cancelados: " + canc);
        System.out.println("Total reservas realizadas: " + totalReservas);
        
        if (mayorOcupacion != null) {
            System.out.println("Vuelo con mayor ocupación: " + mayorOcupacion.getCodigo() 
                    + " (" + mayorOcupacion.getOrigen().getCiudad() + " -> " + mayorOcupacion.getDestino().getCiudad() 
                    + ") con " + maxPasajeros + "/" + mayorOcupacion.getCapacidad() + " pasajeros.");
        }
        
        System.out.println("\n=== DESTINOS DISPONIBLES (Set) ===");
        for (String c : ciudadesDestino) {
            System.out.println(c);
        }
        
        System.out.println("\n=== VUELOS POR DESTINO (Map) ===");
        for (Map.Entry<String, Integer> entry : vuelosPorDestino.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("==================================\n");
    }

    // ==========================================
    // DATOS PRECARGADOS (Requisito del taller)
    // ==========================================

    public void cargarDatosIniciales() {
        // Aeropuertos
        registrarAeropuerto("BOG", "El Dorado", "Bogotá", "Colombia");
        registrarAeropuerto("MDE", "José María Córdova", "Medellín", "Colombia");
        registrarAeropuerto("CLO", "Alfonso Bonilla Aragón", "Cali", "Colombia");
        registrarAeropuerto("CTG", "Rafael Núñez", "Cartagena", "Colombia");
        registrarAeropuerto("PSO", "Antonio Nariño", "Pasto", "Colombia");

        // Vuelos
        registrarVuelo("AV101", "BOG", "MDE", 180);
        registrarVuelo("AV202", "BOG", "CTG", 150);
        registrarVuelo("AV303", "MDE", "CLO", 120);
        registrarVuelo("AV404", "CLO", "BOG", 200);
        registrarVuelo("AV505", "BOG", "PSO", 90);

        // Pasajeros
        registrarPasajero("1001", "Laura Gómez", 25);
        registrarPasajero("1002", "Andrés Martínez", 32);
        registrarPasajero("1003", "Carolina Díaz", 28);
        registrarPasajero("1004", "Daniel Torres", 41);
    }
}