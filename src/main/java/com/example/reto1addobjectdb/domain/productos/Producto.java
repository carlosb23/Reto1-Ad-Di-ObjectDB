package com.example.reto1addobjectdb.domain.productos;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Clase que representa un producto en la aplicación.
 *
 * Esta clase modela las características de un producto, incluyendo su identificador,
 * nombre, precio y cantidad disponible en inventario.
 */
@Data
@Entity
@NoArgsConstructor
public class Producto implements Serializable {

    public Producto( String nombre_producto, double precio, Integer cantidad) {
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     */

    private String nombre_producto;

    /**
     * Precio del producto.
     */

    private double precio;

    /**
     * Cantidad disponible en inventario del producto.
     */

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
                ", nombre_producto='" + nombre_producto + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}

