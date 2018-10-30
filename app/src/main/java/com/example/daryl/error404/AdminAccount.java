package com.example.daryl.error404;

//Classe pour le compte de l'amdministrateur
public class AdminAccount extends Account {
    public AdminAccount( String firstName, String lastName,
                        String dateOfBirth, String email, String passeword){
        super(firstName,lastName,dateOfBirth,email,passeword);
        setTypeOfAccount("Admin");
    }
}
