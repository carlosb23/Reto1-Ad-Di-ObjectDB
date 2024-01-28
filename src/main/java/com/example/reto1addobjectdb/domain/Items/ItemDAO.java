package com.example.reto1addobjectdb.domain.Items;

import com.example.reto1addobjectdb.ObjectDBUtil;
import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.pedido.Pedido;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Item> query = entityManager.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            try {
                // Comenzar la transacción
                entityManager.getTransaction().begin();

                // Guardar el nuevo pedido en la base de datos
                entityManager.persist(data);

                // Commit de la transacción
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza un elemento (item) en la base de datos.
     *
     * @param data El elemento a actualizar.
     * @return
     */
    @Override
    public Pedido update(Item data) {
        return null;
    }

    /**
     * Elimina un elemento (item) de la base de datos.
     *
     * @param data El elemento a eliminar.
     * @return
     */
    @Override
    public boolean delete(Item data) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {

            try {
                entityManager.getTransaction().begin();

                if(!entityManager.contains(data)){
                    data = entityManager.merge(data);
                }
                entityManager.remove(data);
                entityManager.getTransaction().commit();

            } catch (Exception e) {
                // En caso de error, realiza un rollback
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            }finally {
                entityManager.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
