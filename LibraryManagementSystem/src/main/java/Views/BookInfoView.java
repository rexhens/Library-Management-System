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
import javafx.stage.Stage;

public class BookInfoView {
    private User currentUser;

    public BookInfoView(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scene showView(Stage stage) {
        GridPane pane = new GridPane();
        GridPane pane1 = new GridPane();
        GridPane pane2 = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);
        pane1.setAlignment(Pos.CENTER);
        pane2.setHgap(10);
        pane2.setVgap(10);
        pane2.setPadding(new Insets(10, 10, 10, 10));
        pane2.setAlignment(Pos.CENTER);

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

        ArrayList<ImageView> bookCatalog = new ArrayList<>();
        BookController bc = new BookController();
        for (int i = 0; i < FileController.books.size(); i++) {
            ImageView display = new ImageView(new Image(FileController.books.get(i).getCover()));
            display.setFitWidth(100);
            display.setFitHeight(120);
            bookCatalog.add(display);
        }

        int row = 0;
        HBox currentHBox = new HBox(15);
        ArrayList<HBox> hbox = new ArrayList<>();
        for (int i = 0; i < bookCatalog.size(); i++) {
            currentHBox.getChildren().add(bookCatalog.get(i));
            if ((i + 1) % 6 == 0 || i == bookCatalog.size() - 1) {
                hbox.add(currentHBox);
                pane.add(currentHBox,0,row++);
                currentHBox = new HBox(15);
            }
        }

        searchF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (bc.verifyISBN(newValue)) {
                searchF.setStyle("-fx-text-fill: black;");
                searchMsg.setText(null);
            } else if (newValue.isEmpty()) {
                pane.getChildren().clear();
                for (int i = 0; i < hbox.size(); i++) {
                    pane.add(hbox.get(i), 0, i);
                }
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
                        Label title = new Label("Title: " + foundBook.getBookTitle());
                        Label author = new Label("Author: " + foundBook.getAuthor().getName() + " "
                                + foundBook.getAuthor().getSurname());
                        String category = "Categories: ";
                        for (Category c : foundBook.getBookCategories()) {
                            category += c.getCategoryName() + "\n";
                        }
                        Label categories = new Label(category);
                        Label supplier = new Label("Supplier: " + foundBook.getSupplier());
                        Label purchasedDate = new Label("Last purchased: " + foundBook.getPurchasedDate());
                        Label purchasedPrice = new Label(
                                "Purchased price: " + Integer.toString(foundBook.getPurchasedPrice()) + " ALL");
                        Label originalPrice = new Label(
                                "Purchased price: " + Integer.toString(foundBook.getOriginalPrice()) + " ALL");
                        Label sellingPrice = new Label(
                                "Selling price: " + Integer.toString(foundBook.getSellingPrice()) + " ALL");
                        Label stock = new Label(
                                "Stock: " + Integer.toString(foundBook.getStock()) + " copies left in store");
                        ImageView cover = new ImageView(new Image(foundBook.getCover()));
                        cover.setFitWidth(300);
                        cover.setFitHeight(290);
                        cover.setPreserveRatio(true);

                        pane.add(cover, 1, 3);
                        pane.add(title, 1, 4);
                        pane.add(author, 1, 5);
                        pane.add(categories, 1, 6);
                        pane.add(supplier, 1, 7);
                        pane.add(purchasedDate, 1, 8);
                        pane.add(purchasedPrice, 1, 9);
                        pane.add(originalPrice, 1, 10);
                        pane.add(sellingPrice, 1, 11);
                        pane.add(stock, 1, 12);

                    } else {
                        Alert error = new Alert(AlertType.INFORMATION);
                        error.setHeaderText("This book doesn't exists in the database!");
                        error.showAndWait();
                    }
                }
            }
        });

        pane2.add(pane1, 0, 0);
        pane2.add(pane, 0, 1);

        ScrollPane sp = new ScrollPane(pane2);
        Scene sc = new Scene(sp, 600, 550);
        return sc;
    }
}
