package tinder.com.impl;

import tinder.com.Interface.DAO;
import tinder.com.entity.MessageItem;
import tinder.com.entity.User;
import tinder.com.exceptions.ElementNotFoundInDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_SQl implements DAO<User> {
    private final Connection conn;

    public UserDAO_SQl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User get(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from public.users where id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("occupation"),
                        resultSet.getString("photo")
                );
            } else {
                throw new ElementNotFoundInDbException("Element empty!");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong", e);
        }
    }

    @Override
    public List<User> all() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from public.users");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String oc = resultSet.getString("occupation");
                String photo = resultSet.getString("photo");
                User user = new User(name, login, password, oc, photo);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("smth with all table wrong", e);
        }
        return list;
    }

    @Override
    public void add(User user) {
        try {
            get(user.getId());
            throw new IllegalArgumentException("The user with this login is already exist");
        } catch (ElementNotFoundInDbException ex) {
            try {
                PreparedStatement ps = conn.prepareStatement("insert into users(id, name, login, password, occupation, photo) values (?,?,?,?,?,?)");
                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getOccupation());
                ps.setString(6, user.getPhoto());
                ps.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException("something went wrong", e);
            }
        }
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong", e);
        }
    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try {
            Statement stmt = conn.createStatement();
            String query = "select count(*) as count from users";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                count = resultSet.getInt("count");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return count == 0;
    }
}
