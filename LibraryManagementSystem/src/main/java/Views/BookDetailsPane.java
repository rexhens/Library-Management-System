package Views;

import Models.Book;
import Models.Category;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BookDetailsPane {
    public VBox showPane(Book foundBook) {
        VBox p = new VBox();
        p.setAlignment(Pos.CENTER);
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);

        Label title = new Label("Title: " + foundBook.getBookTitle());
        Label author = new Label("Author: " + foundBook.getAuthor().getName() + " "
                + foundBook.getAuthor().getSurname());
        Label isbn = new Label("ISBN13: "+foundBook.getISBN());
        String category = "Categories: ";
        for (Category c : foundBook.getBookCategories()) {
            category += c.getCategoryName() + "\n";
        }
        Label categories = new Label(category);
        Label supplier = new Label("Supplier: " + foundBook.getSupplier());
        Label purchasedDate = new Label("Last purchased: " +
                foundBook.getPurchasedDate());
        Label purchasedPrice = new Label(
                "Purchased price: " + Integer.toString(foundBook.getPurchasedPrice()) + " ALL");
        Label originalPrice = new Label(
                "Purchased price: " + Integer.toString(foundBook.getOriginalPrice()) + " ALL");
        Label sellingPrice = new Label("Selling price: " + Integer.toString(foundBook.getSellingPrice()) + " ALL");
        Label stock = new Label(
                "Stock: " + Integer.toString(foundBook.getStock()) + " copies left in store");
        ImageView cover = new ImageView(new Image(foundBook.getCover()));
        cover.setFitWidth(300);
        cover.setFitHeight(290);
        cover.setPreserveRatio(true);
        p.getChildren().add(pane);

        pane.add(cover, 1, 0);
        pane.add(title, 1, 1);
        pane.add(author, 1, 2);
        pane.add(isbn,1,3);
        pane.add(categories, 1, 4);
        pane.add(supplier, 1, 5);
        pane.add(purchasedDate, 1, 6);
        pane.add(purchasedPrice, 1, 7);
        pane.add(originalPrice, 1, 8);
        pane.add(sellingPrice, 1, 9);
        pane.add(stock, 1, 10);

        Button showCatalog = new Button("Back");
        pane.add(showCatalog,1,11);
        showCatalog.setOnAction(e->{
                p.getChildren().clear();
                BookCatalogPane bcp = new BookCatalogPane();
                p.getChildren().add(bcp.showPane());
        });

        return p;
    }

}
