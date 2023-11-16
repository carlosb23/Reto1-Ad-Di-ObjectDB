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

    static {
        QUERY_ATTR = new HashMap<>();
        QUERY_ATTR.put("id","select distinct(p.id) from Pedido p");
        QUERY_ATTR.put("codigo","select distinct(p.codigo) from Pedido p");
        QUERY_ATTR.put("fecha","select distinct(p.fecha) from Pedido p");
        QUERY_ATTR.put("usuario","select distinct(p.usuario) from Pedido p");
        QUERY_ATTR.put("total","select distinct(p.total) from Pedido p");
    }


    @Override
    public ArrayList<Pedido> getAll() {
        return null;
    }

    @Override
    public Pedido get(Long id) {
        return null;
    }

    @Override
    public Pedido save(Pedido data) {
        return null;
    }

    @Override
    public void update(Pedido data) {

    }

    @Override
    public void delete(Pedido data) {

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

