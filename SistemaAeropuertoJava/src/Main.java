/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jonat
 */
import exceptions.VueloLlenoException;
import models.EstadoVuelo;
import models.Pasajero;
import models.Vuelo;
import services.SistemaVuelos;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * Se encarga del menú y de interactuar con el usuario.
 * Toda la lógica vive en SistemaVuelos.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaVuelos sistema = new SistemaVuelos();

        // PASO 11: datos precargados al iniciar
        sistema.cargarDatosIniciales();

        // Demostración de polimorfismo (requisito del taller)
        sistema.demostrarPolimorfismo();

        int opcion = 0;
        while (opcion != 10) {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarAeropuerto(scanner, sistema);
                    break;
                case 2:
                    registrarPasajero(scanner, sistema);
                    break;
                case 3:
                    registrarVuelo(scanner, sistema);
                    break;
                case 4:
                    listarVuelos(sistema);
                    break;
                case 5:
                    buscarVuelosPorDestino(scanner, sistema);
                    break;
                case 6:
                    reservarVuelo(scanner, sistema);
                    break;
                case 7:
                    verPasajerosVuelo(scanner, sistema);
                    break;
                case 8:
                    cambiarEstadoVuelo(scanner, sistema);
                    break;
                case 9:
                    sistema.mostrarEstadisticas();
                    break;
                case 10:
                    System.out.println("Gracias por usar el sistema. ¡Buen viaje!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    // ================= MENÚ =================

    private static void mostrarMenu() {
        System.out.println("==================================");
        System.out.println("SISTEMA AEROPUERTO JAVA");
        System.out.println("==================================");
        System.out.println("1. Registrar aeropuerto");
        System.out.println("2. Registrar pasajero");
        System.out.println("3. Registrar vuelo");
        System.out.println("4. Listar vuelos");
        System.out.println("5. Buscar vuelos por destino");
        System.out.println("6. Reservar vuelo");
        System.out.println("7. Ver pasajeros de un vuelo");
        System.out.println("8. Cambiar estado de vuelo");
        System.out.println("9. Ver estadísticas");
        System.out.println("10. Salir");
    }

    // ================= OPCIÓN 1 =================

    private static void registrarAeropuerto(Scanner sc, SistemaVuelos sistema) {
        System.out.println("\n--- REGISTRAR AEROPUERTO ---");
        System.out.print("Código: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine().trim();
        System.out.print("País: ");
        String pais = sc.nextLine().trim();

        if (sistema.registrarAeropuerto(codigo, nombre, ciudad, pais)) {
            System.out.println("Aeropuerto registrado correctamente.");
        } else {
            System.out.println("Error: ya existe un aeropuerto con ese código.");
        }
    }

    // ================= OPCIÓN 2 =================

    private static void registrarPasajero(Scanner sc, SistemaVuelos sistema) {
        System.out.println("\n--- REGISTRAR PASAJERO ---");
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        int edad = leerEntero(sc, "Edad: ");

        if (sistema.registrarPasajero(documento, nombre, edad)) {
            System.out.println("Pasajero registrado correctamente.");
        } else {
            System.out.println("Error: documento ya registrado o edad inválida.");
        }
    }

    // ================= OPCIÓN 3 =================

    private static void registrarVuelo(Scanner sc, SistemaVuelos sistema) {
        System.out.println("\n--- REGISTRAR VUELO ---");
        System.out.print("Código del vuelo: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Código aeropuerto origen: ");
        String origen = sc.nextLine().trim();
        System.out.print("Código aeropuerto destino: ");
        String destino = sc.nextLine().trim();
        int capacidad = leerEntero(sc, "Capacidad máxima: ");

        if (sistema.registrarVuelo(codigo, origen, destino, capacidad)) {
            System.out.println("Vuelo registrado correctamente.");
        } else {
            System.out.println("Error: verifique el código del vuelo, que los aeropuertos existan,");
            System.out.println("que origen y destino sean diferentes y que la capacidad sea válida.");
        }
    }

    // ================= OPCIÓN 4 =================

    private static void listarVuelos(SistemaVuelos sistema) {
        List<Vuelo> vuelos = sistema.obtenerTodosLosVuelos();
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }
        for (Vuelo v : vuelos) {
            System.out.println("--------------------------------");
            System.out.println("Vuelo: " + v.getCodigo());
            System.out.println("Origen: " + v.getOrigen().getCiudad());
            System.out.println("Destino: " + v.getDestino().getCiudad());
            System.out.println("Capacidad: " + v.getCapacidad());
            System.out.println("Pasajeros: " + v.getPasajeros().size());
            System.out.println("Estado: " + v.getEstado());
        }
        System.out.println("--------------------------------");
    }

    // ================= OPCIÓN 5 =================

    private static void buscarVuelosPorDestino(Scanner sc, SistemaVuelos sistema) {
        System.out.print("\nIngrese destino: ");
        String ciudad = sc.nextLine().trim();
        List<Vuelo> resultados = sistema.buscarVuelosPorDestino(ciudad);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron vuelos con ese destino.");
        } else {
            System.out.println("Resultados encontrados:");
            for (Vuelo v : resultados) {
                System.out.println(v.getCodigo() + " | "
                        + v.getOrigen().getCiudad() + " -> " + v.getDestino().getCiudad()
                        + " | Estado: " + v.getEstado());
            }
        }
    }

    // ================= OPCIÓN 6 =================

    private static void reservarVuelo(Scanner sc, SistemaVuelos sistema) {
        System.out.println("\n--- RESERVAR VUELO ---");
        System.out.print("Documento del pasajero: ");
        String documento = sc.nextLine().trim();
        System.out.print("Código del vuelo: ");
        String codigoVuelo = sc.nextLine().trim();

        try {
            String resultado = sistema.reservarVuelo(documento, codigoVuelo);
            System.out.println(resultado);
        } catch (VueloLlenoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ================= OPCIÓN 7 =================

    private static void verPasajerosVuelo(Scanner sc, SistemaVuelos sistema) {
        System.out.print("\nCódigo del vuelo: ");
        String codigo = sc.nextLine().trim();
        List<Pasajero> pasajeros = sistema.obtenerPasajerosDeVuelo(codigo);

        if (pasajeros.isEmpty()) {
            System.out.println("El vuelo no existe o aún no tiene pasajeros registrados.");
        } else {
            System.out.println("Pasajeros del vuelo " + codigo.toUpperCase() + ":");
            for (Pasajero p : pasajeros) {
                System.out.println("- " + p.getDocumento() + " | " + p.getNombre() + " | " + p.getEdad() + " años");
            }
        }
    }

    // ================= OPCIÓN 8 =================

    private static void cambiarEstadoVuelo(Scanner sc, SistemaVuelos sistema) {
        System.out.print("\nCódigo del vuelo: ");
        String codigo = sc.nextLine().trim();

        System.out.println("1. PROGRAMADO");
        System.out.println("2. ABORDANDO");
        System.out.println("3. EN_VUELO");
        System.out.println("4. FINALIZADO");
        System.out.println("5. CANCELADO");
        int op = leerEntero(sc, "Seleccione el nuevo estado: ");

        EstadoVuelo nuevoEstado;
        switch (op) {
            case 1: nuevoEstado = EstadoVuelo.PROGRAMADO; break;
            case 2: nuevoEstado = EstadoVuelo.ABORDANDO; break;
            case 3: nuevoEstado = EstadoVuelo.EN_VUELO; break;
            case 4: nuevoEstado = EstadoVuelo.FINALIZADO; break;
            case 5: nuevoEstado = EstadoVuelo.CANCELADO; break;
            default:
                System.out.println("Opción de estado no válida.");
                return;
        }

        if (sistema.cambiarEstadoVuelo(codigo, nuevoEstado)) {
            System.out.println("Estado del vuelo actualizado correctamente.");
        } else {
            System.out.println("Error: el vuelo no existe.");
        }
    }

    // ================= UTILIDAD: leer números sin que el programa se caiga =================

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
            }
        }
    }
}