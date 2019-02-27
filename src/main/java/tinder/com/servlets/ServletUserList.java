package tinder.com.servlets;


import tinder.com.services.CartService;
import tinder.com.services.MessagesService;
import tinder.com.utils.CookieProcessor;
import tinder.com.utils.Freemarker;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;



public class ServletUserList extends ServletRoot {

    private final MessagesService messagesService;
    private final CartService cartService;
    private final CookieProcessor cp;

    public ServletUserList(MessagesService messagesService, CartService cartService, CookieProcessor cp) {
        this.messagesService = messagesService;
        this.cartService = cartService;
        this.cp = cp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Freemarker f = new Freemarker();

        HashMap<String, Object> data = new HashMap<>();
//        data.put();

        f.render("like-page.ftl", data, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);


        doGet(req, resp);
    }

}

