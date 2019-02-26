package tinder.com.servlets;

import tinder.com.entity.User;
import tinder.com.freemarker.Freemarker;
import tinder.com.impl.DAOUserSQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
    private Connection conn;
    private Freemarker f;
    private HashMap<String,Object> data = new HashMap<>();

    public UsersServlet(Connection conn, Freemarker f) {
        this.conn = conn;
        this.f = f;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new DAOUserSQL(conn).get(1);

        data.put("name1", user.getName());

        f.render("like-page.ftl", data, resp);
    }
}
