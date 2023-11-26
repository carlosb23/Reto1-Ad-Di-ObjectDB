package com.example.reto1addihibernate.domain.pedido;

import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.usuario.Usuario;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
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
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pedido> query = sesion.createQuery("from Pedido", Pedido.class);
            salida = (ArrayList<Pedido>) query.getResultList();
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
        var salida = new Pedido();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            salida = session.get(Pedido.class, id);
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
     * Actualiza un pedido existente en la base de datos.
     *
     * @param data Pedido a actualizar.
     */
    @Override
    public void update(Pedido data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Actualizar el pedido en la base de datos
                session.update(data);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                // Manejo de errores, por ejemplo, lanzar una excepción personalizada
                throw new RuntimeException("Error al actualizar el pedido en la base de datos", e);
            }
        }
    }

    /**
     * Elimina un pedido de la base de datos.
     *
     * @param data Pedido a eliminar.
     */
    @Override
    public void delete(Pedido data) {
        HibernateUtil.getSessionFactory().inTransaction((session) -> {
            Pedido p = session.get(Pedido.class, data.getId());
            session.remove(p);
        });
    }

    /**
     * Obtiene el último código de pedido de la base de datos.
     *
     * @return Último código de pedido generado.
     */
    public String getUltimoCodigoPedido() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("select max(p.codigo) from Pedido p", String.class);
            String ultimoCodigo = query.uniqueResult();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery("select sum(p.total) from Pedido p where p.usuario = :usuario", Double.class);
            query.setParameter("usuario", usuario);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores
            throw new RuntimeException("Error al obtener el total de los pedidos", e);
        }
    }
}



