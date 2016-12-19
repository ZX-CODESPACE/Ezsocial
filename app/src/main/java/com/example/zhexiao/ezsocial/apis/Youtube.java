package com.example.zhexiao.ezsocial.apis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.zhexiao.ezsocial.CustomListAdapter;
import com.example.zhexiao.ezsocial.HttpHandler;
import com.example.zhexiao.ezsocial.SocialData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by zhexiao on 12/8/16.
 */

public class Youtube extends AsyncTask<Void, Void, Void> {
    private Context context;
    private String api_url;
    private ListView list_view;
    private String data_type;

    public ArrayList<SocialData> social_array = new ArrayList<SocialData>();

    protected ProgressDialog pro_dialog;


    public Youtube(Context context, String api_url, ListView list_view, String data_type) {
        this.context = context;
        this.api_url = api_url;
        this.list_view = list_view;
        this.data_type = data_type;
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(api_url);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // get json array node
                JSONArray youtubeArr = jsonObj.getJSONArray("items");

                // get all data
                for (int i = 0; i < youtubeArr.length(); i++) {
                    JSONObject youtubeObj = youtubeArr.getJSONObject(i);

                    JSONObject snippet = youtubeObj.getJSONObject("snippet");

                    // get snippet data
                    String name = snippet.getString("title");
                    String description = snippet.getString("description");
                    String channel_id = snippet.getString("channelId");

                    // get video id
                    String video_id;
                    if(data_type == "channel"){
                        JSONObject video_id_json = youtubeObj.getJSONObject("id");
                        video_id = video_id_json.getString("videoId");
                    }else{
                        video_id = null;
                    }

                    //get profile image
                    JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                    JSONObject thumbnails_default = thumbnails.getJSONObject("default");
                    String profile = thumbnails_default.getString("url");

                    //Log.d("Youtube project", "Youtube obj snippet: " + name + "-----"+ profile );

                    // save object data
                    SocialData sd = new SocialData();
                    sd.setTitle(name);
                    sd.setDescription(description);
                    sd.setChannel_id(channel_id);
                    sd.setVideo_id(video_id);
                    sd.setImageUrl(profile);

                    social_array.add(sd);
                }
            } catch (final JSONException e) {

            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Showing progress dialog
        pro_dialog = new ProgressDialog(context);
        pro_dialog.setMessage("Please wait........");
        pro_dialog.setCancelable(false);
        pro_dialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        // Dismiss the progress dialog
        if (pro_dialog.isShowing()){
            pro_dialog.dismiss();
        }

        CustomListAdapter adapter = new CustomListAdapter(context, 0, social_array, data_type);
        list_view.setAdapter(adapter);
    }

}
