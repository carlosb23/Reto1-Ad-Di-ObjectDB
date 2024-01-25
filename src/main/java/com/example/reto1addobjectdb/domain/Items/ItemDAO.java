package com.example.reto1addobjectdb.domain.Items;

import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemDAO para acceder a la base de datos y gestionar elementos (items).
 */
public class ItemDAO implements DAO<Item> {

    /**
     * Obtiene todos los elementos (items) de la base de datos.
     *
     * @return Una lista de todos los elementos.
     */
    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Item> query = session.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un elemento (item) por su identificador único.
     *
     * @param id El identificador único del elemento.
     * @return El elemento correspondiente al identificador o null si no se encuentra.
     */
    @Override
    public Item get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo elemento (item) en la base de datos.
     *
     * @param data El elemento a guardar.
     * @return El elemento guardado.
     */
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

    /**
     * Actualiza un elemento (item) en la base de datos.
     *
     * @param data El elemento a actualizar.
     */
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

    /**
     * Elimina un elemento (item) de la base de datos.
     *
     * @param data El elemento a eliminar.
     */
    @Override
    public void delete(Item data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                // Antes de eliminar el item, asegúrate de que esté gestionado por la sesión actual
                Item itemToDelete = session.get(Item.class, data.getId());

                // Elimina el item
                session.delete(itemToDelete);

                transaction.commit();
            } catch (Exception e) {
                // En caso de error, realiza un rollback
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
