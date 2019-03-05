package tinder.com.servlets;

import tinder.com.entity.CookiesNames;
import tinder.com.entity.User;
import tinder.com.service.UserService;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthServlet extends HttpServlet {
    private UserService userService;
    private final String cookieName = CookiesNames.TINDER.getName();

    public AuthServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("./src/main/resources/templates/auth.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getStr("name");
        String login = pfr.getStr("email");
        String pas = pfr.getStr("password");
        String oc = pfr.getStr("occupation");
        String photo = pfr.getStr("photo");
        User user = new User(login.hashCode(), name, login, pas, oc, photo);
        userService.addUser(user);
        resp.addCookie(new Cookie(cookieName, String.valueOf(login.hashCode())));

        resp.sendRedirect("/users");
    }
}
