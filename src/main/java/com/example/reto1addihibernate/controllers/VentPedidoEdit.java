package com.example.reto1addihibernate.controllers;

import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.pedido.PedidoDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class VentPedidoEdit {

    private final PedidoDAO pedidoDAO = new PedidoDAO();

    @javafx.fxml.FXML
    private Label Npedido;
    @javafx.fxml.FXML
    private Button volver;
    @javafx.fxml.FXML
    private TextField cdpedido;
    @javafx.fxml.FXML
    private DatePicker datefecha;
    @javafx.fxml.FXML
    private TextField txtusuario;
    @javafx.fxml.FXML
    private TextField txttotal;
    @javafx.fxml.FXML
    private Button btneliminar;
    @javafx.fxml.FXML
    private Button btnguardar;
    @javafx.fxml.FXML
    private Button btnvolver;

    @javafx.fxml.FXML
    public void initialize() {

        Npedido.setText(SessionData.getCurrentPedido().getCodigo());






    }

    @javafx.fxml.FXML
    public void btnvolverVP(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/ventanaPrincipal.fxml","");
    }

    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Desea borrar " + SessionData.getCurrentPedido().getCodigo() + "de la tabla pedidos");
        var result = alert.showAndWait().get();

        if (result.getButtonData()==ButtonBar.ButtonData.OK_DONE){
            pedidoDAO.delete(SessionData.getCurrentPedido());
            btnvolverVP(null);
        }

    }



    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {

        Pedido p = SessionData.getCurrentPedido();



    }

}