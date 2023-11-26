package com.example.reto1addihibernate.domain.productos;

import com.example.reto1addihibernate.domain.Items.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa un producto en la aplicación.
 *
 * Esta clase modela las características de un producto, incluyendo su identificador,
 * nombre, precio y cantidad disponible en inventario.
 */
@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Precio del producto.
     */
    @Column(name = "precio")
    private double precio;

    /**
     * Cantidad disponible en inventario del producto.
     */
    @Column(name = "cantidad")
    private Integer cantidad;

    /**
     * Representación de cadena del objeto Producto.
     *
     * @return Cadena que representa el objeto Producto.
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}

