package com.gdeer.projectest.model;

import android.graphics.Bitmap;

/**
 * Created by Gdeer on 2016/1/2.
 */
public class ItemChat {
    private Bitmap chatIcon;
    private String chatName;
    private String chatTime;
    private String chatLastMessage;

    public Bitmap getChatIcon() {
        return chatIcon;
    }

    public void setChatIcon(Bitmap chatIcon) {
        this.chatIcon = chatIcon;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }

    public String getChatLastMessage() {
        return chatLastMessage;
    }

    public void setChatLastMessage(String chatLastMessage) {
        this.chatLastMessage = chatLastMessage;
    }
}
