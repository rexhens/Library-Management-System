package Controllers;

import Models.Admin;
import Models.Librarian;
import Models.StandardViewResponse;

public class LibrarianController {
    public StandardViewResponse<Librarian> editLibrarian(String name, String surname, String username,
                                                        String salary, String phoneNum,int id)
    {
        double salaryDouble;
        Librarian librarian = Admin.findLibrarianById(id);
        try {

            if(name.isEmpty() || surname.isEmpty() || username.isEmpty()
                    || salary.isEmpty())
            {
                return new StandardViewResponse<>(librarian,"Fields are empty!");
            }


            if(name.length() < 3 || name.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Name cannot have this length!");
            }else if(name.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Name can't contain numbers!");
            }

            if(surname.length() < 3 || surname.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Surname cannot have this length!");
            }else if(surname.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Surname can't contain numbers!");
            }
            if(phoneNum.length() != 10)
            {
                return new StandardViewResponse<>(librarian,"Phone number must be exactly 10 characters!");
            }else if(!phoneNum.matches("\\d+"))
            {
                return new StandardViewResponse<>(librarian,"Phone number cannot contain characters!");
            }else if(!isUniqueUsername(username) && !Admin.findLibrarianById(id).getUsername().equals(username))
            {
                return new StandardViewResponse<>(librarian,"There already exists a user with this username");
            }
            salaryDouble = Double.parseDouble(salary);

            librarian.setName(name);
            librarian.setSurname(surname);
            librarian.setUsername(username);
            librarian.setPhoneNum(phoneNum);
            librarian.setSalary(salaryDouble);

            System.out.println("Librarian was successfully edited");
        }catch(NumberFormatException n){
            System.out.println(n.getMessage());
            return new StandardViewResponse<>(librarian,"Cannot parse to double!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new StandardViewResponse<>(librarian,e.getMessage());
        }
        return new StandardViewResponse<>(librarian,"");
    }

    public StandardViewResponse<Librarian> addLibrarian(String name, String surname, String username,
                                                        String password, String salary, String phoneNum)
    {
        double salaryDouble;
        Librarian librarian = null;
        try {

            if(name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()
                    || salary.isEmpty())
            {
                return new StandardViewResponse<>(librarian,"Fields are empty!");
            }


            if(name.length() < 3 || name.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Name cannot have this length!");
            }else if(name.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Name can't contain numbers!");
            }

            if(surname.length() < 3 || surname.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Surname cannot have this length!");
            }else if(surname.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Surname can't contain numbers!");
            }
            if(!isValidPassword(password).isEmpty())
            {
                return new StandardViewResponse<>(librarian,isValidPassword(password));
            }
            if(phoneNum.length() != 10)
            {
                return new StandardViewResponse<>(librarian,"Phone number must be exactly 10 characters!");
            }else if(!phoneNum.matches("\\d+"))
            {
                return new StandardViewResponse<>(librarian,"Phone number cannot contain characters!");
            }else if(!isUniqueUsername(username))
            {
                return new StandardViewResponse<>(librarian,"There already exists a user with this username");
            }
            salaryDouble = Double.parseDouble(salary);
            librarian = new Librarian(name, surname, username,
                    password, salaryDouble, phoneNum);
            Admin.addLibrarian(librarian);
            System.out.println("Librarian was successfully added");
        }catch(NumberFormatException n){
            System.out.println(n.getMessage());
            return new StandardViewResponse<>(librarian,"Cannot parse to double!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new StandardViewResponse<>(librarian,e.getMessage());
        }
        return new StandardViewResponse<>(librarian,"");
    }



    static String isValidPassword(String password) {
        // Define validation criteria
        int minLength = 8;
        int maxLength = 20;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;


        if (password.length() < minLength) {
            return "Password Minimum length is 8!";
        }else if(password.length() >maxLength)
        {
            return "Password Maximum length is 20!";
        }


        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (isSpecialChar(ch)) {
                hasSpecialChar = true;
            }
        }
        if(!hasUppercase){
            return "Password must have at least 1 upper case!";
        }else if(!hasLowercase)
        {
            return "Password must have at least 1 lower case!";
        }else if(!hasSpecialChar)
        {
            return "Password must contain at least 1 special character!";
        }else if(!hasDigit)
        {
            return  "Password must contain at least 1 digit!";
        }
        // Check all criteria
        return "";
    }

    static boolean isSpecialChar(char ch) {
        // Define your set of special characters
        String specialChars = "!@#$%^&*()";
        return specialChars.contains(String.valueOf(ch));
    }

    static boolean isUniqueUsername(String username)
    {
        var libararians = Admin.getLibrarians();
        for(Librarian librarian : libararians)
        {
            if(librarian.getUsername().equals(username))
            {
                return false;
            }
        }
        return true;
    }

}
