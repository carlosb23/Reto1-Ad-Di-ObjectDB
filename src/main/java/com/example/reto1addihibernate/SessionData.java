package com.example.reto1addihibernate;


import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.productos.Producto;
import com.example.reto1addihibernate.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class SessionData {

    @Getter
    @Setter
    private static Usuario currentUser;

    @Getter
    @Setter
    private static Pedido currentPedido;

    @Getter
    @Setter
    private static Item currentItem;

    @Getter
    @Setter
    private static Producto currentProducto;

    @Getter
    @Setter
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    @Getter
    @Setter
    private static ArrayList<Producto> productos = new ArrayList<>();

    @Getter
    @Setter
    private static ArrayList<Item> items = new ArrayList<>();

}
