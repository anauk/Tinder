package tinder.com.filter;

import tinder.com.entity.CookiesNames;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
//проверяет, что куки именно TINDER("tinder_cookie") для /users, /liked, /chat;
public class SpecificeCookieFilter implements Filter {
    private final String cookieName = CookiesNames.TINDER.getName();
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
        Cookie[] cookies = req.getCookies();
        boolean check = Arrays.stream(cookies)
                .anyMatch(c->c.getName().equalsIgnoreCase(cookieName));
        if(check){
            chain.doFilter(request,response);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
