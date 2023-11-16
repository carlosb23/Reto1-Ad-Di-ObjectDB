package com.example.reto1addihibernate.controllers;


import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.usuario.Usuario;
import com.example.reto1addihibernate.domain.usuario.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la pantalla de inicio de sesión (login).
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnacceder;
    @FXML
    private Label info;
    @FXML
    private Label olvicontra;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        Usuario usuario = (new UsuarioDAO().validateUser(user,pass)) ;
        if (usuario != null) {
            SessionData.setCurrentUser(usuario);
            App.ventanaPrincipal("Views/ventanaPrincipal.fxml","Pedidos del usuario");
        } else {
            info.setText("Usuario incorrecto");
            info.setStyle("-fx-background-color: red");
            txtUser.setText("");
            txtPassword.setText("");
        }

    }

    @FXML
    public void clickolvidar(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuarios");
        alert.setContentText("Usuario1: Carlos, Contraseña: 1234\n" +
                "Usuario2: Leo, Contraseña: 1234");
        alert.showAndWait();
    }
}