package Views;

import Models.Librarian;
import Models.Manager;
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

public class LibrarianPerformanceView {
    public Scene showLibrarianPerformanceView(Stage stage, Librarian librarian){

        BorderPane borderPane = new BorderPane();

        StackPane stackText = new StackPane();
        Text text = new Text(librarian.getName() + "'s Performance");
        text.setFont(new Font(30));
        stackText.getChildren().add(text);
        stackText.setPadding(new Insets(20));
        borderPane.setTop(stackText);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setAlignment(Pos.CENTER);

        Label nrBillsLabel = new Label("Total number of Bills");
        TextField noBillsField = new TextField();
        noBillsField.setEditable(false);
        gridPane.add(nrBillsLabel,0,0);
        gridPane.add(noBillsField,1,0);

        Label booksSoldLabel = new Label("Books sold");
        TextField booksSoldField = new TextField();
        booksSoldField.setEditable(false);
        gridPane.add(booksSoldLabel,0,1);
        gridPane.add(booksSoldField,1,1);

        Label moneyMadeTodayLabel = new Label("Money made today");
        TextField moneyMadeTodayField = new TextField();
        moneyMadeTodayField.setEditable(false);
        gridPane.add(moneyMadeTodayLabel,0,2);
        gridPane.add(moneyMadeTodayField,1,2);

        Label moneyMadeThisMonthLabel = new Label("Money made this month");
        TextField moneyMadeThisMonthField = new TextField();
        moneyMadeThisMonthField.setEditable(false);
        gridPane.add(moneyMadeThisMonthLabel,0,3);
        gridPane.add(moneyMadeThisMonthField,1,3);

        Label moneyMadeYearLabel = new Label("Money made this year");
        TextField moneyMadeYEarField = new TextField();
        moneyMadeYEarField.setEditable(false);
        gridPane.add(moneyMadeYearLabel,0,4);
        gridPane.add(moneyMadeYEarField,1,4);

        Button backButton = new Button("Back");
        backButton.setOnAction(e->
        {
            ManageLibrarianView manageLibrarianView = new ManageLibrarianView();
            stage.setScene(manageLibrarianView.showManageLibrarianView(librarian,stage));
        });
        gridPane.add(backButton,2,5);

        borderPane.setCenter(gridPane);

        return new Scene(borderPane,700,500);
    }
}
