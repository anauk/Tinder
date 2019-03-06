package com.tinder.entity;

import java.sql.Timestamp;

public class MessageItemExtra extends MessageItem {
    private final String user1Name;
    private final String user2Name;
    private final String user1photo;
    public MessageItemExtra(int id, int user1_id, int user2_id, String message, Timestamp time, String user1Name, String user2Name, String user1photo) {
        super(id, user1_id, user2_id, message, time);
        this.user1Name = user1Name;
        this.user2Name = user2Name;
        this.user1photo = user1photo;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public String getUser1photo() {
        return user1photo;
    }
}
