import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.com.Interface.UserStorage;
import tinder.com.dataBase.DBConnection;
import tinder.com.entity.CookiesNames;
import tinder.com.impl.*;
import tinder.com.service.CartService;
import tinder.com.service.MessagesService;
import tinder.com.utils.CookieProcessor;
import tinder.com.utils.Freemarker;
import tinder.com.service.UserService;
import tinder.com.servlets.*;
import tinder.com.utils.TemplatesServlet;

import java.sql.Connection;

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
        handler.addServlet(new ServletHolder(new UsersServlet(userService, cu, cartService)), "/users");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addServlet(new ServletHolder(new AuthServlet(userService)), "/auth");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        handler.addServlet(new ServletHolder(new ChatServlet(cartService, userService, messagesService, cu)), "/chat");
        handler.addServlet(new ServletHolder(new LikedServlet(cartService, userService, cu)), "/liked");

        //handler.addFilter(new FilterHolder(new LoginPasswordFilter(userService)), "/login", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));



        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();

    }


}
