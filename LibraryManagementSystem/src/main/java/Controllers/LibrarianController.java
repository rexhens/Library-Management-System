package Controllers;

import Models.Admin;
import Models.Gender;
import Models.Librarian;
import Models.StandardViewResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LibrarianController {
    public StandardViewResponse<Librarian> editLibrarian(String name, String surname, String username,
                                                        String salary, String phoneNum,int id,Gender gender,int accessLevel
                                                        ,LocalDate localDate)
    {
        double salaryDouble;
        Librarian librarian = findLibrarianById(id);
        try {

            if(name.isEmpty() || surname.isEmpty() || username.isEmpty()
                    || salary.isEmpty())
            {
                return new StandardViewResponse<>(librarian,"Fields are empty!");
            }


            if(name.length() < 3 || name.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Name can't have this length!");
            }else if(name.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Name can't contain numbers!");
            }
            for(char ch : name.toCharArray())
            {
                if(isSpecialChar(ch)){
                    return new StandardViewResponse<>(librarian,"Name can't contain special characters!");
                }
            }
            if (!Character.isUpperCase(name.charAt(0))) {
                // Convert the first letter to uppercase
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }


            if(surname.length() < 3 || surname.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Surname cannot have this length!");
            }else if(surname.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Surname can't contain numbers!");
            }
            for(char ch : surname.toCharArray())
            {
                if(isSpecialChar(ch)){
                    return new StandardViewResponse<>(librarian,"Surname can't contain special characters!");
                }
            }if (!Character.isUpperCase(surname.charAt(0))) {
                // Convert the first letter to uppercase
                surname = Character.toUpperCase(surname.charAt(0)) + surname.substring(1);
            }

            if(!phoneNum.matches("^\\+355 6[0-9] [0-9]{3} [0-9]{4}$"))
            {
                return new StandardViewResponse<>(librarian,"Phone number must be of specified format +355 6X XXX XXXX!");
            }else if(!isUniqueUsername(username) && !findLibrarianById(id).getUsername().equals(username))
            {
                return new StandardViewResponse<>(librarian,"There already exists a user with this username");
            }
            LocalDate localDateCompare = LocalDate.now();
            if (localDate.isAfter(localDateCompare)) {
                return new StandardViewResponse<>(librarian,"BirthDate cannot be after actual date!");
            }else if(localDate.isBefore(localDate.minusYears(100)))
            {
                return new StandardViewResponse<>(librarian,"You cannot be this old!");
            }
            salaryDouble = Double.parseDouble(salary);

            librarian.setName(name);
            librarian.setSurname(surname);
            librarian.setUsername(username);
            librarian.setPhoneNum(phoneNum);
            librarian.setSalary(salaryDouble);
            librarian.setBirthDate(localDate);
            librarian.setGender(gender);
            librarian.setAccessLevel(accessLevel);

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
                                                        String password, String salary, String phoneNum, LocalDate localDate,
                                                        Gender gender,int accessLevel,String checkPassword)
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
            for(char ch : name.toCharArray())
            {
                if(isSpecialChar(ch)){
                    return new StandardViewResponse<>(librarian,"Name can't contain special characters!");
                }
            }
            if (!Character.isUpperCase(name.charAt(0))) {
                // Convert the first letter to uppercase
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }


            if(surname.length() < 3 || surname.length() > 20)
            {
                return new StandardViewResponse<>(librarian,"Surname cannot have this length!");
            }else if(surname.matches(".*\\d+.*")){
                return new StandardViewResponse<>(librarian,"Surname can't contain numbers!");
            }
            for(char ch : surname.toCharArray())
            {
                if(isSpecialChar(ch)){
                    return new StandardViewResponse<>(librarian,"Surname can't contain special characters!");
                }
            }
            if (!Character.isUpperCase(surname.charAt(0))) {
                // Convert the first letter to uppercase
                surname = Character.toUpperCase(surname.charAt(0)) + surname.substring(1);
            }


            if(!isValidPassword(password).isEmpty())
            {
                return new StandardViewResponse<>(librarian,isValidPassword(password));
            }
            if(!phoneNum.matches("^\\+355 6[0-9] [0-9]{3} [0-9]{4}$"))
            {
                return new StandardViewResponse<>(librarian,"Phone number must be of specified format +355 6X XXX XXXX!");
            }else if(!isUniqueUsername(username))
            {
                return new StandardViewResponse<>(librarian,"There already exists a user with this username");
            }
            if(!checkPassword.equals(password))
            {
            return new StandardViewResponse<>(librarian,"The password must match with verify password!");
            }
            salaryDouble = Double.parseDouble(salary);
            LocalDate localDateCompare = LocalDate.now();
            if (localDate.isAfter(localDateCompare)) {
                return new StandardViewResponse<>(librarian,"BirthDate cannot be after actual date!");
            }else if(localDate.isBefore(localDate.minusYears(100)))
            {
                return new StandardViewResponse<>(librarian,"You cannot be this old!");
            }
            librarian = new Librarian(name, surname, username,
                    password, salaryDouble, phoneNum,gender,localDate,accessLevel);
            addLibrarian(librarian);
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
        var libararians = FileController.librarians;
        for(Librarian librarian : libararians)
        {
            if(librarian.getUsername().equals(username))
            {
                return false;
            }
        }
        return true;
    }
    public Librarian findLibrarianById(int id)
    {
        for(Librarian librarian : FileController.librarians)
        {
            if(librarian.getId() == id)
                return librarian;
        }
        return null;
    }

    public void  addLibrarian(Librarian librarian)
    {
        FileController.librarians.add(librarian);
    }
    public Librarian findLibrarian(int index){return FileController.librarians.get(index);}

    public void deleteLibrarianByUsername(String username)
    {
        int index = 0;
        for(int i = 0; i < FileController.librarians.size();i++)
        {
            if(FileController.librarians.get(i).getUsername().equals(username)) {
                deleteLibrarianById(i);
                return;
            }
        }
    }
    public  void deleteLibrarianById(int id)
    {
        FileController.librarians.remove(id);
    }

}
