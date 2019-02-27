package tinder.com.DAO;

import tinder.com.Interface.DAO;
import tinder.com.entity.MessageItem;
import tinder.com.entity.MessageItemExtra;
import tinder.com.exceptions.ElementNotFoundInDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO_SQL implements DAO<MessageItem> {
    private final Connection conn;

    public MessagesDAO_SQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(MessageItem item) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into alex_grig_messages(user1_id, user2_id, message) values (?, ?, ?)");
            stmt.setInt(1, item.getUser1_id());
            stmt.setInt(2, item.getUser2_id());
            stmt.setString(3, item.getMessage());
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("something went wrong", e);
        }
    }

    @Override
    public List<MessageItem> getAll() {
        List<MessageItem> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM alex_grig_messages";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();
            while (rSet.next()) {
                MessageItem item = new MessageItem(
                        rSet.getInt("id"),
                        rSet.getInt("user1_id"),
                        rSet.getInt("user2_id"),
                        rSet.getString("message"),
                        rSet.getTimestamp("time")
                );
                messages.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return messages;
    }

    @Override // TODO перехват ElementNotFoundInDbException
    public MessageItem get(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from alex_grig_messages where id = ?");
            stmt.setInt(1, id);
            ResultSet rSet = stmt.executeQuery();
            if (rSet.next()) {
                return new MessageItem(
                        rSet.getInt("id"),
                        rSet.getInt("user1_id"),
                        rSet.getInt("user2_id"),
                        rSet.getString("message"),
                        rSet.getTimestamp("time")
                );
            } else {
                throw new ElementNotFoundInDbException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override // TODO перехват ElementNotFoundInDbException
    public void remove(int id) {
        try {
            get(id);
            PreparedStatement stmt = conn.prepareStatement("delete from alex_grig_messages where id = ?");
            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try {
            Statement stmt = conn.createStatement();
            String query = "select count(*) from alex_grig_messages";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                count = resultSet.getInt("COUNT(*)");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return count == 0;
    }

    // TODO Изменить запрос - добавить обратный порядок юзеров, сортировка по времени!!!
    public List<MessageItemExtra> getByUser(int user1_id, int user2_id) {
        List<MessageItemExtra> messages = new ArrayList<>();
        try {
            String sql = "select * from alex_grig_messages join alex_grig_users on alex_grig_messages.user1_id = alex_grig_users.userId join alex_grig_users on alex_grig_messages.user2_id = alex_grig_users.userId where user1_id =? user2_id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, user1_id);
            stm.setInt(2, user2_id);
            ResultSet rSet = stm.executeQuery();
            while (rSet.next()) {
                MessageItemExtra item = new MessageItemExtra(
                        rSet.getInt("id"),
                        rSet.getInt("user1_id"),
                        rSet.getInt("user2_id"),
                        rSet.getString("message"),
                        rSet.getTimestamp("time"),
                        rSet.getString("name"), // TODO одинаковый запрос!!!
                        rSet.getString("name")    // TODO одинаковый запрос!!!
                );
                messages.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return messages;
    }
}
