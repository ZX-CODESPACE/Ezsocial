package com.example.nurtailamiev.ezsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    EditText email;
    EditText password;
    SqlHelper db;
    User user;
    TextView tv;
    String status = "Logged in successfully!";
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(getDrawable(R.drawable.logo));


    }

    public void clickRegister(View v){

        intent = new Intent(getApplicationContext(), com.example.nurtailamiev.ezsocial.RegisterActivity.class);
        startActivity(intent);
    }

    public void logIn(View v){



        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);

        db = new SqlHelper(this);

        user = new User();

        db.getAllItems(email.getText().toString());

        List<User> userList = db.getAllItems(email.getText().toString());

        String userPassword = "";


        for(User u : userList){
            userPassword = u.password;
        }


        if(password.getText().toString().equals(userPassword) && !(password.getText().toString() == ""))
        {
            intent = new Intent(getApplicationContext(), com.example.nurtailamiev.ezsocial.SignedInSuccessfully.class);
            intent.putExtra("status", status);
            startActivityForResult(intent, 1);
        }
        else
        {
            tv = (TextView)findViewById(R.id.tvDisplay);
            tv.setText("Email/password does not match our records.  Please try again.");
        }


    }



}
