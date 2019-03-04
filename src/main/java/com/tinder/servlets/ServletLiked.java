package com.tinder.servlets;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import com.tinder.entity.CartItemExtra;
import com.tinder.services.CartService;
import com.tinder.services.UserService;
import com.tinder.utils.CookieProcessor;
import com.tinder.utils.Freemarker;
import com.tinder.utils.ParameterFromRequest;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ServletLiked extends HttpServlet {
    private final CartService cartService;
    private final UserService userService;
    private final CookieProcessor cp;

    public ServletLiked(CartService cartService, UserService userService, CookieProcessor cp) {
        this.cartService = cartService;
        this.userService = userService;
        this.cp = cp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Freemarker f = new Freemarker();
        int user_id = -1;
        try {
            user_id = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }
        String userName = userService.getUser(user_id).getName();

        List<CartItemExtra> cart = cartService.getByUserLiked(user_id);

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", userName);
        data.put("cart", cart);

        f.render("liked-people-list.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        try {
            int user2_id = pfr.getInt("user2_id");
            resp.sendRedirect("/chat?user2_id=" + user2_id);

        } catch (IllegalStateException e) {
            String logout = pfr.getString("logout");
            cp.deleteCookie(resp);
            resp.sendRedirect("/login");
        }

    }
}
