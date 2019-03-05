package com.tinder.filters;

import com.tinder.utils.ParameterFromRequest;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PasswordMismatchFilter implements Filter {
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

        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            ParameterFromRequest pfr = new ParameterFromRequest(req);
            PrintWriter writer = response.getWriter();
            String password = pfr.getString("password").trim();
            String password2 = pfr.getString("password2").trim();
            if (!password.equals(password2)) {
                try {
                    throw new IllegalArgumentException("Passwords in two fields do not match each other. Please, try again");
                } catch (IllegalArgumentException e){
//                    writer.printf("<html> <a href=\"/registration\"> %s </a></html>", e.getMessage());
                    resp.sendRedirect("/registration?error="+e.getMessage());
                }
            } else {
                chain.doFilter(request, response);
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
