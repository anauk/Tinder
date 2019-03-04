package com.tinder.servlets;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import com.tinder.entity.MessageItemExtra;
import com.tinder.utils.ParameterFromRequest;
import com.tinder.services.CartService;
import com.tinder.services.MessagesService;
import com.tinder.services.UserService;
import com.tinder.utils.CookieProcessor;
import com.tinder.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ServletChat extends ServletRoot {
    private final CartService cs;
    private final UserService us;
    private final MessagesService ms;
    private final CookieProcessor cp;


    public ServletChat(CartService cs, UserService us, MessagesService ms, CookieProcessor cp) {
        this.cs = cs;
        this.us = us;
        this.ms = ms;
        this.cp = cp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        Freemarker f = new Freemarker();
        int user1_id = -1;
        try {
            user1_id = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }
        String user1Name = us.getUser(user1_id).getName();

        int user2_id = pfr.getInt("user2_id");
        String user2Name = us.getUser(user2_id).getName();

        List<MessageItemExtra> chat = ms.getByUser(user1_id, user2_id);

        HashMap<String, Object> data = new HashMap<>();
        data.put("user1_id", user1_id);
        data.put("user2_id", user2_id);
        data.put("name1", user1Name);
        data.put("name2", user2Name);
        data.put("chat", chat);

        f.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int user1_id = -1;
        try {
            user1_id = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }

        try {
            String message = pfr.getString("message");
            int user2_id = pfr.getInt("user2_id");
            ms.addMessage(user1_id, user2_id, message);
            resp.sendRedirect("/chat?user2_id=" + user2_id);

        } catch (IllegalStateException e) {
            String logout = pfr.getString("logout");
            cp.deleteCookie(resp);
            resp.sendRedirect("/login");
        }
    }
}
