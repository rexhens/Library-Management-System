package Views;

import Controllers.FileController;
import Controllers.LibrarianController;
import Controllers.ManagerController;
import Models.Librarian;
import Models.Manager;
import Models.User;
import Views.Statistics.StatisticMainView;
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
           stage.setScene(manageManagersView(stage));
        });
        statisticBtn.setOnAction(e->{
            StatisticMainView statisticMainView = new StatisticMainView();
            stage.setScene(statisticMainView.showStatisticsView(stage));
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
        ArrayList<Librarian> librarians = new ArrayList<>();
        ArrayList<Button> librarianNameBtn = new ArrayList<>();

        for (User librarian : FileController.users) {
            if(librarian instanceof Librarian){
                librarians.add((Librarian)librarian);
                librarianNameBtn.add(new Button(librarian.getName()));
            } 
        }

        Button backBtn = new Button("Back");
        Button addNewLibrarianBtn = new Button("Add new Librarian");
        librarianNameBtn.add(backBtn);
        librarianNameBtn.add(addNewLibrarianBtn);

        BorderPane border = new BorderPane();

        Text text = new Text("Manage Librarians");
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
        HBox currentHBox = new HBox(10);

        for (int i = 0; i < librarianNameBtn.size(); i++) {
            int finalI = i; // Capture the correct value of i
            currentHBox.getChildren().add(librarianNameBtn.get(i));

            if ((i + 1) % 5 == 0 || i == librarianNameBtn.size() - 1) {
                gridPane.add(currentHBox, 0, row++);
                currentHBox = new HBox(10);
            }

            librarianNameBtn.get(i).setOnAction(e -> {
                if (finalI < librarians.size()) {
                    LibrarianController librarianController = new LibrarianController();
                    Librarian librarian = librarianController.findLibrarian(finalI);
                    ManageLibrarianView librarianDetails = new ManageLibrarianView();
                    stage.setScene(librarianDetails.showManageLibrarianView(librarian, stage));
                } else if (finalI == librarianNameBtn.size() - 2) { // Back button
                    AdminHomePage adminHomePage = new AdminHomePage();
                    stage.setScene(adminHomePage.showAdminHomePage(stage));
                } else if (finalI == librarianNameBtn.size() - 1) { // Add new Librarian button
                    AddLibrarianView addUserView = new AddLibrarianView();
                    stage.setScene(addUserView.addLibrarian(stage));
                }
            });
        }

        border.setCenter(gridPane);
        return new Scene(border, 700, 500);
    }


    public Scene manageManagersView(Stage stage) {
        ArrayList<Manager> managers = new ArrayList<>();
        ArrayList<Button> managersNameBtn = new ArrayList<>();

        for (User manager : FileController.users) {
            if(manager instanceof Manager){
                managers.add((Manager)manager);
                managersNameBtn.add(new Button(manager.getName()));
            }
        }

        Button backBtn = new Button("Back");
        Button addNewLibrarianBtn = new Button("Add new Manager");
        managersNameBtn.add(backBtn);
        managersNameBtn.add(addNewLibrarianBtn);

        BorderPane border = new BorderPane();

        Text text = new Text("Manage Managers");
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
        HBox currentHBox = new HBox(10);

        for (int i = 0; i < managersNameBtn.size(); i++) {
            int finalI = i; // Capture the correct value of i
            currentHBox.getChildren().add(managersNameBtn.get(i));

            if ((i + 1) % 5 == 0 || i == managersNameBtn.size() - 1) {
                gridPane.add(currentHBox, 0, row++);
                currentHBox = new HBox(10);
            }

            managersNameBtn.get(i).setOnAction(e -> {
                if (finalI < managers.size()) {

                    ManagerController managerController = new ManagerController();
                    Manager manager = managerController.findManagerByIndex(finalI);
                  ManageManagerView manageManagersView = new ManageManagerView();
                    stage.setScene(manageManagersView.showManageManagerView(manager,stage));

                } else if (finalI == managersNameBtn.size() - 2) { // Back button
                    AdminHomePage adminHomePage = new AdminHomePage();
                    stage.setScene(adminHomePage.showAdminHomePage(stage));
                } else if (finalI == managersNameBtn.size() - 1) { // Add new Manager button
                    AddManagerView addUserView = new AddManagerView();
                    stage.setScene(addUserView.showAddManagerView(stage));
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
