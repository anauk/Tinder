package tinder.com.servlets;

import tinder.com.entity.CartItem;
import tinder.com.entity.User;
import tinder.com.exceptions.NoNewUsersException;
import tinder.com.service.CartService;
import tinder.com.service.UserService;
import tinder.com.utils.CookieProcessor;
import tinder.com.utils.Freemarker;
import tinder.com.utils.ParameterFromRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
    private final UserService us;
    private final CookieProcessor cp;
    private final CartService cs;


    public UsersServlet(UserService us, CookieProcessor cp, CartService cs) {
        this.us = us;
        this.cp = cp;
        this.cs = cs;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Freemarker f = new Freemarker();
        HashMap<String,Object> data = new HashMap<>();
        int id = -1;
        try {
            try {
                id = Integer.parseInt(cp.getValue(req));
            } catch (tinder.com.exceptions.CookieUnavailableException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> This case should never happen. You have tried to get user-list in illegal way! %n %s </a></html>", e.getMessage());
        }
        try {
            User user = us.getUserUnchecked(id, 1);
            data.put("style", "");
            data.put("photo", user.getPhoto());
            data.put("name1", user.getName());
            data.put("id", user.getId());
        } catch(NoNewUsersException e){
            data.put("style", "style='display: none'");
            data.put("photo", "");
            data.put("name1", e.getMessage());
            data.put("id","");
        }
        f.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String choice = pfr.getStr("choice");
        int id1 = -1;
        try{
            try {
                id1 = Integer.parseInt(cp.getValue(req));
            } catch (tinder.com.exceptions.CookieUnavailableException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e){
            resp.getWriter().printf("<html> <a href=\"/login\"> This case should never happen. You have tried to get user-list in illegal way! %n %s </a></html>", e.getMessage());
        }

        int id2 = -1;
        try {
            id2 = pfr.getInt("id");
        } catch(IllegalStateException e){
            System.out.println("It looks like user wants to log out");
        }
        switch(choice){
            case "like":
                cs.addCart(new CartItem(id1, id2, true));
                break;
            case "dislike":
                cs.addCart(new CartItem(id1, id2, false));
                break;
            case "logout":
                cp.deleteCookie(resp);
                resp.sendRedirect("/login");
                default:
                    throw new IllegalArgumentException("Wrong values");
        }
        doGet(req, resp);

    }
}
