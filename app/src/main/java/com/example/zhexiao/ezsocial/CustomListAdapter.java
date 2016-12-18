package com.example.zhexiao.ezsocial;

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

class CustomListAdapter extends ArrayAdapter<SocialData>{
    private Context ctx;

    // View lookup cache
    private static class ViewHolder {
        TextView titleObj;
        TextView descriptionObj;
        ImageView imageObj;
    }


    public CustomListAdapter(Context context, ArrayList<SocialData> socials) {
        super(context, R.layout.list_item, socials);
        ctx = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SocialData sd = getItem(position);

        // view lookup cache stored in tag
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            viewHolder.titleObj = (TextView) convertView.findViewById(R.id.list_item_name);
            viewHolder.descriptionObj = (TextView) convertView.findViewById(R.id.list_item_description);
            viewHolder.imageObj = (ImageView) convertView.findViewById(R.id.list_item_profile);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        }else{
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.titleObj.setText(sd.getTitle());
        viewHolder.descriptionObj.setText(sd.getDescription());

        viewHolder.titleObj.setTag(sd);
        viewHolder.titleObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialData sd = (SocialData) v.getTag();

                Intent intent = new Intent(ctx, SocialProfileActivity.class);
                intent.putExtra("title", sd.getTitle());
                intent.putExtra("description", sd.getDescription());
                intent.putExtra("channel_id", sd.getChannel_id());
                ctx.startActivities(new Intent[]{intent});
            }
        });

        // image data process
        try {
            URL url = new URL(sd.getImageUrl());
            Bitmap bmp;
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            viewHolder.imageObj.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the completed view to render on screen
        return convertView;
    }
}