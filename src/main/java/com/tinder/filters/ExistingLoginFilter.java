package com.tinder.filters;

import com.tinder.exceptions.ElementNotFoundInDbException;
import com.tinder.utils.ParameterFromRequest;
import org.eclipse.jetty.http.HttpMethod;
import com.tinder.services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//        PrintWriter writer = response.getWriter();
        String login = pfr.getString("login").trim();
        if (users.isUsersDbEmpty()) {
            chain.doFilter(request, response);
        }

        try {
            users.getUser(login.hashCode());
            String error = "The user with this login is already exist";
//            writer.printf("<html> <a href=\"/registration\"> %s </a></html>", error);
            resp.sendRedirect("/registration?error="+error);
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
