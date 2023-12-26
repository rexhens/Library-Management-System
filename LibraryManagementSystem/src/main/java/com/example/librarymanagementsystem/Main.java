package com.example.librarymanagementsystem;

import Views.AddUserView;
import Views.LogInView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AddUserView lg = new AddUserView();
        Scene scene = lg.addLibrarian(stage);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}