package Controllers;

import Models.Gender;
import Models.Librarian;
import Models.StandardViewResponse;
import Models.User;

import java.time.LocalDate;

public interface Modifiable {

    StandardViewResponse<User> editUser(String name, String surname, String username,
                                        String salary, String phoneNum, int id, Gender gender, int accessLevel
            , LocalDate localDate);

    StandardViewResponse<User> addUser(String name, String surname, String username,
                                       String password, String salary, String phoneNum, LocalDate localDate,
                                       Gender gender, int accessLevel, String checkPassword);

    User findUserById(int id);

    boolean deleteUserById(int id);
}
