package Controllers;

import Models.*;


public class AuthorController {

    public void addAuthor(Author a) {
        FileController.authors.add(a);
    }

    public Author createAuthor(String name, String surname, Gender gender) {
        for (Author a : FileController.authors) {
            if (a.getName().equals(name) && a.getSurname().equals(surname))
                return null;
        }
        Author author = new Author(name, surname, gender);
        addAuthor(author);
        return author;
    }

    public Author findAuthor(int id) {
        for (Author a : FileController.authors) {
            if (a.getID() == id)
                return a;
        }
        return null;
    }
    static boolean isSpecialChar(char c) {
        // Define your set of special characters
        String specialChars = "!@#$%^&*()_";
        return specialChars.contains(String.valueOf(c));
    }
    static boolean isUniqueAuthor(int ID, String name, String surname) {
        for (Author author : FileController.authors) {
            if (author.getID()==ID && author.getName().equals(name) && author.getSurname().equals(surname)) {
                return false;
            }
        }
        return true;
    }

    // public boolean authorNameValidation() {
    //     return false;
    // }

    // public boolean deleteAuthor(int id) {
    //     var exists = findAuthor(id);
    //     if (exists == null) {
    //         return false;
    //     }
    //     for (Author author : FileController.authors) {
    //         if (author.getID() == id) {
    //             FileController.authors.remove(author);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public StandardViewResponse<Author> editAuthor(int ID, String name, String surname, Gender gender) {
        Author author = findAuthor(ID);
        try {

            if (name.isEmpty() || surname.isEmpty()) {
                return new StandardViewResponse<>(author, "Fields are empty!");
            }

            if (name.length() < 3 || name.length() > 20) {
                return new StandardViewResponse<>(author, "Name can't have this length!");
            } else if (name.matches(".*\\d+.*")) {
                return new StandardViewResponse<>(author, "Name can't contain numbers!");
            }
            for (char c : name.toCharArray()) {
                if (isSpecialChar(c)) {
                    return new StandardViewResponse<>(author, "Name can't contain special characters!");
                }
            }
            if (!Character.isUpperCase(name.charAt(0))) {
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            }

            if (surname.length() < 3 || surname.length() > 20) {
                return new StandardViewResponse<>(author, "Surname cannot have this length!");
            } else if (surname.matches(".*\\d+.*")) {
                return new StandardViewResponse<>(author, "Surname can't contain numbers!");
            }
            for (char c : surname.toCharArray()) {
                if (isSpecialChar(c)) {
                    return new StandardViewResponse<>(author, "Surname can't contain special characters!");
                }
            }
            if (!Character.isUpperCase(surname.charAt(0))) {
                // Convert the first letter to uppercase
                surname = Character.toUpperCase(surname.charAt(0)) + surname.substring(1);
            }
            if(!isUniqueAuthor(ID, name, surname)) {
                return new StandardViewResponse<>(author, "There already exists an Auhtor with this credentials");
            }
            author.setName(name);
            author.setSurname(surname);
            author.setGender(gender);
            System.out.println("Author was successfully edited");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new StandardViewResponse<>(author, e.getMessage());
        }
        return new StandardViewResponse<>(author, "");
    }
}
