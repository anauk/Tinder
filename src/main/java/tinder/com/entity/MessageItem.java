package tinder.com.entity;

import java.sql.Timestamp;

public class MessageItem {
    private int id;
    private int id1;
    private int id2;
    private String message;
    private Timestamp time;
    private String user1Name;
    private String user2Name;
    private String user1photo;


    public MessageItem(int id1, int id2, String message) {
        this.id =-1;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
        this.time = Timestamp.valueOf("2000-01-01 00:00:00");
    }

    public MessageItem(int id, int id1, int id2, String message, Timestamp time) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
        this.time = time;
    }
    public MessageItem(int id, int id1, int id2, String message, Timestamp time, String user1Name, String user2Name, String user1photo) {
        this(id, id1, id2, message, time);
        this.user1Name = user1Name;
        this.user2Name = user2Name;
        this.user1photo = user1photo;
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

    public String getUser1Name() {
        return user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public String getUser1photo() {
        return user1photo;
    }

    @Override
    public String toString() {
        return "MessageItem{" +
                "id=" + id +
                ", id1=" + id1 +
                ", id2=" + id2 +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
