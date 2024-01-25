package com.example.reto1addihibernate.controllers;

import com.example.reto1addihibernate.App;
import com.example.reto1addihibernate.SessionData;
import com.example.reto1addihibernate.domain.HibernateUtil;
import com.example.reto1addihibernate.domain.Items.ItemDAO;
import com.example.reto1addihibernate.domain.pedido.Pedido;
import com.example.reto1addihibernate.domain.pedido.PedidoDAO;
import com.example.reto1addihibernate.domain.productos.ProductoDAO;
import com.example.reto1addihibernate.domain.usuario.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    @javafx.fxml.FXML
    private Button Informe;

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

        observableListPedidos.add(nuevoPedido);
        tablaproduct.setItems(observableListPedidos);

        SessionData.setCurrentPedido((new PedidoDAO()).save(nuevoPedido));
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

    @javafx.fxml.FXML
    public void vdinforme(ActionEvent actionEvent) {
        // Manejar el evento de selección de un pedido en la tabla
        Pedido pedidoSeleccionado = tablaproduct.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado == null) {
            // Manejar el caso en que no hay un pedido seleccionado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, seleccione un pedido para generar el informe.");
            alert.showAndWait();
            return;
        }

        // Ahora, el pedidoSeleccionado contiene el pedido seleccionado por el usuario
        generarInforme(pedidoSeleccionado);
    }

    private void generarInforme(Pedido pedidos) {

        String pedido = pedidos.getCodigo_pedido();

        // Crea un nuevo escenario para mostrar el informe.
        Stage primaryStage = new Stage();

        //Comprobar que el pedido pulsado es el resultante en el informe
        //System.out.println(pedido);

        try {
            // Obtiene la SessionFactory desde HibernateUtil.
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            // Abre una sesión de Hibernate.
            try (Session session = sessionFactory.openSession()) {
                // Configura los parámetros del informe, en este caso, el código del pedido.
                HashMap<String, Object> parameter = new HashMap<>();
                parameter.put("nombreEmpresa", "Gestion de Pedidos S.L");
                parameter.put("pedido", pedido);

                // Obtiene la conexión de la sesión de Hibernate.
                Connection connection = session.doReturningWork((Connection connectionProvider) ->
                        connectionProvider.unwrap(Connection.class));

                // Llena el informe Jasper utilizando los parámetros y la conexión a la Base de Datos.
                JasperPrint jasperPrint = JasperFillManager.fillReport("GestorPedidos.jasper", parameter, connection);

                // Crea un nodo Swing para integrar el visor de informes Jasper en la aplicación JavaFX.
                SwingNode swingNode = new SwingNode();
                contenidoEnSwing(swingNode, jasperPrint);

                // Configura la interfaz gráfica del nuevo escenario.
                StackPane root = new StackPane();
                root.getChildren().add(swingNode);
                Scene scene = new Scene(root, 800, 600);
                primaryStage.getIcons().add(new Image("C:\\Users\\PC\\Downloads\\Reto1-Ad-Di-Hibernate\\src\\main\\resources\\com\\example\\reto1addihibernate\\img\\logo.png", 100, 100, true, true));
                primaryStage.setTitle("Detalles Pedido");
                primaryStage.setScene(scene);
                primaryStage.show();

                //Nos aseguramos de que cada informe sea unico añadiendo su numero de pedido
                String nombreArchivoPDF = "GestorPedido_" + pedido + ".pdf";

                // Exporta el informe a un archivo PDF con el nombre único.
                JRPdfExporter pdf = new JRPdfExporter();
                pdf.setExporterInput(new SimpleExporterInput(jasperPrint));
                pdf.setExporterOutput(new SimpleOutputStreamExporterOutput(nombreArchivoPDF));
                pdf.setConfiguration(new SimplePdfExporterConfiguration());
                pdf.exportReport();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }


    private void contenidoEnSwing(final SwingNode swingNode, JasperPrint jasperPrint) {
        SwingUtilities.invokeLater(() -> {
            //Crea un visor Jasper y lo establece como contenido del nodo Swing.
            JRViewer viewer = new JRViewer(jasperPrint);
            swingNode.setContent(viewer);
        });
    }
}
