package com.example.librarymanagementsystem;

import Views.LogInView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LogInView lg = new LogInView();
        Scene scene = lg.showScene(stage);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}