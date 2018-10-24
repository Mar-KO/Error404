package com.example.daryl.error404;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    String name, role;
    TextView _role, _name;
    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent i=getIntent();
        name= i.getStringExtra("FIRSTNAME");
        role=i.getStringExtra("ROLE");
        _name=findViewById(R.id.nameText);
        _name.setText(name);
        _role= findViewById(R.id.roleText);
        _role.setText(role);
    }


}
