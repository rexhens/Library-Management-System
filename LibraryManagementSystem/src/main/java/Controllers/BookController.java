package Controllers;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import Models.Author;
import Models.Book;
import Models.Category;
import Models.InvalidIsbnFormatException;
import javafx.scene.control.CheckBox;

public class BookController {

    public void addBook(Book b){
        FileController.books.add(b);
    }

    public void createBook(String ISBN, String title, Author author, ArrayList<Category> categories, String supplier,
                              int purchasedPrice, int originalPrice,int sellingPrice, String address) {
        Book book= new Book(ISBN, title, author, categories, supplier, purchasedPrice, originalPrice, sellingPrice,address);
        addBook(book);
    }

    public Book findBook(String ISBN) {
        for(Book b:FileController.books){
            if(b.getISBN().equals(ISBN))
                return b;
        }
        return null;
    }

    public boolean priceValidation (String x){
        try{
            int n= Integer.parseInt(x);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void verifyISBN(String ISBN) throws InvalidIsbnFormatException{
		if(!ISBN.matches("^(?=[ 0-9]{17}$)97[89]\\s[0-9]{1,5}\\s[0-9]+\\s[0-9]+\\s[0-9]$")){
			throw new InvalidIsbnFormatException("Invalid ISBN format: " + ISBN);
        }
	}

    public boolean selectedCategory(ArrayList<CheckBox> c){
        for(CheckBox cc : c){
            if(cc.isSelected()){
                return true;
            }
        }
        return false;
    }

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