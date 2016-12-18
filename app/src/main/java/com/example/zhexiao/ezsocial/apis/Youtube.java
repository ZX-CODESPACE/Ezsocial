package com.example.zhexiao.ezsocial.apis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
    private String api_url;
    private Context context;
    private ProgressDialog prog_dialog;
    public ArrayList<SocialData> social_array = new ArrayList<SocialData>();

    public Youtube(Context context, String api_url) {
        this.context = context;
        this.api_url = api_url;
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

                    String name = snippet.getString("title");
                    String description = snippet.getString("description");
                    String channel_id = snippet.getString("channelId");

                    //get profile image
                    JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                    JSONObject thumbnails_default = thumbnails.getJSONObject("default");
                    String profile = thumbnails_default.getString("url");

                    //Log.d("Youtube project", "Youtube obj snippet: " + name + "-----"+ profile );

                    // tmp hash map for single contact
                    SocialData sd = new SocialData();
                    sd.setTitle(name);
                    sd.setDescription(description);
                    sd.setImageUrl(profile);
                    sd.setChannel_id(channel_id);

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
        prog_dialog = new ProgressDialog(context);
        prog_dialog.setMessage("Please wait........");
        prog_dialog.setCancelable(false);
        prog_dialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        // Dismiss the progress dialog
        if (prog_dialog.isShowing()){
            prog_dialog.dismiss();
        }
    }

}
