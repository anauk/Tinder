package tinder.com.servlets;

import tinder.com.Interface.UserStorage;
import tinder.com.freemarker.Freemarker;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {
    private final String COOKIE_NAME=CookiesName.TINDER.getName();
    private final UserStorage security;
    private Connection conn;
    private Freemarker f;
    private final String lg = "name";
    private final String ps = "password";
    public LoginServlet(UserStorage security, Connection conn, Freemarker f) {
        this.security = security;
        this.conn=conn;
        this.f=f;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Files.copy(Paths.get("./src/main/resources/templates/login.ftl"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addCookie(new Cookie(COOKIE_NAME, String.valueOf(lg.hashCode())));
        security.register(COOKIE_NAME,String.valueOf(lg.hashCode()));
        resp.sendRedirect("/users");
    }
}
