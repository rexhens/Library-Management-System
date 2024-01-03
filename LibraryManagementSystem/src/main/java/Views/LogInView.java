package Views;

import Controllers.LogInController;
import Models.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogInView {
    public Scene showLogInScene(Stage stage)
    {
        BorderPane border = new BorderPane();
        border.setMinSize(700,500);

        StackPane stackText = new StackPane();
        Text text = new Text("Log In");
        text.setFont(new Font(30));
        stackText.getChildren().add(text);
        stackText.setPadding(new Insets(20));
        border.setTop(stackText);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Username");
        TextField usernameTxtField = new TextField();
        gridPane.add(usernameLabel,0,0);
        gridPane.add(usernameTxtField,1,0);

        Label passwordLabel = new Label("Password");
        TextField passwordField = new PasswordField();
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);

        Label systemLabel = new Label("System");
        TextField systemField = new TextField();
        systemField.setEditable(false);
        gridPane.add(systemLabel,0,2);
        gridPane.add(systemField,1,2);

        Button submitButton = new Button("Log In");
    //  GridPane.setColumnSpan(submitButton, 2);
        gridPane.add(submitButton, 0, 3);
        String errMsg = "";

        submitButton.setOnAction(e -> {
            LogInController controller = new LogInController();
            StandardViewResponse<User> user = controller.OnLogInBtnClick(usernameTxtField.getText(), passwordField.getText());
            if (user.getUser() == null) {
                if(user.getErrorMessage().equals("Wrong Password!"))
                {
                    systemField.setText("Wrong Password!");
                    passwordField.clear();
                }else if(user.getErrorMessage().equals("Username cannot be null!"))
                {
                    systemField.setText("Username can't be null");
                    passwordField.clear();
                }else if(user.getErrorMessage().equals("Password cannot be null!"))
                {
                    systemField.setText("Password can't be null");
                    usernameTxtField.clear();
                }
                else {
                    systemField.setText("Wrong Credentials!");
                    usernameTxtField.clear();
                    passwordField.clear();
                }
            }
            else {
                if (user.getUser() instanceof Admin) {
                    AdminHomePage adminHomePage = new AdminHomePage();
                    stage.setScene(adminHomePage.showAdminHomePage(stage));
                }
                else if (user.getUser() instanceof Librarian) {

                }
            }
        });
        passwordField.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER) {
                LogInController controller = new LogInController();
                StandardViewResponse<User> user = controller.OnLogInBtnClick(usernameTxtField.getText(), passwordField.getText());
                if (user.getUser() == null) {
                    if(user.getErrorMessage().equals("Wrong Password!"))
                    {
                        systemField.setText("Wrong Password!");
                        passwordField.clear();
                    }else if(user.getErrorMessage().equals("Username cannot be null!"))
                    {
                        systemField.setText("Username can't be null");
                        passwordField.clear();
                    }else if(user.getErrorMessage().equals("Password cannot be null!"))
                    {
                        systemField.setText("Password can't be null");
                        usernameTxtField.clear();
                    }
                    else {
                        systemField.setText("Wrong Credentials!");
                        usernameTxtField.clear();
                        passwordField.clear();
                    }
                } else {
                    if(user.getUser() instanceof Admin)
                    {
                        AdminHomePage adminHomePage = new AdminHomePage();
                        stage.setScene(adminHomePage.showAdminHomePage(stage));
                    }
                    else if(user.getUser() instanceof Manager)
                    {

                    }
                    else if(user.getUser() instanceof Librarian)
                    {

                    }
                    // Perform actions for a successful login
                    // For example, show a new scene or perform other operations.
                }
            }
        });
    border.setCenter(gridPane);

        return new Scene(border,700,500);
    }

}
