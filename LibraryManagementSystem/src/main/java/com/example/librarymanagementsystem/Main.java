package com.example.librarymanagementsystem;

import Controllers.FileController;
import Controllers.ManagerController;
import Controllers.Modifiable;
import Views.AdminHomePage;
import Views.LogInView;
import Views.Statistics.StatisticMainView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        @SuppressWarnings("unused")
        FileController fileController = new FileController();
        LogInView lg = new LogInView();
       AdminHomePage statisticMainView = new AdminHomePage();
        Scene scene = lg.showLogInScene(stage);

        stage.setOnCloseRequest(e->
        {
            FileController.writeUsers();
            FileController.writeAuthors();
            FileController.writeBooks();
            FileController.writeCategories();
            FileController.writeTransactions();
            Platform.exit();
        });


        stage.setTitle("Library Management System");
        stage.setScene(statisticMainView.showAdminHomePage(stage));
        //stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}