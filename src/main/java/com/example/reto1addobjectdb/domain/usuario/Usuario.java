package com.example.reto1addobjectdb.domain.usuario;

import com.example.reto1addobjectdb.domain.pedido.Pedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un usuario en la aplicación.
 *
 * Esta clase modela las características de un usuario, incluyendo su identificador,
 * nombre de usuario, contraseña, correo electrónico y pedidos asociados.
 */
@Data
@Entity
@NoArgsConstructor
public class Usuario implements Serializable {

    public Usuario(String username, String password, String email, List<Pedido> pedido) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.pedido = pedido;
    }

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Nombre de usuario del usuario.
     */

    private String username;

    /**
     * Contraseña del usuario.
     */

    private String password;

    /**
     * Correo electrónico del usuario.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email);
    }
}
