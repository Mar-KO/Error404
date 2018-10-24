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

    EditText email;
    EditText password;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void sighUpOnClick(View view){
        Intent intent= new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void WelcomeOnClick(View view){
        email= findViewById(R.id.userName);
        password=findViewById(R.id.password);
        String sEmail,sPassword;
        sEmail=email.getText().toString();
        sPassword=password.getText().toString();

        if(verifyEmailPassword(sEmail,sPassword)){
            Toast.makeText(this,"Enter information properly please", Toast.LENGTH_SHORT);
        }
        else{
        Intent intent= new Intent(this, WelcomeActivity.class);
        intent.putExtra("EMAIL", sEmail);
        startActivity(intent);
        }
    }

    public boolean verifyEmailPassword(String email, String pWord){
            return !((email == null) || email.isEmpty() ||
                    (pWord == null) || pWord.isEmpty());


    }

}
