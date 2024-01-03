package Models;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static int noUsers;
    private int id;
    private String name;
    private String surname;
    private String username;
    private Roles userRole;
    private String password;
    private double salary;
    private String phoneNum;

    public User(){

    }
    public User(String username, String password,Roles role){
        this.username = username;
        this.password = password;
        this.userRole = role;
        id++;
    }

    public User(String name, String surname, String username,
                Roles userRole, String password, double salary,
                String phoneNum) {
        this.id = ++noUsers;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.userRole = userRole;
        this.password = password;
        this.salary = salary;
        this.phoneNum = phoneNum;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public int getId(){ return this.id;}
}
