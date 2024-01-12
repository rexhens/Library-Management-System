package Views.Access;

import Controllers.FileController;
import Controllers.ManagerController;
import Models.AccessLevel;
import Models.Manager;
import Models.User;
import Views.AddManagerView;
import Views.AdminHomePage;
import Views.EmployeeHomePage;
import Views.ManageManagerView;
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

public class ManagersAccessView {
    public Scene showManagerAccessView(Stage stage)
    {
        ArrayList<Manager> managers = new ArrayList<>();
        ArrayList<Button> managersNameBtn = new ArrayList<>();

        for (User manager : FileController.users) {
            if(manager instanceof Manager){
                managers.add((Manager)manager);
                managersNameBtn.add(new Button(manager.getName()));
            }
        }

        Button backBtn = new Button("Back");
        managersNameBtn.add(backBtn);

        BorderPane border = new BorderPane();

        Text text = new Text("Manager Access");
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
                    EmployeeHomePage employeeHomePage = new EmployeeHomePage(manager);
                    stage.setScene(employeeHomePage.showView(stage, AccessLevel.Admin));

                } else if (finalI == managersNameBtn.size() - 1) { // Back button
                    AccessMainPage adminHomePage = new AccessMainPage();
                    stage.setScene(adminHomePage.showMainAccessPage(stage));
                }
            });
        }

        border.setCenter(gridPane);
        return new Scene(border, 700, 500);
    }
}
