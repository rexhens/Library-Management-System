package Controllers;

import Models.BillsType;

import java.time.ZonedDateTime;
import java.util.Date;

import static Controllers.BookController.isSameDay;

public class IncomesController {

    public double getDailyIncome()
    {
        double result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (isSameDay(book.getPurchasedDate(),new Date())) {
                        result+=book.getOriginalPrice();
                       // System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public double getMonthlyIncome(){
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        double result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
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

    public double getYearlyIncome(){
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
        double result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
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

    public Integer numberOfBooksSoldToday()
    {
        Integer result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (isSameDay(book.getPurchasedDate(),new Date())) {
                        result++;
                      //  System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public Integer numberOfBooksSoldThisMonth()
    {
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        Integer result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result++;
                      //  System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public Integer numberOfBooksSoldThisYear()
    {
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
        Integer result = 0;
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result++;
                      //  System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
}
