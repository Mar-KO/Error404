package com.example.daryl.error404;

import java.util.List;

//Classe pour le compte de l'amdministrateur
public class AdminAccount extends Account {
    List<Service> services;
    public AdminAccount( String firstName, String lastName,
                        String dateOfBirth, String email, String passeword){
        super(firstName,lastName,dateOfBirth,email,passeword);
        setTypeOfAccount("Admin");
    }

    public void addList(Service s){
        services.add(s);
    }
}
