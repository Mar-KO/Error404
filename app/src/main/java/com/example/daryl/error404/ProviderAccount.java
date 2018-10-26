package com.example.daryl.error404;

//Classe pour le compte Provider
public class ProviderAccount extends Account {

    public ProviderAccount( String firstName, String lastName,
                           String dateOfBirth, String email, String passeword) {
        super( firstName, lastName, dateOfBirth, email, passeword);
        setTypeOfAccount("Provider");
    }
}