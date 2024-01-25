package com.example.reto1addobjectdb.domain.usuario;

import com.example.reto1addobjectdb.domain.pedido.Pedido;
import lombok.Data;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario en la aplicación.
 *
 * Esta clase modela las características de un usuario, incluyendo su identificador,
 * nombre de usuario, contraseña, correo electrónico y pedidos asociados.
 */
@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario del usuario.
     */
    @Column(name="nombre")
    private String username;

    /**
     * Contraseña del usuario.
     */
    @Column(name="contraseña")
    private String password;

    /**
     * Correo electrónico del usuario.
     */
    @Column(name = "email")
    private String email;

    /**
     * Cantidad de pedidos realizados por el usuario. (Atributo transitorio no persistente en la base de datos)
     */
    @Transient
    private Long cantidapedidos;

    /**
     * Lista de pedidos asociados al usuario.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedido = new ArrayList<>();

    /**
     * Obtiene la cantidad de pedidos realizados por el usuario.
     *
     * @return La cantidad de pedidos realizados por el usuario.
     */
    public Long getCantidapedidos() {
        cantidapedidos = (long) pedido.size();
        return cantidapedidos;
    }
}
