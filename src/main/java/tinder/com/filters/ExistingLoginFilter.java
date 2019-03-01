package tinder.com.filters;

import org.eclipse.jetty.http.HttpMethod;
import tinder.com.exceptions.ElementNotFoundInDbException;
import tinder.com.services.UserService;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class ExistingLoginFilter implements Filter {
    private UserService users;

    public ExistingLoginFilter(UserService users) {
        this.users = users;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }

        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        ParameterFromRequest pfr = new ParameterFromRequest(req);
        PrintWriter writer = response.getWriter();
        String login = pfr.getString("login").trim();
        if (users.isUsersDbEmpty()) {
            chain.doFilter(request, response);
        }

        try {
            users.getUser(login.hashCode());
            String message = "The user with this login is already exist";
            writer.printf("<html> <a href=\"/registration\"> %s </a></html>", message);
        } catch (ElementNotFoundInDbException e) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
