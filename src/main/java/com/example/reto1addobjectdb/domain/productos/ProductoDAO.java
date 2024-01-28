package com.example.reto1addobjectdb.domain.productos;

import com.example.reto1addobjectdb.ObjectDBUtil;
import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.pedido.Pedido;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Producto> query = entityManager.createQuery("select p from Producto p", Producto.class);
            salida = (ArrayList<Producto>) query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
     * @return
     */
    @Override
    public Pedido update(Producto data) {

        return null;
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param data Producto a eliminar.
     * @return
     */
    @Override
    public boolean delete(Producto data) {

        return false;
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
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<String> query = entityManager.createQuery("select p.nombre_producto from Producto p", String.class);
            nombres = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores, por ejemplo, lanzar una excepción personalizada
            throw new RuntimeException("Error al obtener los nombres de los productos", e);
        }

        return nombres;
    }


    public void saveAll(List<Producto> data) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            for (Producto pr : data) {
                entityManager.persist(pr);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
