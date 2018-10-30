package com.example.daryl.error404;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    //instance représentant les infos qui seront entrées dans la page d'accueil

    EditText email;
    EditText password;

    //Instance pour entrer données dans firebase
    DatabaseReference db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance().getReference();
    }

    //Méthode pour le bouton sign up de la page d'accueil. Amène vers une page sign up
    public void sighUpOnClick(View view){
        Intent intent= new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    //Méthode lorsque l'utilisateur a déjà un compte et souhaite se connecter
    public void WelcomeOnClick(View view){

        //On va chercher les données entrées par l'utilisateur
        email= findViewById(R.id.userName);
        password=findViewById(R.id.password);

        //On convertit les EditText en leur string
        String sEmail,sPassword;
        sEmail=email.getText().toString();
        sPassword=password.getText().toString();

        //On vérifie si l'utilisateur a entré des données dans les champs demandés

        //Si l'utilisateur n'a pas bien entré les donnés, un toast apparaît et l'avertit
        if(verifyEmailPassword(sEmail,sPassword)){
            Toast.makeText(this,"Enter information properly please", Toast.LENGTH_SHORT);
        }

        //Si l'utilisateur a bien entré les donnés, on l'envoie vers une page welcome
        else{
            Intent intent= new Intent(this, WelcomeActivity.class);
            intent.putExtra("EMAIL", sEmail);
            startActivity(intent);
        }
    }

    //Méthode pour vérifier si l'utilisateur à bien remplit les deux champs
    public boolean verifyEmailPassword(String email, String pWord){
        return !((email == null) || email.isEmpty() ||
                (pWord == null) || pWord.isEmpty());


    }

}
