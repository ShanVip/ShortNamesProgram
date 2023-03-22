package com.diplom.ShortNamesProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DB db = new DB();
        try {
            db.isConnected();
            db.createTable("shorts");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Сокращение ссылок");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}