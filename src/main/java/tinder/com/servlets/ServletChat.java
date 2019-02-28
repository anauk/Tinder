package tinder.com.servlets;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import tinder.com.entity.MessageItemExtra;
import tinder.com.services.CartService;
import tinder.com.services.MessagesService;
import tinder.com.services.UserService;
import tinder.com.utils.CookieProcessor;
import tinder.com.utils.Freemarker;

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
        Freemarker f = new Freemarker();
        int user1_id = -1;
        try {
            user1_id = Integer.parseInt(cp.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }
        String user1Name = us.getUser(user1_id).getName();
        String u2_id = req.getParameter("user2_id");

        int user2_id = Integer.parseInt(u2_id);
        String user2Name = us.getUser(user2_id).getName();
        System.out.println(user1Name + user2Name);

//        List<MessageItemExtra> chat = ms.getByUser(user1_id, -2047788770);

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", user1Name);
//        data.put("id", user2_id);
//        data.put("chat", chat);

        f.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
