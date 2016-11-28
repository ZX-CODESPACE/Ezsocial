package com.example.zhexiao.ezsocial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    // api var
    private String apiUrl;
    private Integer apiResultCount = 10;
    private String apiKeyword = "espn";

    // view var
    private ListView trendingListView;
    private CustomListAdapter adapter;
    private ArrayList<SocialData> arrayOfSocials = new ArrayList<SocialData>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // required for using http request
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        // init api information
        initApiInfo();

        // init list view
        trendingListView = (ListView) findViewById(R.id.trending);
        // Attach the data into the adapter
        adapter = new CustomListAdapter(this, arrayOfSocials);
        // Attach the adapter to a ListView
        trendingListView.setAdapter(adapter);

        // get list view data
        new getYoutube().execute();


    }

    private String initApiInfo() {
        apiUrl = "https://www.googleapis.com/youtube/v3/search?safeSearch=moderate&q="+apiKeyword+"&maxResults="+apiResultCount+"&type=channel&part=snippet&key=AIzaSyCXpnwSZGQQRRk8S2O8DJdAmovwwNj7TlY";
        return apiUrl;
    }

    public void submitSearch(View view) {
        EditText keywordObj = (EditText) findViewById(R.id.keyword);
        apiKeyword = keywordObj.getText().toString();

        // clear previous data
        adapter.clear();

        apiUrl = initApiInfo();
        new getYoutube().execute();


    }

    public void userLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), com.example.zhexiao.ezsocial.UserLoginActivity.class);
        startActivity(intent);
    }

    private class getYoutube extends AsyncTask<Void, Void, Void>{
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(apiUrl);

            if (jsonStr != null){
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // get json array node
                    JSONArray youtubeArr = jsonObj.getJSONArray("items");

                    // get all data
                    for (int i=0; i<youtubeArr.length(); i++){
                        JSONObject youtubeObj = youtubeArr.getJSONObject(i);

                        JSONObject snippet = youtubeObj.getJSONObject("snippet");

                        String name = snippet.getString("title");
                        String description = snippet.getString("description");

                        //get profile image
                        JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                        JSONObject thumbnails_default = thumbnails.getJSONObject("default");
                        String profile = thumbnails_default.getString("url");

//                        Log.d(TAG, "Youtube obj snippet: " + name + "-----"+ profile );

                        // tmp hash map for single contact
                        SocialData sd = new SocialData();
                        sd.setTitle(name);
                        sd.setDescription(description);
                        sd.setImageUrl(profile);

                        arrayOfSocials.add(sd);
                    }

                }catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else {
                Log.e(TAG, "Couldn't get json from server.");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
        }

    }
}
