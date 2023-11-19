package com.example.reto1addihibernate.domain.usuario;

import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.DAO;
import com.example.reto1addihibernate.domain.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;


/**
 * Implementación de la interfaz UsuarioDAO para acceder a la base de datos y gestionar usuarios.
 */
@Log
public class UsuarioDAO implements DAO<Usuario> {


    @Override
    public ArrayList<Usuario> getAll() {

        var salida = new ArrayList<Usuario>(0);
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuario> query = s.createQuery("from Usuario", Usuario.class);
            salida = (ArrayList<Usuario>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Usuario get(Long id) {

        var salida = new Usuario();

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(Usuario.class,id);
        }

        return salida;

    }

    @Override
    public Usuario save(Usuario data) {
        return null;
    }



    @Override
    public void delete(Usuario data) {

    }

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
    }





