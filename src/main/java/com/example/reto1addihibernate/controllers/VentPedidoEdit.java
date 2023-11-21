package com.example.reto1addihibernate.controllers;

import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.Items.Item;
import com.example.reto1addihibernate.domain.Items.ItemDAO;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.pedido.PedidoDAO;
import com.example.reto1addihibernate.domain.productos.Producto;
import com.example.reto1addihibernate.domain.productos.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class VentPedidoEdit {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final ItemDAO itemDao = new ItemDAO();

    private ObservableList<Producto> listProduct;

    @javafx.fxml.FXML
    private Label Npedido;
    @javafx.fxml.FXML
    private Button volver;
    @javafx.fxml.FXML
    private Button btneliminar;
    @javafx.fxml.FXML
    private Button btnguardar;
    @javafx.fxml.FXML
    private Button btnvolver;
    @javafx.fxml.FXML
    private ComboBox<Producto> comboProduct;
    @javafx.fxml.FXML
    private Spinner spinnerCantidad;
    @javafx.fxml.FXML
    private Label labelTotal;


    @javafx.fxml.FXML
    public void initialize() {
        listProduct = FXCollections.observableArrayList();
        Npedido.setText(SessionData.getCurrentPedido().getCodigo());
        spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        listProduct.setAll(productoDAO.getAll());
        comboProduct.setItems(listProduct);

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

        Pedido pedido = SessionData.getCurrentPedido();

        if(pedido !=null){
            Item item = new Item();
            item.setCodigo(pedido);
            item.setCantidad((Integer) spinnerCantidad.getValue());
            item.setProducto(comboProduct.getSelectionModel().getSelectedItem());

            SessionData.setCurrentItem((new ItemDAO().save(item)));
            SessionData.setCurrentItem(item);

            // Volver a la ventana de datos u otra lógica según tus necesidades
            App.ventanaDatos("Views/ventana-datos.fxml");
        }
    }

}