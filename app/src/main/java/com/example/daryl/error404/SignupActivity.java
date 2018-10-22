package com.example.daryl.error404;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
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
    }

    public void signUpClick(View v)
    {
        firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
        lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
        dobEdit = (EditText) findViewById(R.id.dobEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        accountTypeSpinner = (Spinner) findViewById(R.id.accountTypeSpinner);

        db = FirebaseDatabase.getInstance().getReference();
        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String dob = dobEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String accountType = accountTypeSpinner.getSelectedItem().toString();

        Map<String, Object> info = new HashMap<>();

        info.put("firstName", firstName);
        info.put("lastName", lastName);
        info.put("dateOfBirth", dob);
        info.put("email", email);
        info.put("password", password);
        info.put("accountType", accountType);

        db.child("users").setValue(info);

        
    }
}
