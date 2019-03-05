package tinder.com.entity;

import java.sql.Timestamp;

public class MessageItem {
    private final int id;
    private final int id1;
    private final int id2;
    private final String message;
    private final Timestamp time;

    public MessageItem(int id1, int id2, String message) {
        this.id =-1;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
        this.time = Timestamp.valueOf("0");
    }

    public MessageItem(int id, int id1, int id2, String message, Timestamp time) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
        this.time = time;
    }

    public int getId() {
        return 0;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTime() {
        return time;
    }
}
