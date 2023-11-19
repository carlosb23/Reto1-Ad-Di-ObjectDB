package com.example.reto1addihibernate.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.Items.ItemDAO;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.pedido.PedidoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;


public class VentanaDatosController implements Initializable {
    @FXML
    private TableView tabledato;
    @FXML
    private TableColumn<Item, String> columId;
    @FXML
    private TableColumn<Item, String> columcodigo;
    @FXML
    private TableColumn<Item, String> columcantidad;
    @FXML
    private TableColumn<Item, String> columproduct;
    @FXML
    private Button volver;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        columId.setCellValueFactory((fila)->{
           String id = String.valueOf(fila.getValue().getId());
           return new SimpleStringProperty(id);
        });
        columcodigo.setCellValueFactory((fila)->{
            String codigo = String.valueOf(fila.getValue().getCodigo().getCodigo());
            return new SimpleStringProperty(codigo);
        });
        columcantidad.setCellValueFactory((fila)->{
            String cantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cantidad);
        });
        columproduct.setCellValueFactory((fila)->{
            String product = String.valueOf(fila.getValue().getProducto());
            return new SimpleStringProperty(product);
        });


        SessionData.setCurrentPedido((new PedidoDAO().get(SessionData.getCurrentPedido().getId())));
        tabledato.getItems().addAll(SessionData.getCurrentPedido().getItems());

    }


  @FXML
    public void btnvolverVP(ActionEvent actionEvent) {
        App.ventanaDatos("Views/ventanaPrincipal.fxml");
    }
}