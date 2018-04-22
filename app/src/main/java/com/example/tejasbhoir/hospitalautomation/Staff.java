package com.example.tejasbhoir.hospitalautomation;

public class Staff {

    private String ID;
    private String password;
    private String firstName;
    private String lastName;

    Staff() {
    }

    // Getters
    public String getID() { return ID;}
    public String getPassword() {return password;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}

    // Setters
    public void setID(String ID) {this.ID = ID;}
    public void setPassword(String password) {this.password = password;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
}