package Models;

import java.io.Serial;
import java.time.LocalDate;

public class Librarian extends User  {

    @Serial
    private static final long serialVersionUID = -2328426394838809039L;

    public Librarian(String username, String password) {
        super(username, password,Roles.Librarian);
    }

    public Librarian(String name, String surname, String username, String password, double salary, String phoneNum,
                     Gender gender, LocalDate date,int accesslevel) {
        super(name, surname, username, Roles.Librarian, password, salary, phoneNum,date,gender,accesslevel);
    }

}
