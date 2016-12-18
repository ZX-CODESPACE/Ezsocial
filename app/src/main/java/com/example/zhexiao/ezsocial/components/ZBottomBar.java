package com.example.zhexiao.ezsocial.components;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.zhexiao.ezsocial.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by zhexiao on 12/15/16.
 */

public class ZBottomBar {
    private Context ctx;
    private Activity ctx_activity;
    private BottomBar botbar;
    public static Boolean load_home_page = true;

    public ZBottomBar(Context context, BottomBar botbar){
        this.ctx = context;
        this.botbar = botbar;
        this.ctx_activity = (Activity) this.ctx;
    }

    public void init_bottom_bar(){
        this.botbar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                if (tabId == R.id.tab_user) {
                    ctx_activity.setContentView(R.layout.activity_user_login);
                    Log.d("ZBottombar", "tab user");
                }else if(tabId == R.id.tab_trending){
                    ctx_activity.setContentView(R.layout.activity_main);
                    Log.d("ZBottombar", "tab trending");
                }
            }
        });
    }

}
