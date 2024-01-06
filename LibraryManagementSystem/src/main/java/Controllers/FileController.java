package Controllers;

import Models.Admin;
import Models.Librarian;
import Models.Manager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class FileController<T> extends Application {
    public static ArrayList<Librarian> librarians;
    public static ArrayList<Manager> managers;
    public FileController(){
        if (managers == null) {
            managers = new ArrayList<>();
        }
        if (librarians == null) {
            librarians = new ArrayList<>();
        }
    }
    public void writeToFile(ArrayList<Librarian> objects, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for(Librarian object : objects)
            {
                outputStream.writeObject(object);
            }
            System.out.println("Librarians written successfully to file!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Librarian> readFromFile(String fileName) {
        ArrayList<Librarian> objects = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Librarian object = (Librarian) inputStream.readObject();
                    System.out.println(object);
                    objects.add(object);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException ignored) {
            // Handle exceptions as needed
        }
        System.out.println("Librarians read successfully");
        return objects;
    }

    public void start(Stage stage) throws IOException, ClassNotFoundException {
//            ArrayList<Librarian> setLibrarians = new ArrayList<>();
//            setLibrarians.add(new Librarian("name1","surname1","username1","password",100,"23423424"));
//            setLibrarians.add(new Librarian("name2","surname2","username2","password",100,"23423424"));
//            setLibrarians.add(new Librarian("name3","surname3","username3","password",100,"23423424"));
//            Admin.setLibrarians(setLibrarians);
     //   writeToFile(Admin.getLibrarians(), "users.dat");
// readFromFile("users.dat");
        }
        public static void main(String[] args) {
            launch();
        }
    }

