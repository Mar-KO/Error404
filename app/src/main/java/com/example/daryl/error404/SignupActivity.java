package com.example.daryl.error404;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUpClick(View v)
    {
        EditText firstName = (EditText) findViewById(R.id.firstNameEdit);
        EditText lastName = (EditText) findViewById(R.id.lastNameEdit);
        EditText dob = (EditText) findViewById(R.id.dobEdit);
        EditText email = (EditText) findViewById(R.id.emailEdit);
        EditText password = (EditText) findViewById(R.id.passwordEdit);
        Spinner accountType = (Spinner) findViewById(R.id.accountTypeSpinner);

        
    }
}
