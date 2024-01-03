package com.example.librarymanagementsystem;

import Controllers.FileController;
import Models.Admin;
import Models.Librarian;
import Models.User;
import Views.AddUserView;
import Views.LogInView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FileController fileController = new FileController();
        Admin.setLibrarians(fileController.readFromFile("users.dat"));
        LogInView lg = new LogInView();
        Scene scene = lg.showLogInScene(stage);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}