package tinder.com.DAO;

import tinder.com.Interface.DAO;
import tinder.com.entity.CartItem;
import tinder.com.entity.CartItemExtra;
import tinder.com.exceptions.ElementNotFoundInDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartsDAO_SQL implements DAO<CartItem> {
    private final Connection conn;

    public CartsDAO_SQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(CartItem elem) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into ag_tinder_liked(user1_id, user2_id, sympathy) values (?, ?, ?)");
            stmt.setInt(1, elem.getUser1_id());
            stmt.setInt(2, elem.getUser2_id());
            stmt.setBoolean(3, elem.getSympathy());
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("something went wrong", e);
        }
    }

    @Override
    public List<CartItem> getAll() {
        List<CartItem> goods = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ag_tinder_liked";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();
            while (rSet.next()) {
                CartItem item = new CartItem(
                        rSet.getInt("id"),
                        rSet.getInt("user1_id"),
                        rSet.getInt("user2_id"),
                        rSet.getBoolean("sympathy")
                );
                goods.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return goods;
    }

    @Override
    public CartItem get(int id) {  // TODO перехват ElementNotFoundInDbException
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from ag_tinder_liked where id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new CartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("user1_id"),
                        resultSet.getInt("user2_id"),
                        resultSet.getBoolean("sympathy")
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
            PreparedStatement stmt = conn.prepareStatement("delete from ag_tinder_liked where id = ?");
            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try (Statement stmt = conn.createStatement()) {
            String query = "select count(*) from ag_tinder_liked";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return count == 0;
    }

    // TODO перехват IllegalArgumentException
    public void setSympathy(boolean sympathy, int id) {

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE ag_tinder_liked SET sympathy = ? WHERE id = ?")) {
            stmt.setBoolean(1, sympathy);
            stmt.setInt(2, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }


    public List<CartItemExtra> getByUser(int user1_id) {
        ArrayList<CartItemExtra> cartItems = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement("select * from ag_tinder_liked join ag_tinder_users on ag_tinder_liked.user2_id = ag_tinder_users.id where user1_id =?")) {
            stmt.setInt(1, user1_id);
            ResultSet rSet = stmt.executeQuery();
            while (rSet.next()) {
                CartItemExtra item = new CartItemExtra(
                        rSet.getInt("id"),
                        rSet.getInt("user1_id"),
                        rSet.getInt("user2_id"),
                        rSet.getBoolean("sympathy"),
                        rSet.getString("name"),
                        rSet.getString("occupation"),
                        rSet.getString("photo")
                );
                cartItems.add(item);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return cartItems;
    }
}
