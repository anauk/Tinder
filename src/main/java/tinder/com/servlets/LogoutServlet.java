package tinder.com.servlets;

import tinder.com.entity.CookiesNames;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private final String cookieName = CookiesNames.TINDER.getName();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("/login");
    }
}