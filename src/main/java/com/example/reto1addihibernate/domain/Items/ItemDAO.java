package com.example.reto1addihibernate.domain.Items;

import com.example.reto1addihibernate.domain.DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemDAO para acceder a la base de datos y gestionar elementos (items).
 */
public class ItemDAO implements DAO<Item> {


    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public Item get(Long id) {
        return null;
    }

    @Override
    public Item save(Item data) {
        return null;
    }

    @Override
    public void update(Item data) {

    }

    @Override
    public void delete(Item data) {

    }
}
