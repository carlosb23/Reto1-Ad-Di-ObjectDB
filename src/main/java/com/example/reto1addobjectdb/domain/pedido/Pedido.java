package com.example.reto1addobjectdb.domain.pedido;

import com.example.reto1addobjectdb.domain.Items.Item;
import com.example.reto1addobjectdb.domain.usuario.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un pedido en la aplicación.
 */
@Data
@Entity
@NoArgsConstructor
public class Pedido implements Serializable {

    public Pedido(Long id, String codigo_pedido, Date fecha, Double total, Usuario usuario) {
        this.id = id;
        this.codigo_pedido = codigo_pedido;
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
    }

    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código único del pedido.
     */
    private String codigo_pedido;

    /**
     * Fecha en que se realizó el pedido.
     */
    private Date fecha;

    /**
     * Total del pedido.
     */
    private Double total;

    /**
     * Usuario asociado al pedido.
     */
    @ManyToOne
    private Usuario usuario;

    /**
     * Lista de items asociados al pedido.
     */
    @OneToMany(mappedBy = "codigo_pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(codigo_pedido, pedido.codigo_pedido) && Objects.equals(fecha, pedido.fecha) && Objects.equals(total, pedido.total) && Objects.equals(usuario, pedido.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo_pedido, fecha, total, usuario);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo_pedido='" + codigo_pedido + '\'' +
                ", fecha=" + fecha +
                ", total=" + total +
                ", usuario=" + usuario +
                ", items=" + items +
                '}';
    }
}
