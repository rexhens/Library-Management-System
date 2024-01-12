package Views.Access;

import Controllers.FileController;
import Controllers.LibrarianController;
import Controllers.ManagerController;
import Models.AccessLevel;
import Views.AdminHomePage;
import Views.EmployeeHomePage;
import Views.Statistics.StatisticMainView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccessMainPage {
    public Scene showMainAccessPage(Stage stage)
    {
        BorderPane border = new BorderPane();

        Text text = new Text("Access Main Page");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));
        border.setTop(stack);

        Button librarianAccessButton = new Button("Librarian Access");
        Button managerAccessButton = new Button("Manager Access");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        grid.add(librarianAccessButton,0,0);
        grid.add(managerAccessButton,1,0);
        border.setCenter(grid);

        StackPane stackBackButton = new StackPane();
        Button backButton = new Button("Back");
        stackBackButton.getChildren().add(backButton);

        backButton.setOnAction(e->{
            AdminHomePage adminHomePage = new AdminHomePage();
            stage.setScene(adminHomePage.showAdminHomePage(stage));
        });
        stackBackButton.setPadding(new Insets(0, 0, 40, 0));
        border.setBottom(stackBackButton);

        librarianAccessButton.setOnAction(e->{
            LibrariansAccessView librariansAccessView = new LibrariansAccessView();
            stage.setScene(librariansAccessView.showAllLibrariansView(stage));
        });

        managerAccessButton.setOnAction(e->{
           ManagersAccessView managersAccessView = new ManagersAccessView();
           stage.setScene(managersAccessView.showManagerAccessView(stage));
        });

        return new Scene(border,700,500);
    }}
