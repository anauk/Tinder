package tinder.com.impl;

import tinder.com.Interface.DAO;
import tinder.com.dataBase.DBConnection;
import tinder.com.entity.User;

import java.sql.Connection;

public class AppUser {
    public static void main(String[] args) {
        Connection conn = new DBConnection().connection();
        DAO<User> dao = new DAOUserSQL(conn);
        /*User u1 = new User(1,"Vita", "asda", "post");
        System.out.println(u1);
        Interface.put(u1);*/
        System.out.println(dao.get(1));
    }
}
