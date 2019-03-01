package tinder.com.servlets;

import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletLogin extends ServletRoot {

    private final String cookieName = CookiesNames.TINDER.getName();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Files.copy(Paths.get("./src/main/resources/templates/login.html"), resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String login = pfr.getString("login");

        resp.addCookie(new Cookie(cookieName, String.valueOf(login.hashCode())));
        resp.sendRedirect("/users");
    }
}
