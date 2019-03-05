package tinder.com.entity;

import java.sql.Timestamp;

public class MessageItemExtra extends MessageItem{
    private final String name1;
    private final String name2;

    public MessageItemExtra(int id, int id1, int id2, String message, Timestamp time, String string, String name1, String name2) {
        super(id, id1, id2, message, time);
        this.name1 = name1;
        this.name2 = name2;
    }
}
