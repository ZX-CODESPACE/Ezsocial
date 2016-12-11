package com.example.zhexiao.ezsocial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zhexiao.ezsocial.apis.Youtube;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    // api var
    private String apiUrl;
    private Integer apiResultCount = 10;
    private String apiKeyword = "espn";

    // view var
    private ListView trendingListView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // required for using http request
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        // init api information
        initApi();
    }

    private void initApi() {
        apiUrl = "https://www.googleapis.com/youtube/v3/search?safeSearch=moderate&q="+apiKeyword+"&maxResults="+apiResultCount+"&type=channel&part=snippet&key=AIzaSyCXpnwSZGQQRRk8S2O8DJdAmovwwNj7TlY";

        // get json data from api
        Youtube yt = new Youtube(this, apiUrl);
        yt.execute();

        // get list view object
        trendingListView = (ListView) findViewById(R.id.trending);

        // bind social data with adapter
        adapter = new CustomListAdapter(this, yt.social_array);
        trendingListView.setAdapter(adapter);
    }

    public void submitSearch(View view) {
        EditText keywordObj = (EditText) findViewById(R.id.keyword);
        apiKeyword = keywordObj.getText().toString();

        // clear previous data
        adapter.clear();

        initApi();
    }

    public void userLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.UserLoginActivity.class);
        startActivity(intent);
    }
}
