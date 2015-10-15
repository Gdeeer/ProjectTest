package com.gdeer.projectest.model;

/**
 * Created by Gdeer on 2015/10/15.
 */
public class Announcement {
    private int id;
    private String announcementContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String content) {
        this.announcementContent = content;
    }
}
