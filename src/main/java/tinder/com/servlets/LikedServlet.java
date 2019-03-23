package tinder.com.servlets;

import tinder.com.entity.CartItemExtra;
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
import java.util.List;

public class LikedServlet extends HttpServlet {
    private final CartService cartService;
    private final UserService userService;
    private final CookieProcessor cp;

    public LikedServlet(CartService cartService, UserService userService, CookieProcessor cp) {
        this.cartService = cartService;
        this.userService = userService;
        this.cp = cp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Freemarker f = new Freemarker();
        int id = -1;
        try{
            id = Integer.parseInt(cp.getValue(req));
        } catch (tinder.com.exceptions.CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/login\"> You have tried to enter to liked-page in illegal way! %n %s </a></html>", e.getMessage());
        }
        String userName = userService.getUser(id).getName();

        List<CartItemExtra> cart = cartService.getByUserLiked(id);
        System.out.println(cart.size());
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", userName);
        System.out.println(cart);
        data.put("cart", cart);
        f.render("liked-people-list.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        try {
            int id2 = pfr.getInt("id2");
            resp.sendRedirect("/chat?id2="+id2);
        } catch (IllegalStateException e){
            String logout = pfr.getStr("logout");
            cp.deleteCookie(resp);
            resp.sendRedirect("/login");
        }
    }
}
