package com.example.reto1addobjectdb.controllers;

import com.example.reto1addobjectdb.App;
import com.example.reto1addobjectdb.SessionData;
import com.example.reto1addobjectdb.domain.usuario.UsuarioDAO;
import com.example.reto1addobjectdb.domain.usuario.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la interfaz de cambio de contraseña.
 */
public class CambioContraseña implements Initializable {

    @javafx.fxml.FXML
    private PasswordField passuser1;
    @javafx.fxml.FXML
    private PasswordField passuser2;
    @javafx.fxml.FXML
    private Button btnsavepass;
    @javafx.fxml.FXML
    private Label nombreuser;

    /**
     * Inicializa la interfaz de cambio de contraseña.
     *
     * @param url             La ubicación relativa del objeto a inicializar.
     * @param resourceBundle El recurso de mensajes para esta interfaz.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombreuser.setText(SessionData.getCurrentUser().getUsername());
    }

    /**
     * Maneja el evento de guardar nueva contraseña.
     *
     * @param actionEvent El evento de acción asociado al botón de guardar contraseña.
     */
    @javafx.fxml.FXML
    public void saveNewPass(ActionEvent actionEvent) {
        String newPassword1 = passuser1.getText();
        String newPassword2 = passuser2.getText();

        if (newPassword1.isEmpty() || newPassword2.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete ambos campos de contraseña.", Alert.AlertType.ERROR);
            return;
        }

        if (!newPassword1.equals(newPassword2)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden.", Alert.AlertType.ERROR);
            return;
        }

        // Si las contraseñas coinciden, actualiza la contraseña en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario currentUser = SessionData.getCurrentUser();
        currentUser.setPassword(newPassword1);

        // Actualiza la contraseña en la base de datos
        usuarioDAO.update(currentUser);

        mostrarAlerta("Éxito", "Contraseña actualizada exitosamente.", Alert.AlertType.INFORMATION);

        App.ventanaPrincipal("Views/login-view.fxml", "Tabla de pedidos");
        App.myStage.setTitle("Login");
    }

    /**
     * Muestra una alerta con el título, mensaje y tipo de alerta dados.
     *
     * @param titulo El título de la alerta.
     * @param mensaje El mensaje a mostrar en la alerta.
     * @param tipo El tipo de alerta (por ejemplo, AlertType.ERROR, AlertType.INFORMATION).
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}