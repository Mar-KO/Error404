package com.example.daryl.error404;
import android.widget.DatePicker;
import java.util.Date;
public class Account {
    //Instances de classes
    String typeOfAccount;
    String passeword;
    String firstName;
    String lastName;
    String dateOfBirth;
    String email;
    String id;
    public Account(String id, String firstName, String lastName, String dateOfBirth, String email, String passeword, String typeOfAccount){
        this.id = id;
        this.typeOfAccount = typeOfAccount;
        this.passeword = passeword;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
    //Setters methods
    //In case the user has to change their info
    public void setId(String newId){
        id = newId;
    }
    public void setTypeOfAccount(String newTypeOfAccount){
        typeOfAccount = newTypeOfAccount;
    }
    public void setPasseword(String newPasseword){
        passeword = newPasseword;
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
        return passeword;
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