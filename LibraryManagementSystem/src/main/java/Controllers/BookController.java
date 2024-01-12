package Controllers;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import Models.*;

public class BookController {

    public void addBook(Book b){
        FileController.books.add(b);
    }

    public Book createBook(String ISBN, String title, Author author, ArrayList<Category> categories, String supplier,
                           int purchasedPrice, int originalPrice,int sellingPrice, String address) {
        for(Book b:FileController.books){
            if(b.getISBN().equals(ISBN))
                return null;
        }
        Book book= new Book(ISBN, title, author, categories, supplier, purchasedPrice, originalPrice, sellingPrice,address);
        addBook(book);
        FileController.writeBooks();
        return book;
    }

    public Book findBook(String ISBN) {
        for(Book b:FileController.books){
            if(b.getISBN().equals(ISBN))
                return b;
        }
        return null;
    }

    public void verifyISBN(String ISBN) throws InvalidIsbnFormatException {
        if(!ISBN.matches("^(?=[ 0-9]{17}$)97[89]\\s[0-9]{1,5}\\s[0-9]+\\s[0-9]+\\s[0-9]$")){
            throw new InvalidIsbnFormatException("Invalid ISBN format: " + ISBN);
        }
    }

    static boolean isSameDay(Date date1, Date date2) {
        // Convert both dates to LocalDate
        java.time.LocalDate localDate1 = date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        java.time.LocalDate localDate2 = date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // Compare the dates
        return localDate1.isEqual(localDate2);
    }
    public ArrayList<Book> getBooksSoldToday()
    {
        var result = new ArrayList<Book>();
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (isSameDay(book.getPurchasedDate(), new Date())) {
                        result.add(book);
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Book> getBooksSoldThisMonth()
    {
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        var result = new ArrayList<Book>();
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result.add(book);
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }
    public ArrayList<Book> getBooksSoldThisYear()
    {
        Date beforeMonth = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());
        var result = new ArrayList<Book>();
        var billList = FileController.transactions;
        for(var bill : billList)
        {
            var books = bill.getBooks();
            if( bill.getType() == BillsType.Sold) {
                for (var book : books) {
                    if (book.getPurchasedDate().toInstant().isAfter(beforeMonth.toInstant())) {
                        result.add(book);
                        System.out.println(book);
                    }
                }
            }
        }
        return result;
    }



}