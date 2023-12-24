package Models;

public class Admin extends User {

    @Override
    public void setUserRole(Roles userRole) {
        super.setUserRole(Roles.Admin);
    }

    public Admin(String username, String password) {
        super(username, password);

    }

    public Admin(String name, String surname, String username, Roles userRole, String password, double salary, String phoneNum) {
        super(name, surname, username, userRole, password, salary, phoneNum);
    }

}
