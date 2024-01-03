package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends User implements Serializable {

    private static ArrayList<Librarian> librarians;
    private static ArrayList<Manager> managers;
    public static ArrayList<Librarian> getLibrarians() {

        if(librarians == null)
            librarians = new ArrayList<>();
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
        if (managers == null) {
            managers = new ArrayList<>();
        }
        if (librarians == null) {
            librarians = new ArrayList<>();
        }
    }

    public Admin(String name, String surname, String username, Roles userRole, String password, double salary, String phoneNum) {
        super(name, surname, username, userRole, password, salary, phoneNum);
        if (managers == null) {
            managers = new ArrayList<>();
        }
        if (librarians == null) {
            librarians = new ArrayList<>();
        }
    }
    public static void  addLibrarian(Librarian librarian)
    {
        librarians.add(librarian);
    }
    public static Librarian findLibrarian(int index){return librarians.get(index);}
    public static Librarian findLibrarianById(int id)
    {
        for(Librarian librarian : librarians)
        {
            if(librarian.getId() == id)
                return librarian;
        }
        return null;
    }
}
