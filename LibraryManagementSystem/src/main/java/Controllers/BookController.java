package Controllers;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import Models.Author;
import Models.BillsType;
import Models.Book;
import Models.Category;

public class BookController {

    public void addBook(Book b){
        FileController.books.add(b);
    }

    public boolean createBook(String ISBN, String title, Author author, ArrayList<Category> categories, String supplier,
                              int purchasedPrice, int originalPrice,int sellingPrice,int stock, String address) {
        for(Book b:FileController.books){
            if(b.getISBN().equals(ISBN))
                return false;
        }
        Book book= new Book(ISBN, title, author, categories, supplier, purchasedPrice, originalPrice, sellingPrice, stock, address);
        addBook(book);
        FileController.writeBooks();
        return true;
    }

    public Book findBook(String ISBN) {
        for(Book b:FileController.books){
            if(b.getISBN().equals(ISBN))
                return b;
        }
        return null;
    }

    public boolean verifyISBN(String ISBN){
        if(ISBN.matches("^(?=[ 0-9]{17}$)97[89]\\s[0-9]{1,5}\\s[0-9]+\\s[0-9]+\\s[0-9]$")){
            return true;
        }else{
            return false;
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