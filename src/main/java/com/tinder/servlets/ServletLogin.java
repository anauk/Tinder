package com.tinder.servlets;

import com.tinder.utils.Freemarker;
import com.tinder.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ServletLogin extends ServletRoot {

    private final String cookieName = CookiesNames.TINDER.getName();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ParameterFromRequest pfr = new ParameterFromRequest(req);
        Freemarker f = new Freemarker();
        String error = pfr.getString("error");
        HashMap<String, Object> data = new HashMap<>();
        data.put("error", error);
        f.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String login = pfr.getString("login");

        resp.addCookie(new Cookie(cookieName, String.valueOf(login.hashCode())));
        resp.sendRedirect("/users");
    }
}
