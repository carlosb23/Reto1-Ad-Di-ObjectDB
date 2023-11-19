package com.example.reto1addihibernate.controllers;


import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.pedido.PedidoDAO;
import com.example.reto1addihibernate.domain.usuario.UsuarioDAO;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        tablaproduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pedido>() {
            @Override
            public void changed(ObservableValue<? extends Pedido> observableValue, Pedido pedido, Pedido t1) {
                if (t1 != null) {
                    Pedido pedidoclick = tablaproduct.getSelectionModel().getSelectedItem();
                    SessionData.setCurrentPedido(pedidoclick);
                    App.ventanaDatos("Views/ventana-datos.fxml");
                }
            }
        });

        SessionData.setCurrentUser((new UsuarioDAO().get(SessionData.getCurrentUser().getId())));
        tablaproduct.getItems().addAll(SessionData.getCurrentUser().getPedido());
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
        var p = new Pedido();
        p.setUsuario(SessionData.getCurrentUser());
        p.setFecha(new Date());
        SessionData.setCurrentPedido(p);

        App.ventanaeditPedidos("Views/ventana_edit_pedido.fxml");


    }
}