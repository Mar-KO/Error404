package com.example.daryl.error404;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText addFirstName;
    EditText addLastName;
    EditText addEmail;
    EditText addPassword;
    Spinner addAccountType;
    Intent i = new Intent(this, WelcomeActivity.class);

    DatabaseReference databaseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseAccount = FirebaseDatabase.getInstance().getReference("account");


        addFirstName = (EditText) findViewById(R.id.firstNameEdit);
        addLastName = (EditText) findViewById(R.id.lastNameEdit);
        addEmail = (EditText) findViewById(R.id.emailEdit);
        addPassword = (EditText) findViewById(R.id.passwordEdit);
        addAccountType = (Spinner) findViewById(R.id.accountTypeSpinner);


//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addInfos();
//
//                i.putExtra("FIRSTNAME", addFirstName.getText().toString());
//                i.putExtra("ROLE", addAccountType.getSelectedItem().toString());
//                startActivity(i);
//            }
//        });



        //Create a spinner to choose a date
        mDisplayDate = (TextView) findViewById(R.id.setDate);
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

    public void signUpButton (View view){
        addInfos();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("FIRSTNAME", addFirstName.getText().toString());
        intent.putExtra("ROLE", addAccountType.getSelectedItem().toString());
        startActivity(intent);

    }

    private void addInfos(){

        String firstName = addFirstName.getText().toString().trim();
        String lastName = addLastName.getText().toString().trim();
        String email = addEmail.getText().toString().trim();
        String password = addPassword.getText().toString().trim();
        String accountType = addAccountType.getSelectedItem().toString();
        String date = mDisplayDate.getText().toString();

        if(!(TextUtils.isEmpty(firstName)|| TextUtils.isEmpty(lastName)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)|| TextUtils.isEmpty(accountType)|| TextUtils.isEmpty(date))){
            String id = databaseAccount.push().getKey();

            Account account = new Account(id, firstName, lastName, date, email, password, accountType );

            databaseAccount.child(id).setValue(account);

            Toast.makeText(this, "Account added", Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this, "You must fill all the field before signing up", Toast.LENGTH_LONG).show();
        }
    }

}
