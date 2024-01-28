package com.example.reto1addobjectdb.domain.Items;

import com.example.reto1addobjectdb.domain.pedido.Pedido;
import com.example.reto1addobjectdb.domain.productos.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase que representa un elemento (item) que forma parte de un pedido.
 */
@Data
@Entity
@NoArgsConstructor
public class Item implements Serializable {

    /**
     * Identificador único del item.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Cantidad de productos asociados al item.
     */
    private int cantidad;

    /**
     * Producto asociado al item.
     */
    @OneToOne
    private Producto producto;

    /**
     * Pedido al que pertenece el item.
     */
    @ManyToOne
    private Pedido codigo_pedido;

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
                ", producto=" + producto +
                ", codigo=" + codigo_pedido.getCodigo_pedido() +
                '}';
    }
}



