package com.example.reto1addobjectdb.domain.usuario;

import com.example.reto1addobjectdb.ObjectDBUtil;
import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.pedido.Pedido;
import lombok.extern.java.Log;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz DAO para acceder a la base de datos y gestionar usuarios.
 *
 * Esta clase proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * en la tabla de usuarios en la base de datos.
 */
@Log
public class UsuarioDAO implements DAO<Usuario> {

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * @return Lista de usuarios.
     */
    @Override
    public ArrayList<Usuario> getAll() {

        var salida = new ArrayList<Usuario>(0);
        EntityManager s = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Usuario> query = s.createQuery("Select u from Usuario u", Usuario.class);
            salida = (ArrayList<Usuario>) query.getResultList();
        }finally {

        }
        return salida;
    }

    /**
     * Obtiene un usuario específico por su identificador.
     *
     * @param id Identificador del usuario.
     * @return El usuario correspondiente al identificador o un nuevo objeto Usuario si no se encuentra.
     */
    @Override
    public Usuario get(Long id) {

        Usuario salida = null;
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Usuario> query = entityManager.createQuery("select u from Usuario u where u.id = :id", Usuario.class);
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
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param data Usuario a guardar.
     * @return El usuario guardado con su identificador asignado.
     */
    @Override
    public Usuario save(Usuario data) {
        return null;
    }

    /**
     * Actualiza la información de un usuario existente en la base de datos.
     *
     * @param data Usuario con la información actualizada.
     * @return
     */
    @Override
    public Pedido update(Usuario data) {

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
        return null;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param data Usuario a eliminar.
     * @return
     */
    @Override
    public boolean delete(Usuario data) {

        return false;
    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     *
     * @param name     Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return El usuario validado o null si las credenciales no son válidas.
     */
    public Usuario validateUser(String name, String password) {
        Usuario result = null;
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Usuario> q = entityManager.createQuery("select u from Usuario u where u.username=:u and u.password=:p", Usuario.class);
            q.setParameter("u", name);
            q.setParameter("p", password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {

        }
        return result;
    }

    /**
     * Obtiene un usuario por su dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico del usuario.
     * @return El usuario correspondiente a la dirección de correo electrónico o null si no se encuentra.
     */
    public Usuario getUserByEmail(String email) {
        Usuario result = null;
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try  {
            TypedQuery<Usuario> query = entityManager.createQuery("Select u from Usuario u where u.email=:e", Usuario.class);
            query.setParameter("e", email);

            try {
                result = query.getSingleResult();
            } catch (NoResultException e) {
                // No se encontró ningún usuario con ese correo electrónico
                System.out.println("Usuario no encontrado para el correo electrónico: " + email);
            } catch (Exception e) {
                // Otra excepción
                System.out.println("Error al buscar usuario por correo electrónico: " + e.getMessage());
            }
        }finally {

        }
        return result;
    }


    public void saveAll(List<Usuario> data) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            for (Usuario u : data) {
                entityManager.persist(u);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

}





