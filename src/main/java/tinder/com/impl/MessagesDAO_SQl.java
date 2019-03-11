package tinder.com.impl;

import tinder.com.Interface.DAO;
import tinder.com.entity.MessageItem;
import tinder.com.exceptions.ElementNotFoundInDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO_SQl implements DAO<MessageItem> {
    private final Connection conn;

    public MessagesDAO_SQl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(MessageItem elem) {
        try {
            PreparedStatement prs = conn.prepareStatement("insert into messages (id1, id2, messages) values(?,?,?)");
            prs.setInt(1, elem.getId1());
            prs.setInt(2, elem.getId2());
            prs.setString(3, elem.getMessage());
            prs.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("something went wrong", e);
        }

    }

    @Override
    public List<MessageItem> all() {
        List<MessageItem> messages = new ArrayList<>();
        try {
            PreparedStatement prs = conn.prepareStatement("select * from messages");
            ResultSet resultSet = prs.executeQuery();
            while (resultSet.next()) {
                messages.add(new MessageItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getString("messages"),
                        resultSet.getTimestamp("time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return messages;
    }


    @Override
    public MessageItem get(int id) {
        try {
            PreparedStatement prs = conn.prepareStatement("select from messages where id=?");
            prs.setInt(1, id);
            ResultSet resultSet = prs.executeQuery();
            if (resultSet.next()) {
                return new MessageItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getString("messages"),
                        resultSet.getTimestamp("time")

                );
            } else {
                throw new ElementNotFoundInDbException("not found");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }


    @Override
    public void remove(int id) {
        try {
            get(id);
            PreparedStatement prs = conn.prepareStatement("select * from messages where id=?");
            prs.setInt(1, id);
            prs.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select count(*) as total from messages");
            while (resultSet.next()) {
                count = resultSet.getInt("total");
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return count == 0;
    }
    public List<MessageItem> getByUser(int id1, int id2){
        ArrayList<MessageItem> messeges = new ArrayList<>();
        try{
            PreparedStatement prs = conn.prepareStatement("select * from messages AS msg join users AS user1 on msg.id1 = user1.id join users AS user2 on msg.id2 = user2.id where (id1=? AND id2=?) or (id1=? AND id2=?) order by time ASC ");
            prs.setInt(1, id1);
            prs.setInt(2, id2);
            prs.setInt(3, id2);
            prs.setInt(4, id1);
            ResultSet resultSet = prs.executeQuery();
            while(resultSet.next()){
                MessageItem item = new MessageItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getString("messages"),
                        resultSet.getTimestamp("time"),
                        resultSet.getString("name"),
                        resultSet.getString("name"),
                        resultSet.getString("photo")
                );
                messeges.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return messeges;
    }
}
