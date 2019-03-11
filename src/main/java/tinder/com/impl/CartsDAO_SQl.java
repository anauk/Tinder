package tinder.com.impl;

import tinder.com.Interface.DAO;
import tinder.com.entity.CartItem;
import tinder.com.entity.CartItemExtra;
import tinder.com.exceptions.ElementNotFoundInDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartsDAO_SQl implements DAO<CartItem> {
    private final Connection conn;

    public CartsDAO_SQl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CartItem get(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from liked where id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getBoolean("sympathy")
                );
            } else {
                throw new ElementNotFoundInDbException("");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override
    public List<CartItem> all() {
        List<CartItem> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from liked")){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new CartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getBoolean("sympathy")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Something went wrong");
        }
        return list;
    }


    public List<CartItemExtra> getByUserAll(int id1) {
        ArrayList<CartItemExtra> cartItems = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("select*from liked join users on liked.id2 = users.id where id1=?")) {
            ps.setInt(1, id1);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                CartItemExtra item = new CartItemExtra(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getBoolean("sumpathy"),
                        resultSet.getString("name"),
                        resultSet.getString("photo"),
                        resultSet.getString("occupation")
                );
                cartItems.add(item);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return cartItems;
    }


    public List<CartItemExtra> getByUserLiked(int id) {
        ArrayList<CartItemExtra> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from liked join users on liked.id2 = users.id where id1=? and sumpathy = true")){
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                CartItemExtra cie = new CartItemExtra(
                        resultSet.getInt("id"),
                        resultSet.getInt("id1"),
                        resultSet.getInt("id2"),
                        resultSet.getBoolean("sumpathy"),
                        resultSet.getString("name"),
                        resultSet.getString("occupation"),
                        resultSet.getString("photo")
                );
                list.add(cie);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return list;
    }

    @Override
    public void add(CartItem elem) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into liked(id1, id2, sumpathy) values (?,?,?)");
            ps.setInt(1, elem.getId1());
            ps.setInt(2, elem.getId2());
            ps.setBoolean(3, elem.isSumpathy());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("something went wrong", e);
        }

    }

    @Override
    public void remove(int id) {
        get(id);
        try(PreparedStatement ps = conn.prepareStatement("delete * from liked where id=?")){
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }

    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try(Statement st = conn.createStatement()){
            ResultSet resultSet = st.executeQuery("select count(*) AS count from liked");
            while(resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return count == 0;
    }
    public void setSympathy(boolean sympathy, int id){
        try(PreparedStatement ps = conn.prepareStatement("UPDATE liked set sumpathy =? where id=?")){
            ps.setBoolean(1, sympathy);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }
}
