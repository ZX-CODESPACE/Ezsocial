package com.example.nurtailamiev.ezsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class SignUpSuccess extends AppCompatActivity {

    TextView tv;
    String firstname;
    String lastname;
    String email;
    String password;
    String sentence;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_success);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(getDrawable(R.drawable.logo));

        tv = (TextView)findViewById(R.id.tv_signedup);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            firstname = extras.getString("firstname");
            lastname = extras.getString("lastname");
            email = extras.getString("email");
            password = extras.getString("password");

        }

        sentence = "The user's first name is " + firstname + " and last name is " + lastname
                + " and, the user's email address is " + email + " and password is " + password;
        tv.setText(sentence);
    }
}
