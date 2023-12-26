package Models;

public class Librarian extends User{
    public Librarian(String username, String password) {
        super(username, password,Roles.Librarian);
    }

    public Librarian(String name, String surname, String username, Roles userRole, String password, double salary, String phoneNum) {
        super(name, surname, username, userRole, password, salary, phoneNum);
    }
}
