package com.example.reto1addihibernate.domain.pedido;

import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import com.example.reto1addihibernate.SessionData;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO para acceder a la base de datos y gestionar pedidos.
 */
public class PedidoDAO implements DAO<Pedido> {


    public static final HashMap<String,String> QUERY_ATTR;

    static{
        QUERY_ATTR = new HashMap<>();
        QUERY_ATTR.put("codigo_pedido","select distinct(p.codigo_pedido) from pedido p");
        QUERY_ATTR.put("fecha","select distinct(p.fecha) from pedido p");
        QUERY_ATTR.put("usuario","select distinct(p.usuario) from pedido p");
        QUERY_ATTR.put("total","select distinct(p.total) from pedido p");
        QUERY_ATTR.put("cantidad","select distinct(i.cantidad) from items i");
        QUERY_ATTR.put("product_id","select distinct(i.product_id) from items i");
    }

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
        return null;
    }



    @Override
    public void delete(Pedido data) {
        HibernateUtil.getSessionFactory().inTransaction((session)->{
            Pedido p = session.get(Pedido.class, data.getId());
            session.remove(p);
        });

    }

    public List<String> getDistinctFromAttribute(String attr){
        ArrayList<String> results = new ArrayList<>(0);

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<String> q = s.createQuery(QUERY_ATTR.get(attr), String.class);
            results = (ArrayList<String>) q.getResultList();
        }
        return results;
    }

}

