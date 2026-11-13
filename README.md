# Sistema de Aeropuerto (Java)

Aplicación de consola en Java para gestionar aeropuertos, vuelos, pasajeros y reservas. Es un ejercicio de **programación orientada a objetos**: herencia, clases abstractas, polimorfismo, genéricos, enums, excepciones propias y colecciones (`List`, `Set`, `Map`).

## Funcionalidades

1. Registrar aeropuertos, pasajeros y vuelos, con validaciones (códigos y documentos únicos, edad válida).
2. Listar vuelos y buscarlos por ciudad de destino.
3. Reservar un vuelo, controlando la capacidad (lanza `VueloLlenoException` si está lleno).
4. Ver los pasajeros de un vuelo.
5. Cambiar el estado de un vuelo: `PROGRAMADO`, `ABORDANDO`, `EN_VUELO`, `FINALIZADO` o `CANCELADO`.
6. Ver estadísticas: vuelos por estado, total de reservas, vuelo con mayor ocupación, destinos disponibles (`Set`) y vuelos agrupados por destino (`Map`).

Al iniciar se cargan datos de ejemplo (5 aeropuertos de Colombia, 5 vuelos y 4 pasajeros).

## Diseño

```
models/         Persona (abstracta) ← Pasajero, Piloto · Aeropuerto · Vuelo · EstadoVuelo (enum)
repositories/   Repositorio<T>: repositorio genérico en memoria
services/       SistemaVuelos: toda la lógica de negocio
exceptions/     VueloLlenoException
Main.java       Menú de consola
```

## Cómo ejecutarlo

Requiere Java 21 o superior.

```bash
git clone https://github.com/Jonatan-17/SistemaAeropuerto.git
cd SistemaAeropuerto/SistemaAeropuertoJava
javac -d out $(find src -name "*.java")
java -cp out Main
```

También se puede abrir la carpeta `SistemaAeropuertoJava` como proyecto en NetBeans y ejecutarlo desde ahí.

## Autor

Jonatan David Sarmiento Sierra — [GitHub](https://github.com/Jonatan-17) · [LinkedIn](https://www.linkedin.com/in/jonatan-sarmiento-16a206366/)
