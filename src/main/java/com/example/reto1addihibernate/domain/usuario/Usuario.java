package com.example.reto1addihibernate.domain.usuario;

import lombok.Data;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Clase que representa un usuario en la aplicación.
 */
@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="contraseña")
    private String contrasena;

    @Column(name = "email")
    private String email;
}
