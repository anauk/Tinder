package tinder.com.servlets;

import tinder.com.entity.User;
import tinder.com.services.UserService;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletRegistration extends ServletRoot {
    private UserService userService;
    private final String cookieName = CookiesNames.TINDER.getName();


    public ServletRegistration(UserService userService) {
        this.userService = userService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("./src/main/resources/templates/registration.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getString("name").trim();
        String occupation = pfr.getString("occupation").trim();
        String photo = pfr.getString("photo").trim();
        String login = pfr.getString("login").trim();
        String password = pfr.getString("password").trim();

        User user = new User(name, occupation, photo, login, password);
        userService.addUser(user);
        resp.addCookie(new Cookie(cookieName, String.valueOf(login.hashCode())));

        resp.sendRedirect("/users");
    }
}
