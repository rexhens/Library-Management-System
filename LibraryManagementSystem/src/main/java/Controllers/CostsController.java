package Controllers;

import Models.BillsType;
import Models.Book;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import static Controllers.BookController.isSameDay;

public class CostsController {


    public ArrayList<Book> getBooksBoughtThisMonth()
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
    public double getDailyCost()
    {
        double result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Bought) {
                for (var book : books) {
                    if (isSameDay(book.getPurchasedDate(),new Date())) {
                        result+=book.getOriginalPrice();
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public double getMonthlyCost(){
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        double result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Bought) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result+=book.getOriginalPrice();
                        //System.out.println(book);
                    }
                }
            }
        }
        return result;
    }

    public double getYearlyCost(){
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
        double result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Bought) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result+=book.getOriginalPrice();
                        //System.out.println(book);
                    }
                }
            }
        }
        return result;
    }

    public Integer numberOfBooksBoughtToday()
    {
        Integer result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Bought) {
                for (var book : books) {
                    if (isSameDay(book.getPurchasedDate(),new Date())) {
                        result++;
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public Integer numberOfBooksBoughtThisMonth()
    {
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        Integer result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Bought) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result++;
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public Integer numberOfBooksBoughtThisYear()
    {
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
        Integer result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Bought) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result++;
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }

}