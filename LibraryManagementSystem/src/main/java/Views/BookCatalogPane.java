package Views;

import java.util.ArrayList;

import Controllers.BookController;
import Controllers.FileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BookCatalogPane {
    public GridPane showPane() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);

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
        for (int i = 0; i < bookCatalog.size(); i++) {
            currentHBox.getChildren().add(bookCatalog.get(i));
            if ((i + 1) % 5 == 0 || i == bookCatalog.size() - 1) {
                pane.add(currentHBox,0,row++);
                currentHBox = new HBox(15);
            }
        }
        return pane;
    }
}
