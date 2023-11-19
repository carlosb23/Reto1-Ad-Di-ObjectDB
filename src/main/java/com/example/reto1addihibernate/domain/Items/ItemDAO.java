package com.example.reto1addihibernate.domain.Items;

import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemDAO para acceder a la base de datos y gestionar elementos (items).
 */
public class ItemDAO implements DAO<Item> {


    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Item> query = session.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        }
        return salida;
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
    public void delete(Item data) {

    }
}
