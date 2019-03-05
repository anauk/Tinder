package tinder.com.servlets;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import tinder.com.entity.MessageItemExtra;
import tinder.com.service.CartService;
import tinder.com.service.MessagesService;
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
import java.util.List;

public class ChatServlet extends HttpServlet {
    private final CartService cs;
    private final UserService us;
    private final MessagesService ms;
    private final CookieProcessor cp;

    public ChatServlet(CartService cs, UserService us, MessagesService ms, CookieProcessor cp) {
        this.cs = cs;
        this.us = us;
        this.ms = ms;
        this.cp = cp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        Freemarker f = new Freemarker();
        int id1 = -1;
        try {
            id1 = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }
        String user1Name = us.getUser(id1).getName();
        int id2 = pfr.getInt("id2");

        String user2Name = us.getUser(id2).getName();
        List<MessageItemExtra> chat = ms.getByUser(id1,id2);

        HashMap<String,Object> data = new HashMap<>();
        data.put("id1", id1);
        data.put("id2", id2);
        data.put("name1", user1Name);
        data.put("name2", user2Name);
        data.put("chat", chat);

        f.render("chat.ftl", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int id1 = -1;
        try{
            id1 = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }

        try {
            String message = pfr.getStr("message");
            int id2 = pfr.getInt("id2");
            ms.addMessage(id1, id2, message);
            resp.sendRedirect("/chat?id2=" + id2);
        } catch (IllegalStateException e) {
            String logout = pfr.getStr("logout");
            cp.deleteCookie(resp);
            resp.sendRedirect("/login");
        }

    }
}
