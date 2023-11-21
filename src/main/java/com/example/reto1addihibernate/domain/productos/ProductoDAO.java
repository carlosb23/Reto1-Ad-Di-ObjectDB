package com.example.reto1addihibernate.domain.productos;

import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ProductoDAO para acceder a la base de datos y gestionar productos.
 */
public class ProductoDAO implements DAO<Producto> {


    @Override
    public ArrayList<Producto> getAll() {
        var salida = new ArrayList<Producto>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Producto> query = sesion.createQuery("from Producto", Producto.class);
            salida = (ArrayList<Producto>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Producto get(Long id) {
        return null;
    }

    @Override
    public Producto save(Producto data) {
        return null;
    }

    @Override
    public void update(Producto data) {

    }

    @Override
    public void delete(Producto data) {

    }

    public List<String> getnombreProduct(String nombre) {
        List<String> nombres = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("select p.nombre from Producto p", String.class);
            nombres = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores, por ejemplo, lanzar una excepción personalizada
            throw new RuntimeException("Error al obtener los nombres de los productos", e);
        }

        return nombres;
    }
}

