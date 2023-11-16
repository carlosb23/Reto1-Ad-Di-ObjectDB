package com.example.reto1addihibernate.domain.pedido;


import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un pedido en la aplicación.
 */
@Data
@Entity
@Table(name="pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="codigo")
    private String codigo;

    @Column(name="fecha")
    private Date fecha;

    @Column(name="usuario")
    private Integer usuario;

    @Column(name="total")
    private Integer total;

    /*@ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "")
    private Usuario user;*/

    /*@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();*/


    public Pedido() {
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", total=" + total +
                '}';
    }
}
