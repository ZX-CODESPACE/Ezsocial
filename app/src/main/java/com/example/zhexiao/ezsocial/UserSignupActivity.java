package com.example.zhexiao.ezsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserSignupActivity extends AppCompatActivity {
    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;

    SqlHelper db;
    User s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
    }

    public void signUp(View view) {
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

        Intent intent = new Intent(getApplicationContext(),com.example.zhexiao.ezsocial.UserLoginActivity.class);
        startActivityForResult(intent, 1);
    }
}
