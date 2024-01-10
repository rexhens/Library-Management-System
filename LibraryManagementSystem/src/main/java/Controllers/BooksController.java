package Controllers;

import Models.Book;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

public class BooksController {
    public  ArrayList<Book> getBooksBoughtThisMonth()
    {
        var books = FileController.books;
        var booksBoughtToday = new ArrayList<Book>();
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());

        for(Book book : books)
        {
            if(book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant()))
            {
                booksBoughtToday.add(book);
                System.out.println(book);
            }
        }
        return booksBoughtToday;
    }
    public ArrayList<Book> getBooksBoughtThisYear()
    {
        var books = FileController.books;
        var booksBoughtToday = new ArrayList<Book>();
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());

        for(Book book : books)
        {
            if(book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant()))
            {
                booksBoughtToday.add(book);
                System.out.println(book);
            }
        }
        return booksBoughtToday;
    }

    public ArrayList<Book> getBooksBoughtToday()
    { var books = FileController.books;
        var booksBoughtToday = new ArrayList<Book>();

        for(Book book : books)
        {
            if(isSameDay(book.getPurchasedDate(),new Date()))
            {
                booksBoughtToday.add(book);
                System.out.println(book);
            }
        }
        return booksBoughtToday;
    }
    private static boolean isSameDay(Date date1, Date date2) {
        // Convert both dates to LocalDate
        java.time.LocalDate localDate1 = date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        java.time.LocalDate localDate2 = date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // Compare the dates
        return localDate1.isEqual(localDate2);
    }


}

