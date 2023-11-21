package com.example.reto1addihibernate.domain.Items;

import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comenzar la transacción
                transaction = session.beginTransaction();

                // Guardar el nuevo pedido en la base de datos
                session.save(data);

                // Commit de la transacción
                transaction.commit();
            } catch (Exception e) {
                // Manejar cualquier excepción que pueda ocurrir durante la transacción
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    @Override
    public void update(Item data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Actualizar el item en la base de datos
                session.update(data);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                // Manejo de errores, por ejemplo, lanzar una excepción personalizada
                throw new RuntimeException("Error al actualizar el item en la base de datos", e);
            }
        }
    }

    @Override
    public void delete(Item data) {

    }
}
