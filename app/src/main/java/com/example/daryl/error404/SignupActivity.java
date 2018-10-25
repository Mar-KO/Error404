
package com.example.daryl.error404;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    EditText firstNameEdit;
    EditText lastNameEdit;
    TextView dobEdit;
    EditText emailEdit;
    EditText passwordEdit;
    Spinner accountTypeSpinner;
    DatabaseReference db;
    private final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public static boolean hasAdmin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = FirebaseDatabase.getInstance().getReference();
        //Create a spinner to choose a date
        mDisplayDate =  findViewById(R.id.setDate);
        firstNameEdit =  findViewById(R.id.firstNameEdit);
        lastNameEdit =  findViewById(R.id.lastNameEdit);
        dobEdit =  findViewById(R.id.setDate);
        emailEdit =  findViewById(R.id.emailEdit);
        passwordEdit =  findViewById(R.id.passwordEdit);
        accountTypeSpinner =  findViewById(R.id.accountTypeSpinner);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignupActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable((new ColorDrawable((Color.TRANSPARENT))));
                dialog.show();
            }
        });

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

    public void signUpClick(View v)
    {
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
            hasAdmin=true;
            Intent i = new Intent(this, WelcomeActivity.class);
            i.putExtra("FIRSTNAME", firstName);
            i.putExtra("ROLE", accountType);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Your infomation are not entered properly", Toast.LENGTH_LONG).show();
        }
    }

    private boolean verifyInfo(String fName, String lName, String pWord, String email, String dob) {

        if ((accountTypeSpinner.getSelectedItem().toString().equals("Admin")) && !hasAdmin) {
            if(pWord!="admin" || fName!="admin"){
                return false;}
            else{
                return true;
            }
        }
        else{ return !(fName == null || fName.isEmpty() ||
                lName == null || lName.isEmpty() ||
                pWord == null || pWord.isEmpty() ||
                email == null || email.isEmpty() ||
                dob == null || dob.isEmpty());
        }
    }
}