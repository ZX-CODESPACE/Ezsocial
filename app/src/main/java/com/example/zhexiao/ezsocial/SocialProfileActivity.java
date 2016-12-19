package com.example.zhexiao.ezsocial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhexiao.ezsocial.apis.Youtube;

public class SocialProfileActivity extends AppCompatActivity {
    private String api_url;
    private String api_key = "AIzaSyDndAMmVKHXfORaPUHBCI9Bqb7ENIqKRMc";
    private Integer api_result_count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String description = extras.getString("description");
            String channel_id = extras.getString("channel_id");

            TextView titleObj = (TextView) findViewById(R.id.s_p_title);
            TextView descriptionObj = (TextView) findViewById(R.id.s_p_description);

            titleObj.setText(title);
            descriptionObj.setText(description);

            api_url = "https://www.googleapis.com/youtube/v3/search?type=video&order=date&channelId="+channel_id+"&maxResults="+api_result_count+"&part=snippet&key="+api_key;

            // get json data from api
            ListView channel_data_view = (ListView) findViewById(R.id.channel_data);
            Youtube yt = new Youtube(this, api_url, channel_data_view, "channel");
            yt.execute();
        }
    }


}
