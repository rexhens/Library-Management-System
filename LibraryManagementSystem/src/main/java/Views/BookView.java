package Views;

import java.util.ArrayList;

import Controllers.BookController;
import Controllers.FileController;
import Models.Book;
import Models.Category;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookView {
    private User currentUser;

    public BookView(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scene showView(Stage stage) {
        VBox pane = new VBox(10);
        GridPane pane1 = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);
        pane1.setAlignment(Pos.CENTER);
        pane1.setHgap(10);

        Label searchL = new Label("Search with ISBN: ");
        TextField searchF = new TextField();
        Label searchMsg = new Label();
        searchMsg.setStyle("-fx-text-fill: red;");
        pane1.add(searchL, 0, 0);
        pane1.add(searchF, 1, 0);
        pane1.add(searchMsg, 1, 1);

        Button back = new Button("Homepage");
        pane1.add(back, 2, 0);
        back.setOnAction(e -> {
            EmployeeHomePage hp = new EmployeeHomePage(currentUser);
            stage.setScene(hp.showView(stage));
        });

        BookController bc = new BookController();
        BookCatalogPane bcp = new BookCatalogPane();
        BookDetailsPane bdp = new BookDetailsPane();
        pane.getChildren().addAll(pane1, bcp.showPane());

        searchF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (bc.verifyISBN(newValue)) {
                searchF.setStyle("-fx-text-fill: black;");
                searchMsg.setText(null);
            } else if (newValue.isEmpty()) {
                pane.getChildren().clear();
                pane.getChildren().addAll(pane1,bcp.showPane());
            } else {
                searchF.setStyle("-fx-text-fill: red;");
                searchMsg.setText("Invalid ISBN13 format!");
            }
        });

        searchF.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (searchMsg.getText() == null) {
                    Book foundBook = bc.findBook(searchF.getText());
                    if (foundBook != null) {
                        pane.getChildren().clear();
                        pane.getChildren().addAll(pane1,bdp.showPane(foundBook));

                    } else {
                        Alert error = new Alert(AlertType.INFORMATION);
                        error.setHeaderText("This book doesn't exists in the database!");
                        error.showAndWait();
                    }
                }
            }
        });

        ScrollPane sp = new ScrollPane(pane);
        Scene sc = new Scene(sp, 600, 550);
        return sc;
    }
}
