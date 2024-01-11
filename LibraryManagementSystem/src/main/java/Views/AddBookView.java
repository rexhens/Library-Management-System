package Views;

import Controllers.AuthorController;
import Controllers.BookController;
import Controllers.FileController;
import Models.Author;
import Models.Category;
import Models.Gender;
import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AddBookView {
    private User currentUser;
    public AddBookView(User currentUser) {
        this.currentUser = currentUser;
    }
    public Scene addBook(Stage stage){
        BorderPane bp = new BorderPane();
        Text title = new Text("Add Book");
        StackPane p1 = new StackPane();
        title.setFont(new Font(30));
        p1.getChildren().add(title);
        p1.setPadding(new Insets(20));
        bp.setTop(p1);

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        gp.setAlignment(Pos.CENTER);

        Label isbn = new Label("ISBN");
        TextField isbn1 = new TextField();
        gp.add(isbn,0,0);
        gp.add(isbn1,1,0);

        Label book = new Label("Book Title");
        TextField booktitle = new TextField();
        gp.add(book,0,1);
        gp.add(booktitle,1,1);


        Label authorL= new Label("Select an Author");
        ArrayList<Author> authors = FileController.authors;
        ComboBox<Author> authorComboBox = new ComboBox<>();
        authorComboBox.getItems().addAll(authors);
        gp.add(authorL,0,2);
        gp.add(authorComboBox,1,2);

        Label categoryL= new Label("Select the category");
        gp.add(categoryL, 0, 3);
        ArrayList<Category> categories = FileController.categories;
        ArrayList<CheckBox> categoryCheckboxes = new ArrayList<>();
        for(Category g : categories) {
            categoryCheckboxes.add(new CheckBox(g.toString()));
        }
        VBox pane = new VBox(10);
        pane.setPadding(new Insets(4));
        pane.getChildren().addAll(categoryCheckboxes);
        gp.add(pane,1,3);

        Label su = new Label("Suplier");
        TextField sut = new TextField();
        gp.add(su,0,4);
        gp.add(sut,1,4);

        Label p = new Label("Purchased Price");
        TextField pt = new TextField();
        gp.add(p,0,5);
        gp.add(pt,1,5);


        Label o = new Label("Original Price");
        TextField ot = new TextField();
        gp.add(o,0,6);
        gp.add(ot,1,6);

        Label s = new Label("Selling Price");
        TextField st = new TextField();
        gp.add(s,0,7);
        gp.add(st,1,7);



        /*Label pi = new Label("Cover");
        TextField pit = new TextField();
        gp.add(s,0,9);
        gp.add(st,1,9);*/



        ArrayList<Category> selected = new ArrayList<>();
        Button registerButton = new Button("Register Book");
        registerButton.setOnAction(e -> {
            BookController controller = new BookController();
            for (CheckBox checkBox : categoryCheckboxes) {
                if (checkBox.isSelected()) {
                    Category select = (Category) checkBox.getUserData();
                    selected.add(select);
                }
            }
            int number =0;
            int number1=0;
            int number2=0;
            try {
                number = Integer.parseInt(pt.getText());
            } catch (NumberFormatException i) {
                System.out.println("Invalid input. Please enter an integer.");
            }
            try {
                number1 = Integer.parseInt(ot.getText());
            } catch (NumberFormatException i) {
                System.out.println("Invalid input. Please enter an integer.");
            }
            try {
                number2 = Integer.parseInt(st.getText());
            } catch (NumberFormatException i) {
                System.out.println("Invalid input. Please enter an integer.");
            }

            var added = controller.createBook(isbn1.getText(),booktitle.getText(),authorComboBox.getValue(),selected,sut.getText(),number,number1,number2,"Testing");
            if(added != null)
            {
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setHeaderText("Book was successfully added!");
                success.showAndWait();
                EmployeeHomePage employeeHomePage = new EmployeeHomePage(currentUser);
                stage.setScene(employeeHomePage.showView(stage));
            }else{
                Alert fail = new Alert(Alert.AlertType.ERROR);
                fail.setHeaderText("Book was not successfully added!");
                fail.showAndWait();
                EmployeeHomePage employeeHomePage = new EmployeeHomePage(currentUser);
                stage.setScene(employeeHomePage.showView(stage));
            }
        });
        Button back = new Button("Back");
        back.setOnAction(e ->{
            EmployeeHomePage employeeHomePage = new EmployeeHomePage(currentUser);
            stage.setScene(employeeHomePage.showView(stage));
        });
        HBox b2 = new HBox();
        b2.setSpacing(10);
        b2.getChildren().addAll(registerButton,back);
        gp.add(b2,1,8);
        bp.setCenter(gp);
        Scene sc =new Scene(bp,700,500);
        return sc;
    }
}