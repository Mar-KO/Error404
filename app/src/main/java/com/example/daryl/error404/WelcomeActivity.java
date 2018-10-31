package com.example.daryl.error404;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {
    String name=null, role=null;// email;
    TextView _role, _name;
    //DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Va chercher les valeurs transmisent par SignUpActivity
        Intent i=getIntent();
        name= i.getStringExtra("FIRSTNAME");
        role=i.getStringExtra("ROLE");

        //set les valeur sur les TextView appropriés
        _name=findViewById(R.id.nameText);
        _name.setText(name);
        _role= findViewById(R.id.roleText);
        _role.setText(role);
    }

    public void continueOnClick(View v){
        if(role!=null && role.equals("Admin")) {
            Intent i = new Intent(this, AdminService.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"not an Admin", Toast.LENGTH_SHORT).show();
        }
    }
}
