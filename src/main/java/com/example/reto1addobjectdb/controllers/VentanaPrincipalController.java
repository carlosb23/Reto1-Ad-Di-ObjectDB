package com.example.reto1addobjectdb.controllers;

import com.example.reto1addobjectdb.App;
import com.example.reto1addobjectdb.SessionData;
import com.example.reto1addobjectdb.domain.Items.ItemDAO;
import com.example.reto1addobjectdb.domain.pedido.Pedido;
import com.example.reto1addobjectdb.domain.pedido.PedidoDAO;
import com.example.reto1addobjectdb.domain.productos.ProductoDAO;
import com.example.reto1addobjectdb.domain.usuario.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana principal de la aplicación.
 */
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

    /**
     * Inicializa el controlador de la ventana principal.
     *
     * @param url            La ubicación relativa del objeto a inicializar.
     * @param resourceBundle El recurso de mensajes para esta interfaz.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomuser.setText(SessionData.getCurrentUser().getUsername());
        emailuser.setText(SessionData.getCurrentUser().getEmail());
        NPedidos.setText(SessionData.getCurrentUser().getCantidapedidos() + " pedidos");

        idColumn.setCellValueFactory((column) -> {
            String id = String.valueOf(column.getValue().getId());
            return new SimpleStringProperty(id);
        });
        columcodigo.setCellValueFactory((column) -> {
            String codigo = String.valueOf(column.getValue().getCodigo_pedido());
            return new SimpleStringProperty(codigo);
        });
        columnFecha.setCellValueFactory((column) -> {
            Date fecha = column.getValue().getFecha();
            String fechaFormateada = "";

            if (fecha != null) {
                SimpleDateFormat nuevafecha = new SimpleDateFormat("dd-MM-yyyy");
                fechaFormateada = nuevafecha.format(fecha);
            }

            return new SimpleStringProperty(fechaFormateada);
        });
        columnuser.setCellValueFactory((column) -> {
            String user = String.valueOf(column.getValue().getUsuario().getId());
            return new SimpleStringProperty(user);
        });
        columTotal.setCellValueFactory((column) -> {
            String total = String.valueOf(column.getValue().getTotal());
            return new SimpleStringProperty(total);
        });

        tablaproduct.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
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

    /**
     * Maneja el evento de cerrar sesión.
     *
     * @param actionEvent El evento de acción asociado al botón de cerrar sesión.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/login-view.fxml", "Tabla de pedidos");
        App.myStage.setTitle("Login");
    }

    /**
     * Maneja el evento de salir de la aplicación.
     *
     * @param actionEvent El evento de acción asociado al botón de salir.
     */
    @javafx.fxml.FXML
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnsalir.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento de crear un nuevo pedido.
     *
     * @param actionEvent El evento de acción asociado al botón de añadir nuevo pedido.
     */
    @javafx.fxml.FXML
    public void newOrder(ActionEvent actionEvent) {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCodigo_pedido(pedidoDAO.getUltimoCodigoPedido());
        nuevoPedido.setFecha(new Date());
        nuevoPedido.setUsuario(SessionData.getCurrentUser());
        nuevoPedido.setItems(new ArrayList<>());
        nuevoPedido.setTotal(0.0);

        // Obtener la lista actual de pedidos de la tabla
        ObservableList<Pedido> pedidosActuales = tablaproduct.getItems();

        // Agregar el nuevo pedido a la lista
        pedidosActuales.add(nuevoPedido);

        // Establecer la lista actualizada de pedidos en la tabla
        tablaproduct.setItems(pedidosActuales);

        // Guardar el nuevo pedido en la base de datos
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.save(nuevoPedido);

        // Actualizar el usuario actual en SessionData
        SessionData.setCurrentUser((new UsuarioDAO().get(SessionData.getCurrentUser().getId())));

    }

    /**
     * Maneja el evento de eliminar un pedido.
     *
     * @param actionEvent El evento de acción asociado al botón de eliminar pedido.
     */
    @javafx.fxml.FXML
    public void deletePedido(ActionEvent actionEvent) {
        Pedido pedidoSeleccionado = tablaproduct.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado != null) {
            // Mostrar confirmación antes de eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Está seguro de que desea eliminar este pedido?");
            var result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Eliminar pedido de la base de datos
                pedidoDAO.delete(pedidoSeleccionado);

                // Opcional: Eliminar items asociados al pedido (comentar o descomentar según necesidades)
                // itemDao.deleteByPedido(pedidoSeleccionado);

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
