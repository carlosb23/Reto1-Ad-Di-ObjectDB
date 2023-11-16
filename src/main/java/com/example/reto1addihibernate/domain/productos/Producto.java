package com.example.reto1addihibernate.domain.productos;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un producto en la aplicación.
 */
@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="precio")
    private int precio;
    @Column(name="cantidad")
    private Integer cantidad;

}
