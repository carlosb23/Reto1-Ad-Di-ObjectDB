package com.example.reto1addihibernate.domain.Items;

import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.productos.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un elemento (item) que forma parte de un pedido.
 */
@Data
@Entity
@Table(name="items")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name="codigo")
    private String codigo;*/

    @Column(name="cantidad")
    private int cantidad;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "codigo_item", referencedColumnName = "codigo_pedido")
    private Pedido codigo;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", producto=" + producto +
                ", codigo=" + codigo +
                '}';
    }
}
