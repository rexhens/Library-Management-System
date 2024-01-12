package Views;

import Controllers.CategoryController;
import Models.AccessLevel;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCategoryView {
    private User currentUser;
    public AddCategoryView(User currentUser) {
        this.currentUser = currentUser;
    }
    public Scene addCategory(Stage stage){

        BorderPane bp = new BorderPane();
        Text title = new Text("Add Category");
        StackPane p1 = new StackPane();
        title.setFont(new Font(30));
        p1.getChildren().add(title);
        p1.setPadding(new Insets(20));
        bp.setTop(p1);

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        gp.setAlignment(Pos.CENTER);

        Label name = new Label("Name of Category");
        TextField nameT = new TextField();
        gp.add(name,0,0);
        gp.add(nameT,1,0);

        Button registerButton = new Button("Register Category");
        registerButton.setOnAction(e->{
            CategoryController controller=new CategoryController();
            var added = controller.createCategory(nameT.getText());
            if(added != null)
            {
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setHeaderText("Category was successfully added!");
                success.showAndWait();
                EmployeeHomePage employeeHomePage = new EmployeeHomePage(currentUser);
                stage.setScene(employeeHomePage.showView(stage, AccessLevel.Manager));
            }else{
                Alert fail = new Alert(Alert.AlertType.ERROR);
                fail.setHeaderText("Category was not successfully added!");
                fail.showAndWait();
                EmployeeHomePage employeeHomePage = new EmployeeHomePage(currentUser);
                stage.setScene(employeeHomePage.showView(stage,AccessLevel.Librarian));
            }
        });
        Button back = new Button("Back");
        back.setOnAction(e -> {
            EmployeeHomePage employeeHomePage = new EmployeeHomePage(currentUser);
            stage.setScene(employeeHomePage.showView(stage,AccessLevel.Manager));
        });
        HBox b2 = new HBox();
        b2.setSpacing(10);
        b2.getChildren().addAll(registerButton,back);
        gp.add(b2,1,8);
        bp.setCenter(gp);
        return new Scene(bp,700,500);

    }
}
