package com.example.reto1addihibernate.controllers;


import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.Items.ItemDAO;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.pedido.PedidoDAO;
import com.example.reto1addihibernate.domain.productos.ProductoDAO;
import com.example.reto1addihibernate.domain.usuario.Usuario;
import com.example.reto1addihibernate.domain.usuario.UsuarioDAO;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class VentanaPrincipalController implements Initializable {

    private final PedidoDAO gameDao = new PedidoDAO();

    @javafx.fxml.FXML
    private TableView<Pedido> tablaproduct;
    @javafx.fxml.FXML
    private Label nomuser;
    @javafx.fxml.FXML
    private Label emailuser;
    @javafx.fxml.FXML
    private MenuItem Logout;
    @javafx.fxml.FXML
    private Button btnlogout;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> idColumn;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> columcodigo;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> columnFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> columnuser;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> columTotal;
    @javafx.fxml.FXML
    private Button btnsalir;
    @javafx.fxml.FXML
    private MenuItem exitmenu;
    @javafx.fxml.FXML
    private Label NPedidos;
    @javafx.fxml.FXML
    private Button btnañadir;

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    ObservableList<Pedido> observableListPedidos = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private Button btndeletePedido;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nomuser.setText(SessionData.getCurrentUser().getUsername());
        emailuser.setText(SessionData.getCurrentUser().getEmail());
        NPedidos.setText(SessionData.getCurrentUser().getCantidapedidos()+ " pedidos");

        idColumn.setCellValueFactory((column)->{
            String id = String.valueOf(column.getValue().getId());
            return new SimpleStringProperty(id);
        });
        columcodigo.setCellValueFactory((column)->{
            String codigo = String.valueOf(column.getValue().getCodigo());
            return new SimpleStringProperty(codigo);
        });
        columnFecha.setCellValueFactory((column)->{
            Date fecha = column.getValue().getFecha(); // Suponiendo que getFecha() devuelve un LocalDate
            String fechaFormateada = "";

            if (fecha != null) {
                SimpleDateFormat nuevafecha = new SimpleDateFormat("dd-MM-yyyy");
                fechaFormateada = nuevafecha.format(fecha);
            }

            return new SimpleStringProperty(fechaFormateada);
        });
        columnuser.setCellValueFactory((column)->{
            String user = String.valueOf(column.getValue().getUsuario().getId());
            return new SimpleStringProperty(user);
        });
        columTotal.setCellValueFactory((column)->{
            String total = String.valueOf(column.getValue().getTotal());
            return new SimpleStringProperty(total);
        });

        tablaproduct.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Doble clic detectado

                Pedido pedidoclick = tablaproduct.getSelectionModel().getSelectedItem();
                if (pedidoclick != null) {
                    SessionData.setCurrentPedido(pedidoclick);
                    App.ventanaDatos("Views/ventana-datos.fxml");
                }
            }
        });

        SessionData.setCurrentUser((new UsuarioDAO().get(SessionData.getCurrentUser().getId())));
        tablaproduct.getItems().addAll(SessionData.getCurrentUser().getPedido());

        double total = pedidoDAO.getTotalPedidos(SessionData.getCurrentUser());
        columTotal.setText(String.valueOf(total));
        columTotal.setText("TOTAL");

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

    @javafx.fxml.FXML
    public void newOrder(ActionEvent actionEvent) {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCodigo(pedidoDAO.getUltimoCodigoPedido());
        nuevoPedido.setFecha(new Date());
        nuevoPedido.setUsuario(SessionData.getCurrentUser());
        nuevoPedido.setItems(new ArrayList<>());

        // Siempre establece el total a 0 al inicio
        nuevoPedido.setTotal(0.0);

        observableListPedidos.add(nuevoPedido);
        tablaproduct.setItems(observableListPedidos);

        SessionData.setCurrentPedido((new PedidoDAO()).save(nuevoPedido));
        SessionData.setCurrentUser((new UsuarioDAO().get(SessionData.getCurrentUser().getId())));
    }

    @javafx.fxml.FXML
    public void deletePedido(ActionEvent actionEvent) {
        Pedido pedidoSeleccionado = tablaproduct.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado != null) {
            // Verificar si el pedido tiene items
            if (!pedidoSeleccionado.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("El pedido que intentas eliminar tiene items, porfavor vacie el pedido");
                alert.showAndWait();
                return;  // Salir del método si el pedido tiene items
            }

            // Mostrar confirmación antes de eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Está seguro de que desea eliminar este pedido?");
            var result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Eliminar pedido de la base de datos
                pedidoDAO.delete(pedidoSeleccionado);

                // Eliminar pedido de la tabla
                tablaproduct.getItems().remove(pedidoSeleccionado);

                // Opcional: Limpiar la selección en la tabla
                tablaproduct.getSelectionModel().clearSelection();
            }
        } else {
            // Manejar el caso en que no hay un pedido seleccionado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, seleccione un pedido para borrar.");
            alert.showAndWait();
        }

    }
}