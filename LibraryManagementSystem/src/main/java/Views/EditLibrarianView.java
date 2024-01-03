package Views;

import Controllers.LibrarianController;
import Models.Librarian;
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

public class EditLibrarianView {
    public Scene editLibrarian(Stage stage, Librarian librarian)
    {
        BorderPane borderPane = new BorderPane();

        StackPane stackText = new StackPane();
        Text text = new Text("Edit Librarian");
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
        TextField nameField = new TextField(librarian.getName());
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameField,1,0);

        Label surnameLabel = new Label("Surname");
        TextField surnameTxtField = new TextField(librarian.getSurname());
        gridPane.add(surnameLabel,0,1);
        gridPane.add(surnameTxtField,1,1);

        Label usernameLabel = new Label("Username");
        TextField usernameTxtField = new TextField(librarian.getUsername());
        gridPane.add(usernameLabel,0,2);
        gridPane.add(usernameTxtField,1,2);

        Label passwordLabel = new Label("Password");
        TextField passwordField = new TextField("**********");
        passwordField.setEditable(false);
        gridPane.add(passwordLabel,0,3);
        gridPane.add(passwordField,1,3);

        Label phoneNumLabel = new Label("Phone Number");
        TextField phoneNumField = new TextField(librarian.getPhoneNum());
        gridPane.add(phoneNumLabel,0,4);
        gridPane.add(phoneNumField,1,4);

        Label salaryLabel = new Label("Salary");
        TextField salaryField = new TextField(Double.toString(librarian.getSalary()));
        gridPane.add(salaryLabel,0,5);
        gridPane.add(salaryField,1,5);

        Label systemLabel = new Label("System");
        TextArea systemArea = new TextArea();
        systemArea.setEditable(false);
        systemArea.setWrapText(true);
        gridPane.add(systemLabel,0,6);
        gridPane.add(systemArea,1,6);

        Button editButton = new Button("Edit");
        gridPane.add(editButton, 2,8) ;
        editButton.setOnAction(e ->{
            LibrarianController controller = new LibrarianController();
            var edited = controller.editLibrarian(nameField.getText(),surnameTxtField.getText(),
                    usernameTxtField.getText(),salaryField.getText(),phoneNumField.getText(),
                    librarian.getId());
            if(edited.getErrorMessage().isEmpty() || edited.getErrorMessage() == null)
            {
                AdminHomePage adminHomePage = new AdminHomePage();
                stage.setScene(adminHomePage.showAdminHomePage(stage));
            } else{
                systemArea.setText(edited.getErrorMessage());
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e->{
            AdminHomePage adminHomePage = new AdminHomePage();
            stage.setScene(adminHomePage.manageLibrariansView(stage));
        });
        gridPane.add(backBtn,3,8);

        borderPane.setCenter(gridPane);
        return new Scene(borderPane,700,500);
    }
}
