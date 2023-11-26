package com.example.reto1addihibernate;

import com.example.reto1addihibernate.domain.usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        myStage.setTitle("Login");
        myStage.setScene(scene);
        myStage.show();
    }

    public static void ventanaPrincipal(String fxml,String title){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle(title);
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }
    public static void ventanaDatos(String fxml){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle("Ventana Pedidos");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }


    public static void ventananewpass(String fxml){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle("Ventana cambio contraseña");
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el FXML");
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}