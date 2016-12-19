package com.example.zhexiao.ezsocial;

import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zhexiao.ezsocial.apis.Youtube;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    public ArrayList<SocialData> social_array = new ArrayList<SocialData>();

    public static Boolean load_home_page = true;

    // api var
    private String apiUrl;
    private String apiKey = "AIzaSyDndAMmVKHXfORaPUHBCI9Bqb7ENIqKRMc";
    private Integer apiResultCount = 10;
    private String apiKeyword = "";

    // view var
    private ListView trendingListView;
    private CustomListAdapter adapter;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // required for using http request
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        initBottombar();

        // init api information
        initApi();
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


    private void initApi() {
        if(apiKeyword == ""){
            apiUrl = "https://www.googleapis.com/youtube/v3/search?chart=mostPopular&maxResults="+apiResultCount+"&type=channel&part=snippet&key="+apiKey;
        }else{
            apiUrl = "https://www.googleapis.com/youtube/v3/search?q="+apiKeyword+"&maxResults="+apiResultCount+"&type=channel&part=snippet&key="+apiKey;
        }

        trendingListView = (ListView) findViewById(R.id.trending);
        Youtube yt = new Youtube(this, apiUrl, trendingListView, "list_channels");
        yt.execute();
    }

    public void submitSearch(View view) {
        EditText keywordObj = (EditText) findViewById(R.id.keyword);
        apiKeyword = keywordObj.getText().toString();

        initApi();
    }
}
