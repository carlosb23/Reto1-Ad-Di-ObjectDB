package com.example.reto1addihibernate.domain.pedido;

import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import com.example.reto1addihibernate.SessionData;
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


    @Override
    public ArrayList<Pedido> getAll() {
        var salida = new ArrayList<Pedido>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Pedido> query = sesion.createQuery("from Pedido", Pedido.class);
            salida = (ArrayList<Pedido>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Pedido get(Long id) {
        var salida = new Pedido();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Pedido.class, id);
        }
        return salida;
    }

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

    @Override
    public void delete(Pedido data) {
        HibernateUtil.getSessionFactory().inTransaction((session)->{
            Pedido p = session.get(Pedido.class, data.getId());
            session.remove(p);
        });

    }

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

}



