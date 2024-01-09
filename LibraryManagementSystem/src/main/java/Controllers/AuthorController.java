package Controllers;

import Models.Author;
import Models.Gender;
import Models.Librarian;
import Models.User;

public class AuthorController {

    public void addAuthor(Author a){
        FileController.authors.add(a);
    }

	public boolean createAuthor(String name, String surname,Gender gender) {
        for(Author a:FileController.authors){
            if(a.getName().equals(name)&&a.getSurname().equals(surname))
            return false;
        }
        Author author=new Author (name,surname,gender);
		addAuthor(author);
		FileController.writeAuthors();
		return true;
	}

	public Author findAuthor(int id) {
		for(Author a:FileController.authors){
        if(a.getID()==id)
			return a;
		}
		return null;
	}


    public boolean authorNameValidation(){
        return false;
    }
}
