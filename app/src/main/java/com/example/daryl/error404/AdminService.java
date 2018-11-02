package com.example.daryl.error404;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import java.util.*;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminService extends AppCompatActivity {
    DatabaseReference db;
    EditText name, price;
    String _name;
    double _price;
    ListView serviceListView;
    List<Service> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service);

        serviceListView = (ListView) findViewById(R.id.serviceListView);
        serviceList = new ArrayList<>();

        db= FirebaseDatabase.getInstance().getReference("service");

    }
    public void addOnClick(View view){
        price= findViewById(R.id.priceEdit);
        name=findViewById(R.id.nameEdit);
        _name= name.getText().toString();
        try{
        _price= Double.parseDouble(price.getText().toString());}
        catch(NumberFormatException e){
            Toast.makeText(this,"Please enter the price correctly", Toast.LENGTH_SHORT).show();
        }

        if(_price<0){Toast.makeText(this,"The price cannot be negative",Toast.LENGTH_SHORT).show(); }
        else{
        Service service= new Service(_name,_price);
        db.child(db.push().getKey()).setValue(service);}
        Toast.makeText(this,"Service added", Toast.LENGTH_SHORT).show();
        name.setText("");
        price.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                serviceList.clear();

                for(DataSnapshot serviceSnapshot: dataSnapshot.getChildren()){
                    Service service = serviceSnapshot.getValue(Service.class);

                    serviceList.add(service);
                }

                ServiceList adapter = new ServiceList(AdminService.this, serviceList);
                serviceListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
