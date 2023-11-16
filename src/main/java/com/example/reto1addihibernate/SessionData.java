package com.example.reto1addihibernate;


import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;


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
}
