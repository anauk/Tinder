package tinder.com.impl;

import tinder.com.Interface.DAO;
import tinder.com.dataBase.DBConnection;
import tinder.com.entity.User;

import java.sql.Connection;

public class AppUser {
    public static void main(String[] args) {
        Connection conn = new DBConnection().connection();
        DAO<User> dao = new UserDAO_SQl(conn);
        User u1 = new User("Kola", "acvb", "yui", "oc", "photo");
        User u2 = new User("Riwq", "op", "yhjl", "oc", "photo");
        System.out.println(u1);
        dao.add(u1);
        dao.add(u2);
        System.out.println(dao.get(1));
    }
}
