package com.example.zhexiao.ezsocial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

class CustomListAdapter extends ArrayAdapter<SocialData>{

    public CustomListAdapter(Context context, ArrayList<SocialData> socials) {
        super(context, 0, socials);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SocialData sd = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Lookup view for data population
        TextView titleObj = (TextView) convertView.findViewById(R.id.list_item_name);
        TextView descriptionObj = (TextView) convertView.findViewById(R.id.list_item_description);
        ImageView imageObj = (ImageView) convertView.findViewById(R.id.list_item_profile);

        // Populate the data into the template view using the data object
        titleObj.setText(sd.getTitle());
        descriptionObj.setText(sd.getDescription());

        // image data process
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

        // Return the completed view to render on screen
        return convertView;
    }
}