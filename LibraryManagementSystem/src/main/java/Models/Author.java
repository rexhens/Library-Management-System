package Models;
import java.io.Serial;
import java.io.Serializable;

public class Author implements Serializable{
    @Serial
    private static final long serialVersionUID = -3802481739164397635L;
    private String name;
    private String surname;
    private Gender gender;

    public Author (String name,String surname, Gender gender){
        this.name=name;
        this.surname=surname;
        this.gender=gender;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

}