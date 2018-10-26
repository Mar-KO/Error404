package com.example.daryl.error404;

//Classe pour le compte de l'utilisateur
public class UserAccount extends Account {
    public UserAccount( String firstName, String lastName,
                         String dateOfBirth, String email, String passeword){
        super( firstName,lastName,dateOfBirth,email,passeword);
        setTypeOfAccount("User");
    }
}
