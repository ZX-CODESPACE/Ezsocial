package com.example.zhexiao.ezsocial;

/**
 * Created by zhexiao on 11/27/16.
 */

public class SocialData {
    private String imageUrl;
    private String title;
    private String description;

    public SocialData() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
