package Views.Access;

import Controllers.FileController;
import Controllers.LibrarianController;
import Models.AccessLevel;
import Models.Librarian;
import Models.User;
import Views.AddLibrarianView;
import Views.AdminHomePage;
import Views.EmployeeHomePage;
import Views.ManageLibrarianView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LibrariansAccessView {
    public Scene showAllLibrariansView(Stage stage){
        ArrayList<Librarian> librarians = new ArrayList<>();
        ArrayList<Button> librarianNameBtn = new ArrayList<>();

        for (User librarian : FileController.users) {
            if(librarian instanceof Librarian){
                librarians.add((Librarian)librarian);
                librarianNameBtn.add(new Button(librarian.getName()));
            }
        }

        Button backBtn = new Button("Back");
        librarianNameBtn.add(backBtn);

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
                    EmployeeHomePage employeeHomePage = new EmployeeHomePage(librarian);
                    stage.setScene(employeeHomePage.showView(stage, AccessLevel.Admin));
                } else if (finalI == librarianNameBtn.size() - 1) { // Back button
                    AccessMainPage adminHomePage = new AccessMainPage();
                    stage.setScene(adminHomePage.showMainAccessPage(stage));
                }
            });
        }

        border.setCenter(gridPane);
        return new Scene(border, 700, 500);
    }
}
