package tinder.com.entity;

import tinder.com.Interface.Identifiable;

import java.sql.Timestamp;

public class MessageItem implements Identifiable {
    private final int id;
    private final int user1_id;
    private final int user2_id;
    private final String message;
    private final Timestamp time;


    public MessageItem(int user1_id, int user2_id, String message) {
        this(-1, user1_id, user2_id, message, Timestamp.valueOf("0"));
    }

    public MessageItem(int id, int user1_id, int user2_id, String message, Timestamp time) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.message = message;
        this.time = time;
    }

    @Override
    public int getId() {
        return 0;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTime() {
        return time;
    }
}
