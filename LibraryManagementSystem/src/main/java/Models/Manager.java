package Models;

public class Manager extends User{
    public Manager(String username, String password) {
        super(username, password);
    }

    public Manager(String name, String surname, String username, Roles userRole, String password, double salary, String phoneNum) {
        super(name, surname, username, userRole, password, salary, phoneNum);
    }
}
