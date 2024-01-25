package com.example.reto1addobjectdb.domain.pedido;

import com.example.reto1addobjectdb.domain.Items.Item;
import com.example.reto1addobjectdb.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un pedido en la aplicación.
 */
@Data
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código único del pedido.
     */
    @Getter
    @Column(name = "codigo_pedido")
    private String codigo_pedido;

    /**
     * Fecha en que se realizó el pedido.
     */
    @Column(name = "fecha")
    private Date fecha;

    /**
     * Total del pedido.
     */
    @Column(name = "total")
    private Double total;

    /**
     * Usuario asociado al pedido.
     */
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    /**
     * Lista de items asociados al pedido.
     */
    @OneToMany(mappedBy = "codigo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    /**
     * Constructor por defecto de la clase Pedido.
     */
    public Pedido() {
    }

    public void setCodigo_pedido(String codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    /**
     * Representación de cadena del objeto Pedido.
     *
     * @return Cadena que representa el objeto Pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo_pedido + '\'' +
                ", fecha=" + fecha +
                ", total=" + total +
                ", usuario=" + usuario.getId() +
                ", items=" + items +
                '}';
    }
}
