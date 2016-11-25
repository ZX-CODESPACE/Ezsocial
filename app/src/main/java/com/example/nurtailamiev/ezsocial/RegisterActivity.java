package com.example.nurtailamiev.ezsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import com.example.nurtailamiev.ezsocial.SqlHelper;
import com.example.nurtailamiev.ezsocial.User;

public class RegisterActivity extends AppCompatActivity {
    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    SqlHelper db;
    User s;
    Intent intent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(getDrawable(R.drawable.logo));
    }

    public void signUp(View v){
        first_name = (EditText)findViewById(R.id.etFN);
        last_name = (EditText)findViewById(R.id.etLN);
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPW);

        db = new SqlHelper(this);

        s = new User();

        s.setFName(first_name.getText().toString());
        s.setLName(last_name.getText().toString());
        s.setEmail(email.getText().toString());
        s.setPassword(password.getText().toString());

        db.addUser(s);

        db.getAllItems(s.getEmail());


        intent = new Intent(getApplicationContext(),com.example.nurtailamiev.ezsocial.SignUpSuccess.class);
        intent.putExtra("firstname", s.getFName());
        intent.putExtra("lastname", s.getLName());
        intent.putExtra("email", s.getEmail());
        intent.putExtra("password", s.getPassword());
        startActivityForResult(intent, 1);





    }
}
