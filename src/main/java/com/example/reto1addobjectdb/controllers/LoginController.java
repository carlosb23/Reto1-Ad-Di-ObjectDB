package com.example.reto1addobjectdb.controllers;

import com.example.reto1addobjectdb.App;
import com.example.reto1addobjectdb.SessionData;
import com.example.reto1addobjectdb.domain.usuario.Usuario;
import com.example.reto1addobjectdb.domain.usuario.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.Optional;
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
    @FXML
    private ImageView verpass;
    @FXML
    private ImageView ocultarpass;
    @FXML
    private TextField visiblepass;

    /**
     * Inicializa el controlador de la pantalla de inicio de sesión.
     *
     * @param url             La ubicación relativa del objeto a inicializar.
     * @param resourceBundle El recurso de mensajes para esta interfaz.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
         * Esta mejora permite que al presionar la tecla 'Enter' en el campo de contraseña,
         * se simule el clic en el botón de inicio de sesión.
         */
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Simular el clic en el botón de inicio de sesión
                btnacceder.fire();
            }
        });
    }

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param actionEvent El evento de acción asociado al botón de inicio de sesión.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        Usuario usuario = (new UsuarioDAO().validateUser(user, pass));
        if (usuario != null) {
            SessionData.setCurrentUser(usuario);
            App.ventanaPrincipal("Views/ventanaPrincipal.fxml", "Pedidos del usuario");
        } else {
            info.setText("Usuario incorrecto");
            info.setStyle("-fx-background-color: red");
            txtUser.setText("");
            txtPassword.setText("");
        }
    }

    /**
     * Maneja el evento de olvido de contraseña.
     *
     * @param event El evento asociado al enlace de olvido de contraseña.
     */
    @FXML
    public void clickolvidar(Event event) {
        // Crea un cuadro de diálogo para ingresar el correo electrónico
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Olvidé mi contraseña");
        dialog.setHeaderText("Ingrese su correo electrónico en el cuadro de texto:");
        dialog.setContentText("Correo: ");

        // Obtén la respuesta del usuario
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(email -> {

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getUserByEmail(email);

            if (usuario != null) {
                SessionData.setCurrentUser(usuario);
                App.ventananewpass("Views/cambio-contraseña.fxml");
            } else {
                // Si el correo no es válido, muestra un mensaje de error
                mostrarAlerta("Error", "Correo electrónico no válido", Alert.AlertType.ERROR);
            }
        });
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

    @FXML
    public void verpass(Event event) {

        verpass.setVisible(false);
        ocultarpass.setVisible(true);

        txtPassword.setVisible(false);
        visiblepass.setText(txtPassword.getText());
        visiblepass.setVisible(true);

    }

    @FXML
    public void ocultarpass(Event event) {

        verpass.setVisible(true);
        ocultarpass.setVisible(false);
        visiblepass.setVisible(false);
        txtPassword.setVisible(true);
    }
}