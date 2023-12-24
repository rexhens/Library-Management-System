package Controllers;

import Models.Admin;
import Models.User;
import Models.UserResult;

import java.util.ArrayList;

public class LogInController {
    public static ArrayList<User> users = new ArrayList<>();

    public UserResult OnLogInBtnClick(String username, String password)
    {
        if(username.isEmpty())
        {
           return  new UserResult(null,"Username cannot be null!");
        }
        if(password.isEmpty())
        {
            return  new UserResult(null,"Password cannot be null!");

        }
        users.add(new Admin("admin","admin"));
        for(User user : users)
        {
            if(user.getUsername().equals(username))
            {
                if(user.getPassword().equals(password))
                {
                    return new UserResult(user,"");
                }else{
                    return  new UserResult(null,"Wrong Password!");
                }
            }
        }
        return  new UserResult(null,"Wrong Credentials!");

    }
}
