package com.example.reto1addihibernate.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase de utilidad para la gestión de la SessionFactory de Hibernate.
 *
 * Esta clase proporciona métodos estáticos para acceder a la SessionFactory de Hibernate,
 * que es necesaria para interactuar con la base de datos.
 */
@Log
public class HibernateUtil {

    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con éxito!");
        } catch(Exception ex) {
            log.severe("Error al crear SessionFactory()");
        }
    }

    /**
     * Obtiene la SessionFactory de Hibernate.
     *
     * @return La SessionFactory de Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
