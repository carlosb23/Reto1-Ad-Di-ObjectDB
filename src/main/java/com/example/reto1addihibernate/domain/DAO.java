package com.example.reto1addihibernate.domain;

import java.util.ArrayList;

/**
 * Interfaz genérica para definir las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos.
 *
 * @param <T> Tipo de objeto que se gestionará en la base de datos.
 */
public interface DAO<T> {

    /**
     * Obtiene todos los objetos almacenados en la base de datos.
     *
     * @return Lista de objetos.
     */
    public ArrayList<T> getAll();

    /**
     * Obtiene un objeto específico por su identificador.
     *
     * @param id Identificador del objeto.
     * @return El objeto correspondiente al identificador o null si no se encuentra.
     */
    public T get(Long id);

    /**
     * Guarda un nuevo objeto en la base de datos.
     *
     * @param data Objeto a guardar.
     * @return El objeto guardado con su identificador asignado.
     */
    public T save(T data);

    /**
     * Actualiza la información de un objeto existente en la base de datos.
     *
     * @param data Objeto con la información actualizada.
     */
    public void update(T data);

    /**
     * Elimina un objeto de la base de datos.
     *
     * @param data Objeto a eliminar.
     */
    public void delete(T data);
}
