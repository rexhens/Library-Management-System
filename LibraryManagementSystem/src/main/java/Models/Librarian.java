package Models;

import java.io.Serializable;

public class Librarian extends User implements Serializable {

    public Librarian(String username, String password) {
        super(username, password,Roles.Librarian);
    }

    public Librarian(String name, String surname, String username, String password, double salary, String phoneNum) {
        super(name, surname, username, Roles.Librarian, password, salary, phoneNum);
    }
    public String toString()
    {
        return "Name: " + getName() +
                "\nUsername: " + getUsername() +
                "\nPassword: " + getPassword() +
                "\nRole: " + getUserRole() +
                "\nSalary: " + getSalary() +
                "\nPhone Number: " + getPhoneNum();
    }
}
