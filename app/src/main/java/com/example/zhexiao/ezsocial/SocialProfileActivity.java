package com.example.zhexiao.ezsocial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SocialProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String description = extras.getString("description");

            TextView titleObj = (TextView) findViewById(R.id.s_p_title);
            TextView descriptionObj = (TextView) findViewById(R.id.s_p_description);

            titleObj.setText(title);
            descriptionObj.setText(description);
        }
    }


}
