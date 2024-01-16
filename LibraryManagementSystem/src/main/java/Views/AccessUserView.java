package Views;

import Models.Librarian;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AccessUserView {
    public Scene showAccessUserView(User user)
    {
        BorderPane borderPane = new BorderPane();

        StackPane stackText = new StackPane();
        Text text = new Text();
        if(user instanceof Librarian) {
             text.setText("Librarian Access");
        }else{
            text.setText("Manager Access");
        }

        text.setFont(new Font(30));
        stackText.getChildren().add(text);
        stackText.setPadding(new Insets(20));
        borderPane.setTop(stackText);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        ToggleGroup tgA = new ToggleGroup();
        Label accessL = new Label("Access Level");
        RadioButton level1 = new RadioButton("Sell books");
        RadioButton level2 = new RadioButton("Add Stock/Authors/Categories");
        RadioButton level3 = new RadioButton("Both");
        level1.setToggleGroup(tgA);
        level2.setToggleGroup(tgA);
        level3.setToggleGroup(tgA);

        gridPane.add(accessL, 0, 0);
        HBox b2 = new HBox(10);
        b2.getChildren().addAll(level1, level2, level3);
        gridPane.add(b2, 1, 0);


        borderPane.setCenter(gridPane);
        return new Scene(borderPane,700,500);
    }
}
