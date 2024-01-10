package Views;

import Controllers.BookController;
import Models.Book;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrintBillView {
    private User currentUser;

    public PrintBillView(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scene showView(Stage stage) {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));

        Label totalPrice = new Label("Total Price: ");
        TextField totalPriceF = new TextField();
        totalPriceF.setEditable(false);

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        pane.setCenter(gp);

        Label isbnL = new Label("Book ISBN13");
        Label copies = new Label("Number of copies");
        Label price = new Label("Price");

        VBox vbISBN = new VBox(10);
        VBox vbCopies = new VBox(10);
        VBox vbPrice = new VBox(10);

        Text text = new Text("Print Bill");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));
        pane.setTop(stack);
        Button back = new Button("Cancel");
        back.setOnAction(e -> {
            EmployeeHomePage hp = new EmployeeHomePage(currentUser);
            stage.setScene(hp.showView(stage));
        });
        Button print = new Button("Finish Transaction");
        print.setOnAction(e -> {
            // validation of input
            // if any of input not valid make fields red if tries to finish transaction give
            // popup
            // if input valid add all field contents in an object
            // print objcet in txt
            // add object in array list
            Alert info = new Alert(AlertType.INFORMATION);
            info.setHeaderText("Bill Printed!");
            info.showAndWait();
        });
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(print, back);
        pane.setBottom(hb);

        ScrollPane sp = new ScrollPane(pane);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        Scene sc = new Scene(sp, 700, 500);
        sp.prefWidthProperty().bind(sc.widthProperty());
        sp.prefHeightProperty().bind(sc.heightProperty());
        return sc;
    }
}
