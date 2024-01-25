package com.example.reto1addobjectdb.domain.productos;

import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz DAO para acceder a la base de datos y gestionar productos.
 *
 * Esta clase proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la tabla de productos en la base de datos.
 */
public class ProductoDAO implements DAO<Producto> {

    /**
     * Obtiene todos los productos almacenados en la base de datos.
     *
     * @return Lista de productos.
     */
    @Override
    public ArrayList<Producto> getAll() {
        var salida = new ArrayList<Producto>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Producto> query = sesion.createQuery("from Producto", Producto.class);
            salida = (ArrayList<Producto>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un producto específico por su identificador.
     *
     * @param id Identificador del producto.
     * @return El producto correspondiente al identificador o null si no se encuentra.
     */
    @Override
    public Producto get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param data Producto a guardar.
     * @return El producto guardado con su identificador asignado.
     */
    @Override
    public Producto save(Producto data) {
        return null;
    }

    /**
     * Actualiza la información de un producto existente en la base de datos.
     *
     * @param data Producto con la información actualizada.
     */
    @Override
    public void update(Producto data) {

    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param data Producto a eliminar.
     */
    @Override
    public void delete(Producto data) {

    }

    /**
     * Obtiene una lista de nombres de productos que coinciden con el nombre proporcionado.
     *
     * @param nombre Nombre a buscar en los productos.
     * @return Lista de nombres de productos que coinciden con el nombre proporcionado.
     * @throws RuntimeException Si hay un error al obtener los nombres de los productos.
     */
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
