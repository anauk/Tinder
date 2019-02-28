import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.com.DAO.CartsDAO_SQL;
import tinder.com.DAO.MessagesDAO_SQL;
import tinder.com.DAO.UserDAO_SQL;
import tinder.com.Interface.DAO;
import tinder.com.dataBase.DbConnection;
import tinder.com.filters.*;
import tinder.com.services.CartService;
import tinder.com.services.MessagesService;
import tinder.com.services.UserService;
import tinder.com.servlets.*;
import tinder.com.utils.CookieProcessor;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();

        String cookieName = CookiesNames.TINDER.getName();
        Connection conn = new DbConnection().connection();


        DAO users = new UserDAO_SQL(conn);
        MessagesDAO_SQL messages = new MessagesDAO_SQL(conn);
        CartsDAO_SQL carts = new CartsDAO_SQL(conn);
        UserService userService = new UserService(users, carts);
        MessagesService messagesService = new MessagesService(messages);
        CartService cartService = new CartService(carts);
        CookieProcessor cp = new CookieProcessor(cookieName, userService);
        CartServlet cartServlet = new CartServlet(cartService, userService, cp);
        ServletUserList servletUserList = new ServletUserList(userService, messagesService, cartService, cp);
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

        handler.addFilter(new FilterHolder(new NoUsersFilter(userService)), "/login", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new ExistingLoginFilter(userService)), "/registration", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new EntryFormatFilter()), "/registration", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new PasswordMismatchFilter()), "/registration", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginPasswordFilter(userService)), "/login", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new HasCookiesFilter()), "/liked", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new HasMyCookieFilter()), "/liked", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieMatchFilter(cp)), "/liked", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new HasCookiesFilter()), "/users", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new HasMyCookieFilter()), "/users", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieMatchFilter(cp)), "/users", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));


        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();

    }

}
