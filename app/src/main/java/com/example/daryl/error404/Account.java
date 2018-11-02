package com.example.daryl.error404;
import android.widget.DatePicker;
import java.util.Date;
public class Account {
    //Instances de classes
    String typeOfAccount;
    String password;
    String firstName;
    String lastName;
    String dateOfBirth;
    String email;

    public Account(){}
    public Account( String firstName, String lastName, String dateOfBirth, String email, String password){
        this.lastName=lastName;
        this.password = password;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public Account( String firstName, String lastName, String dateOfBirth, String email, String password,String type){
        this.lastName=lastName;
        this.password = password;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.typeOfAccount=type;
    }
    //Setters methods
    //In case the user has to change their info

    public void setTypeOfAccount(String newTypeOfAccount){
        typeOfAccount = newTypeOfAccount;
    }
    public void setPasseword(String newPasseword){
        password = newPasseword;
    }
    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }
    public void setLastName(String newLastName){
        lastName = newLastName;
    }
    public void setDateOfBirth(String newDateOfBirth){
        dateOfBirth = newDateOfBirth;
    }
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    //Getters methods
    //To get the info of the user
    public String getTypeOfAccount(){
        return typeOfAccount;
    }
    public String getPasseword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
}
