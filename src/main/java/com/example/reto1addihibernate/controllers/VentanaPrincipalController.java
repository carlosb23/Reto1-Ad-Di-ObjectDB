package com.example.reto1addihibernate.controllers;


import com.example.reto1addihibernate.App;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana principal de la aplicación.
 */
public class VentanaPrincipalController implements Initializable {

    //@javafx.fxml.FXML
    //private TableView<Pedido> tablaproduct;
    @javafx.fxml.FXML
    private Label nomuser;
    @javafx.fxml.FXML
    private Label emailuser;
    @javafx.fxml.FXML
    private MenuItem Logout;
    @javafx.fxml.FXML
    private Button btnlogout;
    @javafx.fxml.FXML
    private TableColumn idColumn;
    @javafx.fxml.FXML
    private TableColumn columcodigo;
    @javafx.fxml.FXML
    private TableColumn columnFecha;
    @javafx.fxml.FXML
    private TableColumn columnuser;
    @javafx.fxml.FXML
    private TableColumn columTotal;
    @javafx.fxml.FXML
    private Button btnsalir;
    @javafx.fxml.FXML
    private MenuItem exitmenu;

    // Usuarios según su inicio de sesión
    private static Long userId;

    /**
     * Establece el ID del usuario actualmente logueado.
     *
     * @param userId El ID del usuario.
     */
    public static void setUserId(Long userId) {
        VentanaPrincipalController.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/login-view.fxml", "Tabla de pedidos");
        App.myStage.setTitle("Login");
    }

    @javafx.fxml.FXML
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnsalir.getScene().getWindow();
        stage.close();
    }

}