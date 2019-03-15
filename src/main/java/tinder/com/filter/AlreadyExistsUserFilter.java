package tinder.com.filter;

import org.eclipse.jetty.http.HttpMethod;
import tinder.com.exceptions.ElementNotFoundInDbException;
import tinder.com.service.UserService;
import tinder.com.utils.ParameterFromRequest;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AlreadyExistsUserFilter implements Filter {
    private UserService us;

    public AlreadyExistsUserFilter(UserService us) {
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
        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String email = pfr.getStr("email");
        if(us.isUserDBEmpty()){
            chain.doFilter(request, response);
        }
        try {
            us.getUser(email.hashCode());
            String error = "The user with this email is already exist";
            response.getWriter().printf("<html> <a href=\"/auth\"> %s </a></html>", error);
        } catch (ElementNotFoundInDbException e){
            try {
                chain.doFilter(request, response);
            }catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }


    }

    @Override
    public void destroy() {

    }
}
