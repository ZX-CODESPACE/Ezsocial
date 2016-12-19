package com.example.zhexiao.ezsocial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<SocialData>{
    private Context context;
    private ArrayList<SocialData> social_array;

    private String data_type;

    public CustomListAdapter(Context context, int resource, ArrayList<SocialData> socials, String data_type) {
        super(context, resource, socials);

        this.social_array = socials;
        this.context = context;
        this.data_type = data_type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item
        SocialData sd = social_array.get(position);

        // set view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, null);

        // get view object
        TextView titleObj = (TextView) view.findViewById(R.id.list_item_name);
        TextView descriptionObj = (TextView) view.findViewById(R.id.list_item_description);
        ImageView imageObj = (ImageView) view.findViewById(R.id.list_item_profile);

        // set value
        titleObj.setText(sd.getTitle());
        descriptionObj.setText(sd.getDescription());

        // bind title event
        titleObj.setTag(sd);
        titleObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialData sd = (SocialData) v.getTag();

                if(data_type == "list_channels"){
                    Intent intent = new Intent(context, SocialProfileActivity.class);
                    intent.putExtra("title", sd.getTitle());
                    intent.putExtra("description", sd.getDescription());
                    intent.putExtra("channel_id", sd.getChannel_id());
                    context.startActivities(new Intent[]{intent});
                }else if(data_type == "channel"){
                    Intent intent = new Intent(context, YoutubePlayerActivity.class);
                    intent.putExtra("video_id", sd.getVideo_id());
                    context.startActivities(new Intent[]{intent});
                }
            }
        });

        // set image
        try {
            URL url = new URL(sd.getImageUrl());
            Bitmap bmp;
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageObj.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}