package Controllers;

import java.util.ArrayList;

import Models.Author;
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

}