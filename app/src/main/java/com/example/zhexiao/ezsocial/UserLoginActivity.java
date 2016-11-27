package com.example.zhexiao.ezsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class UserLoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }

    public void logIn(View view) {
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);

        SqlHelper db = new SqlHelper(this);
        User user = new User();
        db.getAllItems(email.getText().toString());

        List<User> userList = db.getAllItems(email.getText().toString());

        String userPassword = "";
        for(User u : userList){
            userPassword = u.password;
        }


        if(password.getText().toString().equals(userPassword) && !(password.getText().toString() == ""))
        {
            Intent intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.MainActivity.class);
            intent.putExtra("status", "Logged in successfully!");
            startActivityForResult(intent, 1);
        }
        else
        {
            TextView tv = (TextView)findViewById(R.id.tvDisplay);
            tv.setText("Email/password does not match our records.  Please try again.");
        }
    }

    public void clickRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.UserSignupActivity.class);
        startActivity(intent);
    }
}
