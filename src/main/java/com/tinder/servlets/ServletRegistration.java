package com.tinder.servlets;

import com.tinder.entity.User;
import com.tinder.services.UserService;
import com.tinder.utils.Freemarker;
import com.tinder.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ServletRegistration extends ServletRoot {
    private UserService userService;
    private final String cookieName = CookiesNames.TINDER.getName();


    public ServletRegistration(UserService userService) {
        this.userService = userService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Files.copy(Paths.get("./src/main/resources/templates/registration.ftl"), resp.getOutputStream());
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        Freemarker f = new Freemarker();
        String error = pfr.getString("error");
        HashMap<String, Object> data = new HashMap<>();
        data.put("error", error);
        f.render("registration.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getString("name").trim();
        String occupation = pfr.getString("occupation").trim();
        String photo = pfr.getString("photo").trim();
        String login = pfr.getString("login").trim();
        String password = pfr.getString("password").trim();

        User user = new User(name, occupation, login, password, photo);
        userService.addUser(user);
        resp.addCookie(new Cookie(cookieName, String.valueOf(login.hashCode())));

        resp.sendRedirect("/users");
    }
}
