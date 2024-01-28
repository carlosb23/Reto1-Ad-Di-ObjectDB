package com.example.reto1addobjectdb.domain.pedido;

import com.example.reto1addobjectdb.ObjectDBUtil;
import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.usuario.Usuario;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO para acceder a la base de datos y gestionar pedidos.
 */
public class PedidoDAO implements DAO<Pedido> {

    /**
     * Obtiene todos los pedidos de la base de datos.
     *
     * @return Lista de pedidos.
     */
    @Override
    public ArrayList<Pedido> getAll() {
        var salida = new ArrayList<Pedido>(0);
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p", Pedido.class);
            salida = (ArrayList<Pedido>) query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    /**
     * Obtiene un pedido por su identificador único.
     *
     * @param id Identificador único del pedido.
     * @return Pedido encontrado o un objeto Pedido vacío si no se encuentra.
     */
    @Override
    public Pedido get(Long id) {
        Pedido salida = null;
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Pedido> query = entityManager.createQuery("select ped from Pedido ped where ped.id = :id", Pedido.class);
            query.setParameter("id", id);
            var resultado = query.getResultList();
            if (resultado.size() > 0) {
                salida = resultado.get(0);
            }
        } finally {
            entityManager.close();
        }
        return salida;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param data Pedido a guardar.
     * @return Pedido guardado.
     */
    @Override
    public Pedido save(Pedido data) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return data;
    }

    /**
     * Actualiza un pedido existente en la base de datos.
     *
     * @param data Pedido a actualizar.
     * @return
     */
    @Override
    public Pedido update(Pedido data) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Utiliza el método merge para actualizar la entidad en la base de datos.
            data = entityManager.merge(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Maneja la excepción adecuadamente (puede imprimir o lanzar una excepción personalizada).
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return data;
    }

    /**
     * Elimina un pedido de la base de datos.
     *
     * @param data Pedido a eliminar.
     * @return
     */
    @Override
    public boolean delete(Pedido data) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Si la entidad no está gestionada, primero la adjuntamos al contexto de persistencia.
            if (!entityManager.contains(data)) {
                data = entityManager.merge(data);
            }
            entityManager.remove(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return false;
    }



    /**
     * Obtiene el último código de pedido de la base de datos.
     *
     * @return Último código de pedido generado.
     */
    public String getUltimoCodigoPedido() {
        EntityManager session = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<String> query = session.createQuery("select max(p.codigo_pedido) from Pedido p", String.class);
            String ultimoCodigo = query.getSingleResult();
            if (ultimoCodigo == null) {
                // No hay códigos anteriores, inicia desde PED-001
                return "PED-001";
            } else {
                int ultimoNumero = Integer.parseInt(ultimoCodigo.substring(4));
                int nuevoNumero = ultimoNumero + 1;
                return "PED-" + String.format("%03d", nuevoNumero);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores
            throw new RuntimeException("Error al generar el código de pedido", e);
        }
    }

    /**
     * Obtiene el total de los pedidos de un usuario.
     *
     * @param usuario Usuario para el cual se calculará el total de los pedidos.
     * @return Total de los pedidos del usuario.
     */
    public double getTotalPedidos(Usuario usuario) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Double> query = entityManager.createQuery("select sum(p.total) from Pedido p where p.usuario = :usuario", Double.class);
            query.setParameter("usuario", usuario);
            Double total = query.getSingleResult();
            return total != null ? total.doubleValue() : 0.0; // Maneja el caso en que total sea null
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores
            throw new RuntimeException("Error al obtener el total de los pedidos", e);
        }
    }
}



