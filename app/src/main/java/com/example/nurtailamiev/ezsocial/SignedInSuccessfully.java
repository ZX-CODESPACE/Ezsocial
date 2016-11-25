package com.example.nurtailamiev.ezsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class SignedInSuccessfully extends AppCompatActivity {

    String status;
    TextView tv;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in_successfully);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(getDrawable(R.drawable.logo));


        tv = (TextView) findViewById(R.id.tv_Display);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getString("status");
        }

        tv.setText(status);
    }
}
