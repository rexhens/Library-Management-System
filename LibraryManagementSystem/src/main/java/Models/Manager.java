package Models;

import java.io.Serial;
import java.time.LocalDate;

public class Manager extends User{
    @Serial
    private static final long serialVersionUID = -5568420036250481739L;

    public Manager(String username, String password) {
        super(username, password,Roles.Manager);
    }

    public Manager(String name, String surname, String username, Roles userRole, String password,
                   double salary, String phoneNum, Gender gender, LocalDate date,int accesslevel) {
        super(name, surname, username, userRole, password, salary, phoneNum,date,gender,accesslevel);
    }
}
