package Views;

import Controllers.BookController;
import Models.Book;
import Models.InvalidIsbnFormatException;
import Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddStockView {
    private static User currentUser;
    private static Stage stage;

    public AddStockView(User u, Stage s) {
        currentUser = u;
        stage= s;
    }

    public Scene showView() {
        BorderPane pane = new BorderPane();
        GridPane gPane = new GridPane();
        VBox vb = new VBox(10);
        vb.setAlignment(Pos.CENTER_LEFT);
        HBox hb = new HBox(20);
        hb.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        gPane.setAlignment(Pos.CENTER);
        gPane.setPadding(new Insets(10, 10, 10, 10));
        gPane.setHgap(10);
        gPane.setVgap(10);

        pane.setTop(gPane);
        pane.setCenter(hb);

        Label searchL = new Label("Search with ISBN: ");
        TextField searchF = new TextField();
        Label searchMsg = new Label();
        searchMsg.setStyle("-fx-text-fill: red;");

        Text text = new Text("Find Book For Stock");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));

        gPane.add(text, 1 , 0);
        gPane.add(searchL, 0, 1);
        gPane.add(searchF, 1, 1);
        gPane.add(searchMsg, 1, 2);

        class MyButtonClickHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                EmployeeHomePage hp = new EmployeeHomePage(currentUser);
                stage.setScene(hp.showView(stage));
            }
        }
        MyButtonClickHandler myBtn = new MyButtonClickHandler();

        Button back = new Button("Homepage");
        gPane.add(back, 2, 1);
        back.setOnAction(myBtn::handle);

        BookController bc = new BookController();

        searchF.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                bc.verifyISBN(newValue);
                searchF.setStyle("-fx-text-fill: black;");
                searchMsg.setText(null);
            }catch(InvalidIsbnFormatException e){
                searchF.setStyle("-fx-text-fill: red;");
                searchMsg.setText(e.getMessage());
            }
        });

        searchF.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (searchMsg.getText() == null) {
                    Book foundBook = bc.findBook(searchF.getText());
                    if (foundBook != null) {
                        ImageView cover = new ImageView(new Image(foundBook.getCover()));
                        Label book = new Label("Book: "+foundBook.getBookTitle());
                        Label author = new Label("Author: "+foundBook.getAuthor().getName()+" "+foundBook.getAuthor().getSurname());
                        Label price = new Label("Purchase price: "+ foundBook.getPurchasedPrice());
                        Label date = new Label();
                        if(foundBook.getPurchasedDate()==null){
                            date.setText("Last purchased date: Not purchased yet");
                        }
                        else{
                            date.setText("Last purchased date: "+foundBook.getPurchasedDate().toString());
                        }
                        Label stock = new Label("Current stock: "+ foundBook.getStock());
                        
                        Button add= new Button("Add Stock");
                        add.setOnAction(null);
                
                        vb.getChildren().addAll(book,author,price,date,stock);
                        hb.getChildren().addAll(cover,vb);


                    } else {
                        Alert error = new Alert(AlertType.INFORMATION);
                        error.setHeaderText("This book doesn't exists in the database!");
                        error.showAndWait();
                    }
                }
            }
        });

        ScrollPane sp = new ScrollPane(pane);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        Scene sc = new Scene(sp, 700, 500);
        sp.prefWidthProperty().bind(sc.widthProperty());
        sp.prefHeightProperty().bind(sc.heightProperty());
        return sc;
    }
    
    public int totalPriceCalculation(Book b, int quantity){
        return b.getSellingPrice()*quantity;
    }
}
