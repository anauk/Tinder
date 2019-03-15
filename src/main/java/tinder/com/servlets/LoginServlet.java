package tinder.com.servlets;


import tinder.com.entity.CookiesNames;
import tinder.com.utils.Freemarker;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final String cookieName = CookiesNames.TINDER.getName();
    private Freemarker f = new Freemarker();
    HashMap<String,Object> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        f.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String lg = pfr.getStr("email");

        resp.addCookie(new Cookie(cookieName, String.valueOf(lg.hashCode())));
        resp.sendRedirect("/users");
    }
}
