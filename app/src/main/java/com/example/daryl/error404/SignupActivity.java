package com.example.daryl.error404;

//import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText dobEdit;
    EditText emailEdit;
    EditText passwordEdit;
    Spinner accountTypeSpinner;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void signUpClick(View v)
    {
        firstNameEdit =  findViewById(R.id.firstNameEdit);
        lastNameEdit =  findViewById(R.id.lastNameEdit);
        dobEdit =  findViewById(R.id.dobEdit);
        emailEdit =  findViewById(R.id.emailEdit);
        passwordEdit =  findViewById(R.id.passwordEdit);
        accountTypeSpinner =  findViewById(R.id.accountTypeSpinner);


        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String dob = dobEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String accountType = accountTypeSpinner.getSelectedItem().toString();

        Map<String, Object> info = new HashMap<>();
        if(verifyInfo(firstName,lastName,password,email,dob)) {
            info.put("firstName", firstName);
            info.put("lastName", lastName);
            info.put("dateOfBirth", dob);
            info.put("email", email);
            info.put("password", password);
            info.put("accountType", accountType);

            db.child("users").child(email).setValue(info);

            Intent i = new Intent(this, WelcomeActivity.class);
            i.putExtra("FIRSTNAME", firstName);
            i.putExtra("ROLE", accountType);
            startActivity(i);
        }
        else{

            Toast.makeText(this,"Your infomations are not entered properly", Toast.LENGTH_LONG).show();
        }
        
    }

    private boolean verifyAdmin(){
        boolean found=false;



        return found;
    }

    private boolean verifyInfo(String fName, String lName, String pWord, String email, String dob) {
        return !(fName == null || fName.isEmpty() ||
                lName == null || lName.isEmpty() ||
                pWord == null || pWord.isEmpty() ||
                email == null || email.isEmpty() ||
                dob == null || dob.isEmpty());
    }
}
