package Views.Statistics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IncomesAdminView {
    public Scene showAdministratorIncomePage(Stage stage) {

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 20, 20, 20));

        Text text = new Text("Income of Sold Books");
        StackPane stack = new StackPane();
        text.setFont(new Font(30));
        stack.getChildren().add(text);
        stack.setPadding(new Insets(20));
        border.setTop(stack);

        TextField totalBooksDay = new TextField();
        Text textTotalBooksDay = new Text("Total Books Today");
        TextField totalIncomeDay = new TextField();
        Text textIncomeDay = new Text("Total Income Today");

        TextField totalBooksMonth = new TextField();
        Text textTotalBooksMonth = new Text("Total Books in a Month");
        TextField totalIncomeMonth = new TextField();
        Text textIncomeMonth = new Text("Total Income in a Month");

        TextField totalBooksYearly = new TextField();
        Text textTotalBooksYearly = new Text("Total Books in a Year");
        TextField totalIncomeYearly = new TextField();
        Text textIncomeYearly = new Text("Total Income in a Year");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(textTotalBooksDay, 0, 0);
        grid.add(totalBooksDay, 1, 0);
        grid.add(textIncomeDay, 0, 1);
        grid.add(totalIncomeDay,1,1);
        grid.add(textTotalBooksMonth, 2, 0);
        grid.add(totalBooksMonth,3,0);
        grid.add(textIncomeMonth, 2, 1);
        grid.add(totalIncomeMonth, 3, 1);
        grid.add(textTotalBooksYearly, 4, 0);
        grid.add(totalBooksYearly, 5, 0);
        grid.add(textIncomeYearly, 4, 1);
        grid.add(totalIncomeYearly, 5, 1);

        border.setCenter(grid);

        totalBooksDay.setEditable(false);
        totalIncomeDay.setEditable(false);
        totalBooksMonth.setEditable(false);
        totalIncomeMonth.setEditable(false);
        totalBooksYearly.setEditable(false);
        totalIncomeYearly.setEditable(false);

        totalBooksDay.setText( Integer.toString( 0 ) );
        totalIncomeDay.setText( Double.toString( 0.0));
        totalBooksMonth.setText( Integer.toString(0)  );
        totalIncomeMonth.setText( Double.toString(0 ));
        totalBooksYearly.setText( Integer.toString( 0 ));
        totalIncomeYearly.setText( Double.toString( 0 ));

        StackPane stackBackButton = new StackPane();
        Button backButton = new Button("Back");
        stackBackButton.getChildren().add(backButton);
        backButton.setOnAction(event -> {
           StatisticMainView statisticMainView = new StatisticMainView();
           stage.setScene(statisticMainView.showStatisticsView(stage));
            });

        stackBackButton.setPadding(new Insets(0, 0, 40, 0));
        border.setBottom(stackBackButton);

        return new Scene(border,700,500);

    }
}
