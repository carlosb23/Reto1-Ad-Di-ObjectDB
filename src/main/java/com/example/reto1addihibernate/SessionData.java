package com.example.reto1addihibernate;


import com.example.reto1addihibernate.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;


public class SessionData {

    @Getter
    @Setter
    private static Usuario currentUser;


}
