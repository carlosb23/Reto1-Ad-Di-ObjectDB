module com.example.reto1addihibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.sql;


    opens com.example.reto1addihibernate.controllers to javafx.fxml;
    opens com.example.reto1addihibernate to javafx.fxml;

    exports com.example.reto1addihibernate;
}