package com.example.zhexiao.ezsocial;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initBottombar();
    }


    private void initBottombar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Log.d("test item", String.valueOf(item.getMenuInfo()));

                        switch (item.getItemId()) {
                            case R.id.btn_trending:
                                intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.btn_about:
                                intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.AboutActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.btn_sign_in:
                                intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.UserLoginActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
    }
}
