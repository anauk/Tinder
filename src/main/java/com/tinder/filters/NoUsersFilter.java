package com.tinder.filters;

import org.eclipse.jetty.http.HttpMethod;
import com.tinder.services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoUsersFilter implements Filter {
    private UserService users;

    public NoUsersFilter(UserService users) {
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

        if (HttpMethod.GET.name().equalsIgnoreCase(req.getMethod()) && users.isUsersDbEmpty()) {
            resp.sendRedirect("/registration");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
