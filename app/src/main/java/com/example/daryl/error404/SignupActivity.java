package com.example.daryl.error404;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    //Création de variables représentant les champs de la page sign up
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private TextView dobEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private Spinner accountTypeSpinner;
    private static ArrayList<Account> accountList;
    DatabaseReference db;

    //Variables nécessaires à la création du spinner pour les dates
    private final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //Les données que l'utilisateur rentre dans l'application
    String firstName;
    String lastName;
    String dob;
    String email;
    String password;
    String accountType;


    //Variables boolean qui servira pour vérifier si un compte admin a déjà été créer
    private boolean hasAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        accountList = new ArrayList<Account>();
        db = FirebaseDatabase.getInstance().getReference();
        //Create a spinner to choose a date
        mDisplayDate = findViewById(R.id.setDate);

        //Prend l'information des champs remplit
        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        dobEdit = findViewById(R.id.setDate);
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);


        //Le listener et la méthode onCLick du date picker
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Le style du date picker
                DatePickerDialog dialog = new DatePickerDialog(SignupActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable((new ColorDrawable((Color.TRANSPARENT))));
                dialog.show();
            }
        });

        //Création d'une objet date picker lorsque l'on clique sur "Select a date"
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }


        };
    }

    //methode du bouton sign up
    public void signUpClick(View v) {
        //transforme les champs remplit en String
        firstName = firstNameEdit.getText().toString();
        lastName = lastNameEdit.getText().toString();
        dob = dobEdit.getText().toString();
        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();
        accountType = accountTypeSpinner.getSelectedItem().toString();
        Account currentAccount = createAccount();
        verifyAdmin();
        if (hasAdmin == true && accountType=="Admin") {
            Toast.makeText(this, "There is already an Admin", Toast.LENGTH_SHORT).show();
        }
        else {
            //vérifie si tous les champs sont remplit correctement
            if (verifyInfo(firstName, lastName, password, email, dob)) {

                //ajoute le Account à firebase
                String idDB = db.push().getKey();
                db.child("users").child(idDB).setValue(currentAccount);
                accountList.add(currentAccount);

                //Ouvre l'activité activity_Welome
                Intent i = new Intent(this, WelcomeActivity.class);
                i.putExtra("ID", idDB);
                startActivity(i);
            }
            else {
                Toast.makeText(this, "Your infomation are not entered properly", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean verifyInfo(String fName, String lName, String dob, String email, String pWord) {
        return !(fName == null || fName.isEmpty() ||
                lName == null || lName.isEmpty() ||
                pWord == null || pWord.isEmpty() ||
                email == null || email.isEmpty() ||
                dob == null || dob.isEmpty());
    }

    //Méthode qui vérifie s'il y a déjà un administrateur
    private void verifyAdmin() {

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Account account = ds.getValue(Account.class);
                    if (account.getTypeOfAccount().equals("Admin")) {
                        hasAdmin = true;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //creates an account depending on the type
    public Account createAccount() {
        Account temp = null;

        switch (accountTypeSpinner.getSelectedItem().toString()) {
            case ("Admin"):
                temp = new AdminAccount(firstNameEdit.getText().toString(),
                        lastNameEdit.getText().toString(),
                        dobEdit.getText().toString(),
                        emailEdit.getText().toString(),
                        passwordEdit.getText().toString());
                break;
            case ("Provider"):
                temp = new ProviderAccount(firstNameEdit.getText().toString(),
                        lastNameEdit.getText().toString(),
                        dobEdit.getText().toString(),
                        emailEdit.getText().toString(),
                        passwordEdit.getText().toString());
                break;
            case ("User"):
                temp = new UserAccount(firstNameEdit.getText().toString(),
                        lastNameEdit.getText().toString(),
                        dobEdit.getText().toString(),
                        emailEdit.getText().toString(),
                        passwordEdit.getText().toString());
                break;
        }
        return temp;
    }


}