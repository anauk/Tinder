import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.com.Interface.UserStorage;
import tinder.com.dataBase.DBConnection;
import tinder.com.freemarker.Freemarker;
import tinder.com.impl.UserStoragImpl;
import tinder.com.servlets.AssertServlet;
import tinder.com.servlets.HelloServlet;
import tinder.com.servlets.LoginServlet;
import tinder.com.servlets.UsersServlet;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        UserStorage security = new UserStoragImpl();
        Connection connection = new DBConnection().connection();
        Freemarker f = new Freemarker("src/main/resources/templates");

        handler.addServlet(AssertServlet.class, "/assert/*");

        handler.addServlet(new ServletHolder(new HelloServlet()), "/hello/*");
        handler.addServlet(new ServletHolder(new UsersServlet(connection, f)), "/users/*");
        handler.addServlet(new ServletHolder(new LoginServlet(security, connection, f)), "/login/*");

        //handler.addFilter(LoginFilter.class, "/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        //handler.addFilter(AuthFilter.class, "/auth/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
