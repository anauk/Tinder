package com.tinder.DAO;
import com.tinder.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// class for methods involving several tables in db
public class DAOextra_SQL {
    private final Connection conn;


    public DAOextra_SQL(Connection conn) {
        this.conn = conn;
    }


    public List<User> getByUser(int user_id, int user_per_page) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "select * from ag_tinder_users AS users where (users.id != ?) AND users.id NOT in (select liked.user2_id from ag_tinder_liked liked where liked.user1_id = ?) order by users.id ASC LIMIT ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, user_id);
            stm.setInt(2, user_id);
            stm.setInt(3, user_per_page);

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
}
