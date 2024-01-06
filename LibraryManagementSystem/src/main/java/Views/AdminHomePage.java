package Views;

import Controllers.FileController;
import Controllers.LibrarianController;
import Models.Admin;
import Models.Librarian;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AdminHomePage extends Application {
    public Scene showAdminHomePage(Stage stage)
    {
        BorderPane border = new BorderPane();

        Text text = new Text("Manage Employees");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));
        border.setTop(stack);

        Button manageLibrarianBtn = new Button("Manage Librarians");
        Button manageManagerBtn = new Button("Manage Manager");
        Button statisticBtn = new Button("Statistics");
        Button logOutbtn = new Button("Log Out");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(manageLibrarianBtn, 0, 0);
        grid.add(manageManagerBtn, 1, 0);
        grid.add(statisticBtn, 2, 0);
        grid.add(logOutbtn, 3, 0);
        border.setCenter(grid);

        manageLibrarianBtn.setOnAction(e -> {

            stage.setScene(manageLibrariansView(stage));
        });
        manageManagerBtn.setOnAction(e ->{
            AddUserView addUserView = new AddUserView();
        });

        logOutbtn.setOnAction(e -> {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("You have been logged out!");
            error.showAndWait();
            LogInView logInView = new LogInView();
            stage.setScene(logInView.showLogInScene(stage));
        });
        return new Scene(border,700,500);
    }

    public Scene manageLibrariansView(Stage stage) {
        ArrayList<Librarian> librarians = FileController.librarians;
        ArrayList<Button> librarianNameBtn = new ArrayList<>();

        for (Librarian librarian : librarians) {
            librarianNameBtn.add(new Button(librarian.getName()));
        }

        Button backBtn = new Button("Back");
        Button addNewLibrarianBtn = new Button("Add new Librarian");
        librarianNameBtn.add(backBtn);
        librarianNameBtn.add(addNewLibrarianBtn);

        BorderPane border = new BorderPane();

        Text text = new Text("Home Page");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));
        border.setTop(stack);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        int row = 0;
        HBox currentHBox = new HBox();

        for (int i = 0; i < librarianNameBtn.size(); i++) {
            int finalI = i; // Capture the correct value of i
            currentHBox.getChildren().add(librarianNameBtn.get(i));

            if ((i + 1) % 5 == 0 || i == librarianNameBtn.size() - 1) {
                gridPane.add(currentHBox, 0, row++);
                currentHBox = new HBox();
            }

            librarianNameBtn.get(i).setOnAction(e -> {
                if (finalI < librarians.size()) {
                    LibrarianController librarianController = new LibrarianController();
                    Librarian librarian = librarianController.findLibrarian(finalI);
                    EditLibrarianView librarianDetails = new EditLibrarianView();
                    stage.setScene(librarianDetails.editLibrarian(stage, librarian));
                } else if (finalI == librarianNameBtn.size() - 2) { // Back button
                    AdminHomePage adminHomePage = new AdminHomePage();
                    stage.setScene(adminHomePage.showAdminHomePage(stage));
                } else if (finalI == librarianNameBtn.size() - 1) { // Add new Librarian button
                    AddUserView addUserView = new AddUserView();
                    stage.setScene(addUserView.addLibrarian(stage));
                }
            });
        }

        border.setCenter(gridPane);
        return new Scene(border, 700, 500);
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FileController fileController = new FileController();
//
//        ArrayList<Librarian> setLibrarians = new ArrayList<>();
//          setLibrarians.add(new Librarian("name1","surname1","username1","password",100,"23423424"));
//          setLibrarians.add(new Librarian("name2","surname2","username2","password",100,"23423424"));
//         setLibrarians.add(new Librarian("name3","surname3","username3","password",100,"23423424"));
//            Admin.setLibrarians(setLibrarians);
//            fileController.writeToFile(FileController.librarians,"users.dat");
//        Admin.setLibrarians(fileController.readFromFile("users.dat"));
//        Scene scene = manageLibrariansView(stage);
//        stage.setTitle("Library Management System");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
