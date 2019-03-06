package com.tinder.filters;

import com.tinder.exceptions.ElementNotFoundInDbException;
import com.tinder.utils.ParameterFromRequest;
import org.eclipse.jetty.http.HttpMethod;
import com.tinder.services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPasswordFilter implements Filter {
    private UserService userService;

    public LoginPasswordFilter(UserService users) {
        this.userService = users;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        HttpServletResponse resp;
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            req = (HttpServletRequest) request;
            resp = (HttpServletResponse) response;
        } else {
            throw new IllegalArgumentException("ServletRequest and ServletResponse should be instance of HttpServletRequest and HttpServletResponse");
        }

        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getString("login");
        String password = pfr.getString("password");


        try {
            if (!userService.checkUserPass(login, password)) {
                throw new IllegalArgumentException("The user login or password is incorrect!");
            }
            chain.doFilter(request, response);
        } catch (IllegalArgumentException | ElementNotFoundInDbException e) {
//            response.getWriter().println("<html> <a href=\"/login\">The user login or password is incorrect</a></html>");
            resp.sendRedirect("/login?error=The user login or password is incorrect!");
        }

    }

    @Override
    public void destroy() {

    }
}
