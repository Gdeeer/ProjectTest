package com.gdeer.projectest.model;

/**
 * Created by Gdeer on 2015/10/15.
 */
public class Announcement {
    private int id;
    private String title;
    private String tag;
    private String count;
    private String describe;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getTag() {
        return tag;
    }
    public String getcount() {
        return count;
    }
    public String getDescribe() {
        return describe;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
