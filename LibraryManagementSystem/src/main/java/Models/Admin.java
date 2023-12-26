package Models;

import java.util.ArrayList;

public class Admin extends User {

    private static ArrayList<Librarian> librarians;
    private static ArrayList<Manager> managers;

    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    public static void setLibrarians(ArrayList<Librarian> librarians) {
        Admin.librarians = librarians;
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

    public static void setManagers(ArrayList<Manager> managers) {
        Admin.managers = managers;
    }

    public Admin(String username, String password) {
        super(username, password,Roles.Admin);
        managers = new ArrayList<>();
        librarians = new ArrayList<>();
    }

    public Admin(String name, String surname, String username, Roles userRole, String password, double salary, String phoneNum) {
        super(name, surname, username, userRole, password, salary, phoneNum);
        managers = new ArrayList<>();
        librarians = new ArrayList<>();
    }
    public static void  addLibrarian(Librarian librarian)
    {
        librarians.add(librarian);
    }

}
