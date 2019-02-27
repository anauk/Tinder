package tinder.com.entity;

import java.sql.Timestamp;

public class MessageItemExtra extends MessageItem {
    private final String user1Name;
    private final String user2Name;
    public MessageItemExtra(int id, int user1_id, int user2_id, String message, Timestamp time, String user1Name, String user2Name) {
        super(id, user1_id, user2_id, message, time);
        this.user1Name = user1Name;
        this.user2Name = user2Name;
    }
}
