package com.example.reto1addobjectdb;

import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Utilidad para interactuar con la base de datos ObjectDB.
 * Esta clase proporciona un EntityManagerFactory para obtener instancias de EntityManager
 * y administrar la interacción con la base de datos.
 */
@Log
public class ObjectDBUtil {

    @Getter
    private final static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }
}
