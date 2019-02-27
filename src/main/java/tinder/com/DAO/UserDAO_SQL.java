package tinder.com.DAO;


import tinder.com.Interface.DAO;
import tinder.com.entity.User;
import tinder.com.exceptions.ElementNotFoundInDbException;
import tinder.com.exceptions.LoginMatchException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_SQL implements DAO<User> {
    private final Connection conn;

    public UserDAO_SQL(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void add(User user) { // TODO перехват LoginMatchException
        try {
            get(user.getId());
            throw new LoginMatchException("The user with this login is already exist");
        } catch (ElementNotFoundInDbException ex) {
            try {
                PreparedStatement stmt = conn.prepareStatement("insert into ag_tinder_users(id, name, occupation, login, password , photo) values (?, ?, ?, ?, ?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setString(2, user.getName());
                stmt.setString(3, user.getOccupation());
                stmt.setString(4, user.getLogin());
                stmt.setString(5, user.getPassword());
                stmt.setString(6, user.getPhoto());
                stmt.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException("something went wrong", e);
            }
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ag_tinder_users";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();
            while (rSet.next()) {
                User user = new User(
                        rSet.getString("name"),
                        rSet.getString("occupation"),
                        rSet.getString("login"),
                        rSet.getString("password"),
                        rSet.getString("photo")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return users;
    }

    @Override
    public User get(int userId) { // TODO перехват ElementNotFoundInDbException
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from ag_tinder_users where id = ?");
            stmt.setInt(1, userId);
            ResultSet rSet = stmt.executeQuery();
            if (rSet.next()) {
                return new User(
                        rSet.getString("name"),
                        rSet.getString("occupation"),
                        rSet.getString("login"),
                        rSet.getString("password"),
                        rSet.getString("photo")
                );
            } else {
                throw new ElementNotFoundInDbException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override
    public void remove(int id) { // TODO перехват ElementNotFoundInDbException
        try {
            get(id);
            PreparedStatement stmt = conn.prepareStatement("DELETE from ag_tinder_users where id = ?");
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
            String query = "select count(*) from ag_tinder_users";
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
}
