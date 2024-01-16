package Views;

import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Changepassview {
    private User currentUser;

    public Changepassview(User currentUser) {
        this.currentUser = currentUser;
    }
    public Scene showview(Stage stage){
        BorderPane bp = new BorderPane();
        Text text = new Text("Change Password");
        StackPane pane1 = new StackPane();
        text.setFont(new Font(30));
        pane1.getChildren().add(text);
        pane1.setPadding(new Insets(20));
        bp.setTop(pane1);

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setAlignment(Pos.CENTER);

        Label pass=new Label("Enter the new password here");
        TextField pass1=new TextField();
        Button confirmbtn=new Button("Confirm")

        return new Scene(gp, 700, 500);
    }
}
