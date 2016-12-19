package com.example.zhexiao.ezsocial;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zhexiao.ezsocial.apis.Youtube;
import com.example.zhexiao.ezsocial.components.ZBottomBar;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    public ArrayList<SocialData> social_array = new ArrayList<SocialData>();

    public static Boolean load_home_page = true;

    // api var
    private String apiUrl;
    private String apiKey = "AIzaSyCXpnwSZGQQRRk8S2O8DJdAmovwwNj7TlY";
    private Integer apiResultCount = 10;
    private String apiKeyword = "";

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

        initBottombar();

        // init api information
        initApi();
    }

    private void initBottombar() {
        BottomBar botBar = (BottomBar) findViewById(R.id.bottomBar);
        botBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                if (tabId == R.id.tab_trending) {
                    Log.d("ZBottombar", "tab trending");
                }else if(tabId == R.id.tab_user){
                    Log.d("ZBottombar", "tab user");
                    setContentView(R.layout.activity_user_login);
                }
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
