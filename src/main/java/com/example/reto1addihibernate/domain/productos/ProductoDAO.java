package com.example.reto1addihibernate.domain.productos;

import com.example.reto1addihibernate.domain.DAO;

import java.util.ArrayList;

/**
 * Implementación de la interfaz ProductoDAO para acceder a la base de datos y gestionar productos.
 */
public class ProductoDAO implements DAO<Producto> {


    @Override
    public ArrayList<Producto> getAll() {
        return null;
    }

    @Override
    public Producto get(Long id) {
        return null;
    }

    @Override
    public Producto save(Producto data) {
        return null;
    }

    @Override
    public void update(Producto data) {

    }

    @Override
    public void delete(Producto data) {

    }
}
