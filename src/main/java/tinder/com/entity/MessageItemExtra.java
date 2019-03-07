package tinder.com.entity;

import java.sql.Timestamp;

public class MessageItemExtra extends MessageItem{
    private String user1Name;
    private String user2Name;
    private String user1photo;

    public MessageItemExtra(int id, int id1, int id2, String message, Timestamp time, String user1Name, String user2Name, String user1photo) {
        super(id, id1, id2, message, time);
        this.user1Name = user1Name;
        this.user2Name = user2Name;
        this.user1photo = user1photo;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    public String getUser1photo() {
        return user1photo;
    }

    public void setUser1photo(String user1photo) {
        this.user1photo = user1photo;
    }

    @Override
    public String toString() {
        return "MessageItemExtra{" +
                "user1Name='" + user1Name + '\'' +
                ", user2Name='" + user2Name + '\'' +
                ", user1photo='" + user1photo + '\'' +
                '}';
    }
}
