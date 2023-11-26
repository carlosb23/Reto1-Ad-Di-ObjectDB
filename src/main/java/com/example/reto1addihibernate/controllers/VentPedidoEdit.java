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

/**
 * Controlador para la ventana de edición de pedidos.
 */
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
    private Button btnguardar;
    @javafx.fxml.FXML
    private ComboBox<Producto> comboProduct;
    @javafx.fxml.FXML
    private Spinner spinnerCantidad;
    @javafx.fxml.FXML
    private Label labelTotal;

    /**
     * Inicializa el controlador de la ventana de edición de pedidos.
     */
    @javafx.fxml.FXML
    public void initialize() {
        listProduct = FXCollections.observableArrayList();
        Npedido.setText(SessionData.getCurrentPedido().getCodigo());
        spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        listProduct.setAll(productoDAO.getAll());
        comboProduct.setItems(listProduct);

    }

    /**
     * Maneja el evento de volver a la ventana principal.
     *
     * @param actionEvent El evento de acción asociado al botón de volver.
     */
    @javafx.fxml.FXML
    public void btnvolverVP(ActionEvent actionEvent) {
        App.ventanaPrincipal("Views/ventanaPrincipal.fxml","");
    }

    /**
     * Maneja el evento de guardar los cambios en el pedido.
     *
     * @param actionEvent El evento de acción asociado al botón de guardar.
     */
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

            double total = calcularTotal() + (item.getCantidad() * item.getProducto().getPrecio());
            SessionData.getCurrentPedido().setTotal(total);

            pedidoDAO.update(SessionData.getCurrentPedido());

            // Volver a la ventana de datos u otra lógica según tus necesidades
            App.ventanaDatos("Views/ventana-datos.fxml");
        }
    }

    /**
     * Calcula y devuelve el total del pedido.
     *
     * @return El total del pedido.
     */
    private double calcularTotal() {
        double total = 0.0;
        for (Item item : SessionData.getCurrentPedido().getItems()) {
            total += item.getCantidad() * item.getProducto().getPrecio();
        }
        return total;
    }

}