package com.gdeer.projectest.model;

import android.graphics.Bitmap;

/**
 * Created by Gdeer on 2016/1/2.
 */
public class ItemFun {
    private Bitmap publisherIcon;
    private String publisherName;
    private String publishTime;
    private String publishContent;

    public Bitmap getPublisherIcon() {
        return publisherIcon;
    }

    public void setPublisherIcon(Bitmap publisherIcon) {
        this.publisherIcon = publisherIcon;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent;
    }
}
