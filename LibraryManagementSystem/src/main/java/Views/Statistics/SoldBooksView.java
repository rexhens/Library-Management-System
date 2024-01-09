package Views.Statistics;

import Controllers.FileController;
import Models.Book;
import Views.AdminHomePage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SoldBooksView {
    public Scene showSoldBooksView(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Text text = new Text("Sold books");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));
        borderPane.setTop(stack);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        Label boughtTodayLabel = new Label("Books sold Today");
        gridPane.add(boughtTodayLabel,0,0);
        ArrayList<Book> booksBoughtToday = FileController.books;
        int j = 0;
        for(int i = 0; i < booksBoughtToday.size();i++)
        {
            gridPane.add(new Label(booksBoughtToday.get(i).getBookTitle()),0,++j);
        }


        Label boughtThoisMonthLabel = new Label("Books sold This Month");
        gridPane.add(boughtThoisMonthLabel,1,0);
        ArrayList<Book> booksBoughtThisMonth = FileController.books;
        j =0;
        for(int i = 0; i < booksBoughtThisMonth.size();i++)
        {
            gridPane.add(new Label(booksBoughtToday.get(i).getBookTitle()),1,++j);
        }


        Label boughtThisYearLabel = new Label("Books sold This Year");
        gridPane.add(boughtThisYearLabel,2,0);
        ArrayList<Book> booksBoughtThisYear = FileController.books;
        j=0;
        for(int i = 0; i < booksBoughtThisYear.size();i++)
        {
            gridPane.add(new Label(booksBoughtThisYear.get(i).getBookTitle()),2,++j);
        }

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            StatisticMainView adminHomePage = new StatisticMainView();
            stage.setScene(adminHomePage.showStatisticsView(stage));
        });
        gridPane.add(backBtn,5,booksBoughtThisYear.size()+3);


        borderPane.setCenter(gridPane);
        return new Scene(borderPane, 700, 500);
    }
}
