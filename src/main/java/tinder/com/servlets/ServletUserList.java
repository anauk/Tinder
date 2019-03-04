package tinder.com.servlets;


import com.sun.deploy.net.cookie.CookieUnavailableException;
import tinder.com.entity.CartItem;
import tinder.com.entity.CartItemExtra;
import tinder.com.entity.User;
import tinder.com.exceptions.NoNewUsersException;
import tinder.com.services.CartService;
import tinder.com.services.MessagesService;
import tinder.com.services.UserService;
import tinder.com.utils.CookieProcessor;
import tinder.com.utils.Freemarker;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class ServletUserList extends ServletRoot {

    private final UserService us;
    private final CartService cs;
    private final CookieProcessor cp;

    public ServletUserList(UserService userService, CartService cartService, CookieProcessor cp) {
        this.us = userService;
        this.cs = cartService;
        this.cp = cp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Freemarker f = new Freemarker();
        HashMap<String, Object> data = new HashMap<>();
        int id = -1;
        try {
            id = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> This case should never happen. You have tried to get user-list in illegal way! %n %s </a></html>", e.getMessage());
        }

        try {
            User user = us.getUserUnchecked(id, 1);
            data.put("style", "");
            data.put("user_photo", user.getPhoto());
            data.put("user_name", user.getName());
            data.put("user_id", user.getId());
        } catch (NoNewUsersException e) {
            data.put("style", "style='display: none'");
            data.put("user_photo", "");
            data.put("user_name", e.getMessage());
            data.put("user_id", "");
        }
        f.render("like-page.ftl", data, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String choice = pfr.getString("choice");
        int user1_id = -1;
        try {
            user1_id = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> This case should never happen. You have tried to get user-list in illegal way! %n %s </a></html>", e.getMessage());
        }
        int user2_id = -1;
        try {
            user2_id = pfr.getInt("user_id");
        } catch (IllegalStateException e) {
            System.out.println("It looks like user wants to log out");
        }
        switch (choice) {
            case "like":
                cs.add(new CartItem(user1_id, user2_id, true));
                break;
            case "dislike":
                cs.add(new CartItem(user1_id, user2_id, false));
                break;
            case "logout":
                cp.deleteCookie(resp);
                resp.sendRedirect("/login");
                break;
            default:
                throw new IllegalArgumentException("wrong values");
        }
        doGet(req, resp);
    }

}

