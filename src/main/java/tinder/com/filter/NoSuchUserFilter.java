package tinder.com.filter;

import org.eclipse.jetty.http.HttpMethod;
import tinder.com.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoSuchUserFilter implements Filter {
    private UserService us;

    public NoSuchUserFilter(UserService us) {
        this.us = us;
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
        if(HttpMethod.GET.name().equalsIgnoreCase(req.getMethod())&&us.isUserDBEmpty()){
            resp.getWriter().print("<html> <a href=\"/auth\"> Sorry there is no such user. Register, following this link! </a></html>");

        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
