package Views;
import Models.Roles;
import Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EmployeeHomePage {
    private User currentUser;

    public EmployeeHomePage(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scene showView(Stage stage) {
        BorderPane bp = new BorderPane();
        Text text = new Text("Homepage");
        StackPane pane1 = new StackPane();
        text.setFont(new Font(30));
        pane1.getChildren().add(text);
        pane1.setPadding(new Insets(20));
        bp.setTop(pane1);
        GridPane p = new GridPane();
        p.setHgap(10);
        p.setVgap(10);
        p.setPadding(new Insets(10, 10, 10, 10));
        p.setAlignment(Pos.CENTER);
        ArrayList<Button> EmployeeBt = new ArrayList<>();

        Button bookInfo = new Button("Book Information");
        Button authorInfo = new Button("All Authors");
        Button categoryInfo = new Button("All Categories");

        EmployeeBt.add(bookInfo);
        EmployeeBt.add(authorInfo);
        EmployeeBt.add(categoryInfo);


        if(currentUser.getAccessLevel()==1||currentUser.getAccessLevel()==3) {
            Button billing = new Button("Print Bill");
            EmployeeBt.add(billing);
        }

        if(currentUser.getAccessLevel()==2||currentUser.getAccessLevel()==3){
            Button addNewBook = new Button ("Add New Book");
            Button modifyBook = new Button("Modify Book data");
            Button totalBookSold = new Button("Sold Book Copies");
            Button addStock = new Button("Add Stock");
            Button addAuthor = new Button("Add New Author");
            /*addAuthor.setOnAction(e->{
                AuthorFormView afv = new AuthorFormView(currentUser);
                stage.setScene(afv.showView(stage));
            });*/

            Button addCategory = new Button("Add New Category");

            EmployeeBt.add(addNewBook);
            EmployeeBt.add(modifyBook);
            EmployeeBt.add(totalBookSold);
            EmployeeBt.add(addStock);
            EmployeeBt.add(addAuthor);
            EmployeeBt.add(addCategory);
        }
        if(currentUser.getUserRole()== Roles.Manager){
            //search librarian - only role manager
            //check statistics for specific librarian (tot no bills/books sold/tot money made for a date or period) - only role manager
        }

        Button back = new Button("Log out");
        EmployeeBt.add(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert error = new Alert(AlertType.INFORMATION);
                error.setHeaderText("You are logging out!");
                error.showAndWait();
                LogInView li=new LogInView();
                stage.setTitle("Log in");
                stage.setScene(li.showLogInScene(stage));
            }});
        int row=1;
        HBox currentHbox=new HBox(10);
        for(int i=0;i<EmployeeBt.size();i++){
            currentHbox.getChildren().add(EmployeeBt.get(i));
            if((i+1)%5==0 || i==EmployeeBt.size()-1){
                p.add(currentHbox,0,row++);
                currentHbox=new HBox(10);
            }
        }
        bp.setCenter(p);
        Scene sc=new Scene(bp,600,550);
        return sc;
    }
}
