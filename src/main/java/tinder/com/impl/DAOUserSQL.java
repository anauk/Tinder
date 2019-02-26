package tinder.com.impl;

import tinder.com.Interface.DAO;
import tinder.com.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DAOUserSQL implements DAO<User> {
    private final Connection conn;

    public DAOUserSQL(Connection conn) {
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
                        resultSet.getString("password")
                );
            } else {
                throw new NullPointerException("User is ...");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong", e);
        }
    }

    @Override
    public void put(User entity) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into public.users(id,name, login, password) values (?,?,?,?)");
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getLogin());
            ps.setString(4, entity.getPassword());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Something went wrong with put user");
        }
    }

    @Override
    public Collection<User> all() {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from public.users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(id, name, login, password);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth with all table wrong", e);
        }
        return list;
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
}
