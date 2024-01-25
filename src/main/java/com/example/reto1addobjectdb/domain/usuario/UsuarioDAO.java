package com.example.reto1addobjectdb.domain.usuario;

import com.example.reto1addobjectdb.domain.DAO;
import com.example.reto1addobjectdb.domain.HibernateUtil;
import jakarta.persistence.NoResultException;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

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
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuario> query = s.createQuery("from Usuario", Usuario.class);
            salida = (ArrayList<Usuario>) query.getResultList();
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

        var salida = new Usuario();

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(Usuario.class,id);
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
     */
    @Override
    public void update(Usuario data) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Actualizar el usuario en la base de datos
                session.update(data);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                // Manejo de errores, por ejemplo, lanzar una excepción personalizada
                throw new RuntimeException("Error al actualizar el usuario en la base de datos", e);
            }
        }

    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param data Usuario a eliminar.
     */
    @Override
    public void delete(Usuario data) {

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
        try( Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuario> q = session.createQuery("from Usuario where username=:u and password=:p",Usuario.class);
            q.setParameter("u",name);
            q.setParameter("p",password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery("from Usuario where email=:e", Usuario.class);
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
        }
        return result;
    }
}





