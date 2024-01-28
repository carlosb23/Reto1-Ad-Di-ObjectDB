package com.example.reto1addobjectdb.controllers;

import com.example.reto1addobjectdb.App;
import com.example.reto1addobjectdb.SessionData;
import com.example.reto1addobjectdb.domain.Items.Item;
import com.example.reto1addobjectdb.domain.Items.ItemDAO;
import com.example.reto1addobjectdb.domain.pedido.Pedido;
import com.example.reto1addobjectdb.domain.pedido.PedidoDAO;
import com.example.reto1addobjectdb.domain.productos.Producto;
import com.example.reto1addobjectdb.domain.productos.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

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
        Npedido.setText(SessionData.getCurrentPedido().getCodigo_pedido());
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

        if(pedido != null) {
            Item item = new Item();
            item.setCodigo_pedido(pedido);
            item.setCantidad((Integer) spinnerCantidad.getValue());
            item.setProducto(comboProduct.getSelectionModel().getSelectedItem());

            // Guardar el nuevo ítem en la base de datos
            Item nuevoItem = itemDao.save(item);

            // Actualizar la lista de ítems del pedido en SessionData
            pedido.getItems().add(nuevoItem);

            // Actualizar el total del pedido sumando el precio del nuevo ítem
            double total = pedido.getTotal() + (nuevoItem.getCantidad() * nuevoItem.getProducto().getPrecio());
            pedido.setTotal(total);

            // Actualizar el pedido en la base de datos
            pedidoDAO.update(pedido);

            // Volver a la ventana de datos
            App.ventanaDatos("Views/ventana-datos.fxml");
        }
    }

    /**
     * Calcula y devuelve el total del pedido.
     *
     * @return El total del pedido.
     */

}