package tinder.com;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.com.dataBase.DBConnection;
import tinder.com.entity.CookiesNames;
import tinder.com.filter.*;
import tinder.com.impl.*;
import tinder.com.service.CartService;
import tinder.com.service.MessagesService;
import tinder.com.utils.CookieProcessor;
import tinder.com.service.UserService;
import tinder.com.servlets.*;
import tinder.com.servlets.TemplatesServlet;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        String cookieName = CookiesNames.TINDER.getName();
        Connection connection = new DBConnection().connection();
        ServletContextHandler handler = new ServletContextHandler();

        UserService userService = new UserService(new UserDAO_SQl(connection), new CartsDAO_SQl(connection), new DAOextra_SQL(connection));
        CookieProcessor cu = new CookieProcessor(cookieName, userService);
        CartService cartService = new CartService(new CartsDAO_SQl(connection));
        MessagesService messagesService = new MessagesService(new MessagesDAO_SQl(connection));

        handler.addServlet(TemplatesServlet.class, "/templates/*");

        handler.addServlet(new ServletHolder(new HelloServlet()), "/hello");
        handler.addServlet(new ServletHolder(new SlashServlet()), "/");
        handler.addServlet(new ServletHolder(new UsersServlet(userService, cu, cartService)), "/users");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addServlet(new ServletHolder(new AuthServlet(userService)), "/auth");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        handler.addServlet(new ServletHolder(new ChatServlet(cartService, userService, messagesService, cu)), "/chat/*");
        handler.addServlet(new ServletHolder(new LikedServlet(cartService, userService, cu)), "/liked");

        handler.addFilter(new FilterHolder(new LoginPasswordFilter(userService)), "/login", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new NoSuchUserFilter(userService)), "/login", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        handler.addFilter(new FilterHolder(new AlreadyExistsUserFilter(userService)), "/auth", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        handler.addFilter(new FilterHolder(new HasCoocieFilter()), "/liked", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new HasCoocieFilter()), "/users", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new HasCoocieFilter()), "/chat/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        handler.addFilter(new FilterHolder(new SpecificeCookieFilter()), "/liked", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new SpecificeCookieFilter()), "/users", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new SpecificeCookieFilter()), "/chat/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        handler.addFilter(new FilterHolder(new IdentifyCookieFilter(cu)), "/liked", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new IdentifyCookieFilter(cu)), "/users", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new IdentifyCookieFilter(cu)), "/chat/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        String port = System.getenv().get("PORT");
        if (port == null || port.equals("")){
            port = "8080";
        }

        Server server = new Server(Integer.parseInt(port));
        server.setHandler(handler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
