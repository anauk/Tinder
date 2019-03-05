package tinder.com.filter;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import tinder.com.utils.CookieProcessor;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
** проверяет наличие правильного значения у куки, когда имена кук совпали,
** а их значения по ID в базе нет
 */
public class CookieMatchFilter implements Filter {
    private final CookieProcessor cu;

    public CookieMatchFilter(CookieProcessor cu) {
        this.cu = cu;
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
        if (cu.cookieMatch(req)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }

}
