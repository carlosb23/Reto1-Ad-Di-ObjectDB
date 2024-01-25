package com.example.reto1addobjectdb.domain.productos;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

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
    @Column(name = "nombre_producto")
    private String nombre_producto;

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
                ", nombre='" + nombre_producto + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}

