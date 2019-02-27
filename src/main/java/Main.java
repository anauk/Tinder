import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.com.DAO.CartsDAO_SQL;
import tinder.com.DAO.MessagesDAO_SQL;
import tinder.com.DAO.UserDAO_SQL;
import tinder.com.Interface.DAO;
import tinder.com.dataBase.DbConnection;
import tinder.com.services.CartService;
import tinder.com.services.MessagesService;
import tinder.com.services.UserService;
import tinder.com.servlets.*;
import tinder.com.utils.CookieProcessor;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();

        String cookieName = CookiesNames.TINDER.getName();
        Connection conn = new DbConnection().connection();


        DAO users = new UserDAO_SQL(conn);
        MessagesDAO_SQL messages = new MessagesDAO_SQL(conn);
        CartsDAO_SQL carts = new CartsDAO_SQL(conn);
        UserService userService = new UserService(users);
        MessagesService messagesService = new MessagesService(messages);
        CartService cartService = new CartService(carts);
        CookieProcessor cp = new CookieProcessor(cookieName, userService);
        CartServlet cartServlet = new CartServlet(cartService, userService, cp);
        ServletUserList servletUserList = new ServletUserList(messagesService, cartService, cp);
        ServletRegistration servletRegistration = new ServletRegistration(userService);
        ServletLogin servletLogin = new ServletLogin();
        ServletLogout servletLogout = new ServletLogout();
        AssetsServlet assetsServlet = new AssetsServlet();

        handler.addServlet(new ServletHolder(servletUserList), "/users");
        handler.addServlet(new ServletHolder(servletRegistration), "/registration");
        handler.addServlet(new ServletHolder(servletLogin), "/login");
        handler.addServlet(new ServletHolder(servletLogout), "/logout");
        handler.addServlet(new ServletHolder(cartServlet), "/liked");
        handler.addServlet(new ServletHolder(assetsServlet), "/assets/*");

        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
