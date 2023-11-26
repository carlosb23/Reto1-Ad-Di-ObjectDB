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
@Table(name = "items")
public class Item implements Serializable {

    /**
     * Identificador único del item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cantidad de productos asociados al item.
     */
    @Column(name = "cantidad")
    private int cantidad;

    /**
     * Producto asociado al item.
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Producto producto;

    /**
     * Pedido al que pertenece el item.
     */
    @ManyToOne
    @JoinColumn(name = "codigo_item", referencedColumnName = "codigo_pedido", nullable = false)
    private Pedido codigo;

    /**
     * Representación de cadena del objeto Item.
     *
     * @return Cadena que representa el objeto Item.
     */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", producto=" + producto.getNombre() +
                ", codigo=" + codigo +
                '}';
    }
}



