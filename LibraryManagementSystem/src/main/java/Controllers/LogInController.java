package Controllers;

import Models.Admin;
import Models.User;
import Models.StandardViewResponse;

import java.util.ArrayList;

public class LogInController {
    public static ArrayList<User> users = new ArrayList<>();

    public StandardViewResponse<User> OnLogInBtnClick(String username, String password)
    {
        if(username.isEmpty())
        {
           return  new StandardViewResponse<User>(null,"Username cannot be null!");
        }
        if(password.isEmpty())
        {
            return  new StandardViewResponse<User>(null,"Password cannot be null!");

        }
        users.add(new Admin("admin","admin"));
        for(User user : users)
        {
            if(user.getUsername().equals(username))
            {
                if(user.getPassword().equals(password))
                {
                    return new StandardViewResponse<User>(user,"");
                }else{
                    return  new StandardViewResponse<User>(null,"Wrong Password!");
                }
            }
        }
        return  new StandardViewResponse<User>(null,"Wrong Credentials!");

    }
}
