module com.example.reto1addihibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.sql;
    requires javafx.swing;
    requires jasperreports;


    opens com.example.reto1addobjectdb.domain.usuario;
    opens com.example.reto1addobjectdb.domain.pedido;
    opens com.example.reto1addobjectdb.domain.Items;
    opens com.example.reto1addobjectdb.domain.productos;

    opens com.example.reto1addobjectdb to javafx.fxml;
    opens com.example.reto1addobjectdb.controllers to javafx.fxml;

    exports com.example.reto1addobjectdb;
    exports com.example.reto1addobjectdb.controllers;


}