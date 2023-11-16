package com.example.reto1addihibernate.domain.usuario;

import com.example.reto1addihibernate.domain.pedido.Pedido;
import lombok.Data;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String username;

    @Column(name="contraseña")
    private String password;

    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedido = new ArrayList<>();
}
