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

        if( user.length()<4 || pass.length()<4 ){
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color:red; -fx-text-fill: white;");

        } else{

            /* ACCESO A BASE DE DATOS PARA LA VALIDACION */
            Usuario u = (new UsuarioDAO()).validateUser( user, pass );

            if(u==null){
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color:red; -fx-text-fill: white;");
            }else {
                info.setText("Usuario " + user + "(" + pass + ") correcto");
                info.setStyle("-fx-background-color:green; -fx-text-fill: white;");

                SessionData.setCurrentUser(u);

                /* Guardar usuario en sesión e ir a la proxima ventana */

                App.ventanaPrincipal("main-view.fxml","Colección de videojuegos");
            }

        }

    }

    @FXML
    public void clickolvidar(Event event) {
    }
}