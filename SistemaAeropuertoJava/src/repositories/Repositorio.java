/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica para almacenar listas de cualquier tipo de objeto.
 * La letra <T> representa el Tipo de dato que se va a guardar.
 * 
 * @param <T> El tipo de objeto que guardará el repositorio.
 */
public class Repositorio<T> {

    // Lista interna que guardará los elementos
    private List<T> elementos = new ArrayList<>();

    /**
     * Agrega un elemento a la lista.
     * @param elemento El objeto a guardar.
     */
    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    /**
     * Devuelve todos los elementos guardados en la lista.
     * @return Lista de elementos.
     */
    public List<T> obtenerTodos() {
        return elementos;
    }
}