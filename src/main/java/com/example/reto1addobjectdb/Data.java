package com.example.reto1addobjectdb;
import com.example.reto1addobjectdb.domain.productos.Producto;
import com.example.reto1addobjectdb.domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;


public class Data {

    public static List<Producto> generarProductos(){
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Smartphone", 299.00, 50));
        productos.add(new Producto("Portátil", 799.00, 30));
        productos.add(new Producto("Auriculares", 79.00, 100));
        productos.add(new Producto("Televisor", 599.00, 20));
        productos.add(new Producto("Tablet", 199.00, 40));
        productos.add(new Producto("PC Sobremesa",950.00,65));
        return productos;
    }

    public static List<Usuario> generarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Carlos","12345","carloscorreo@gmail.com",new ArrayList<>()));
        usuarios.add(new Usuario("Leo","1234","leo@gmail.com",new ArrayList<>()));
        return usuarios;
    }
}
