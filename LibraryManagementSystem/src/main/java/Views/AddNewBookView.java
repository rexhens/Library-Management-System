package Views;

import java.util.ArrayList;

import Controllers.AuthorController;
import Controllers.FileController;
import Models.Author;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewBookView {
    private User currentUser;

    public AddNewBookView(User currentUser) {
        this.currentUser = currentUser;
    }
    public Scene showView(Stage stage) {
        GridPane pane = new GridPane();
        
        Button back = new Button("Homepage");
        pane.add(back, 2, 0);
        back.setOnAction(e -> {
            EmployeeHomePage hp = new EmployeeHomePage(currentUser);
            stage.setScene(hp.showView(stage));
        });

    // pane.getSubmitBtn().setOnAction(this::onBookSubmit);
    // pane.getHomepageBtn().setOnAction(e->{});
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    // private void onBookSubmit(ActionEvent actionEvent) {
    // String isbn13 = pane.getIsbnTF().getText();
    // String title = pane.getTitleTF().getText();
    // float price = Float.parseFloat(pane.getPriceTF().getText());
    // Author author = pane.getAuthorComboBox().getValue();
    // Book newBook = new Book(isbn13, title, description, price, author,
    // isPaperback);
    // for(int i=0; i < pane.getGenreCheckboxes().size(); i++) {
    // if(pane.getGenreCheckboxes().get(i).isSelected())
    // newBook.addGenre(Genre.values()[i]);
    // }
    // }

    // private final TextField titleF = new TextField();
    // private final Label titleL = new Label("Title");
    // private final TextField ISBNF = new TextField();
    // private final Label ISBNL = new Label("ISBN 13");
    // private final TextField purchasedPriceF = new TextField();
    // private final Label purchasedPriceL = new Label("Purchased Price");
    // private final TextField originalPriceF = new TextField();
    // private final Label originalPriceL = new Label("Original Price");
    // private final TextField sellingPriceF = new TextField();
    // private final Label sellingPriceL = new Label("Selling Price");
    // private final Label authorL = new Label("Select an author: ");
    // private final ComboBox<Author> authorComboBox = new ComboBox<>();
    // private final ArrayList<CheckBox> CategoryCheckboxes = new ArrayList<>();
    // private final Label CategoryL = new Label("Categories: ");
    // private final Label

    // private final Button submitBtn = new Button("Submit");
    // private final Button back = new Button("Homepage");

    // public BookFormPane() {
    // setUpView();
    // }

    // private void setUpView() {
    // this.setAlignment(Pos.CENTER);
    // this.setPadding(new Insets(0, 10, 0, 10));
    // this.setVgap(10);
    // this.setHgap(10);

    // ToggleGroup group = new ToggleGroup();
    // rbPaperback.setToggleGroup(group);
    // rbEbook.setToggleGroup(group);
    // HBox hbox = new HBox(10);
    // hbox.getChildren().addAll(rbPaperback, rbEbook);

    // descriptionTA.setPrefColumnCount(20);
    // descriptionTA.setPrefRowCount(5);
    // descriptionTA.setWrapText(true);

    // ArrayList<Author> authors = FileHandler.getAvailableAuthors();
    // authorComboBox.getItems().addAll(authors);

    // // Categorys
    // VBox paneForCategorys = new VBox(10);
    // paneForCategories.setPadding(new Insets(4));
    // for(Category g : Category.values()) {
    // CategoryCheckboxes.add(new CheckBox(g.toString()));
    // }
    // paneForCategories.getChildren().addAll(CategoryCheckboxes);

    // this.add(titleLbl, 0, 0);
    // this.add(titleTF, 1, 0);
    // this.add(isbnLbl, 0, 1);
    // this.add(isbnTF, 1, 1);
    // this.add(priceLbl, 0, 2);
    // this.add(priceTF, 1, 2);
    // this.add(versionLbl, 0, 3);
    // this.add(hbox, 1, 3);
    // this.add(descriptionLbl, 0, 4);
    // this.add(descriptionTA, 1, 4);
    // this.add(authorsLbl, 0, 5);
    // this.add(authorComboBox, 1, 5);
    // this.add(CategoryLbl, 0, 6);
    // this.add(paneForCategorys, 1, 6);
    // this.add(submitBtn, 1, 7);
    // }
}
