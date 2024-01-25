package com.example.reto1addobjectdb;

import com.example.reto1addobjectdb.domain.Items.Item;
import com.example.reto1addobjectdb.domain.pedido.Pedido;
import com.example.reto1addobjectdb.domain.productos.Producto;
import com.example.reto1addobjectdb.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase de almacenamiento de datos de sesión.
 *
 * Esta clase proporciona campos estáticos para almacenar información de sesión, como el usuario actual,
 * el pedido actual, el ítem actual y el producto actual.
 */
public class SessionData {

    /**
     * Usuario actual en sesión.
     */
    @Getter
    @Setter
    private static Usuario currentUser;

    /**
     * Pedido actual en sesión.
     */
    @Getter
    @Setter
    private static Pedido currentPedido;

    /**
     * Ítem actual en sesión.
     */
    @Getter
    @Setter
    private static Item currentItem;

    /**
     * Producto actual en sesión.
     */
    @Getter
    @Setter
    private static Producto currentProducto;

}
