package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Models.Bill;
import Models.BillsType;
import Models.Book;

public class BillController {
    public void addBill(Bill b){
        FileController.transactions.add(b);
    }

	public boolean createBill(int ID, ArrayList<Book> books, ArrayList<Integer> quantity,int totalPrice,BillsType type) {
        Bill b=new Bill(ID, books, quantity, totalPrice,type);
        addBill(b);
        FileController.writeTransactions();
        printBill(b);
		return true;
	}

    public void printBill(Bill b){
        try{
            File print = new File ("printableBills/Bill"+b.getBillNumber()+".txt");
            try (
                PrintWriter o=new PrintWriter(print);
            ){
                o.print(b);
            }
            System.out.println("Bill "+b.getBillNumber()+" printed successfully.");
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }catch(FileNotFoundException e1){
            System.out.println(e1.getMessage());
        }
    }
}
