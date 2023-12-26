package Views;

import Controllers.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddUserView {
    public Scene addLibrarian(Stage stage)
    {
        BorderPane borderPane = new BorderPane();

        StackPane stackText = new StackPane();
        Text text = new Text("Welcome");
        text.setFont(new Font(30));
        stackText.getChildren().add(text);
        stackText.setPadding(new Insets(20));
        borderPane.setTop(stackText);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name");
        TextField nameTxtField = new TextField();
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameTxtField,1,0);

        Label surnameLabel = new Label("Surname");
        TextField surnameTxtField = new TextField();
        gridPane.add(surnameLabel,0,1);
        gridPane.add(surnameTxtField,1,1);

        Label usernameLabel = new Label("Username");
        TextField usernameTxtField = new TextField();
        gridPane.add(usernameLabel,0,2);
        gridPane.add(usernameTxtField,1,2);

        Label passwordLabel = new Label("Password");
        TextField passwordField = new PasswordField();
        gridPane.add(passwordLabel,0,3);
        gridPane.add(passwordField,1,3);

        Label phoneNumLabel = new Label("Phone Number");
        TextField phoneNumField = new TextField();
        gridPane.add(phoneNumLabel,0,4);
        gridPane.add(phoneNumField,1,4);

        Label salaryLabel = new Label("Salary");
        TextField salaryField = new TextField();
        gridPane.add(salaryLabel,0,5);
        gridPane.add(salaryField,1,5);

        Label systemLabel = new Label("System");
        TextArea systemField = new TextArea();
        systemField.setEditable(false);
        systemField.setWrapText(true);
        gridPane.add(systemLabel,0,6);
        gridPane.add(systemField,1,6);


        Button registerButton = new Button("Register Librarian");
        registerButton.setOnAction(e -> {
            AdminController controller = new AdminController();
            var added = controller.addLibrarian(nameTxtField.getText(),surnameTxtField.getText(),usernameTxtField.getText(),
                    passwordField.getText(),salaryField.getText(),phoneNumField.getText());
            if(added.getUser() != null)
            {
                stage.setScene(new Scene(new GridPane(),100,100));
            }else{
                systemField.setText(added.getErrorMessage());
            }
        });
        gridPane.add(registerButton,0,7);
       borderPane.setCenter(gridPane);
       return new Scene(borderPane,500,300);
    }
}
