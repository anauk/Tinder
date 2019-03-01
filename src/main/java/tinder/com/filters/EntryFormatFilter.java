package tinder.com.filters;


import org.eclipse.jetty.http.HttpMethod;
import tinder.com.exceptions.IncorrectEntryException;
import tinder.com.utils.EntryFormats;
import tinder.com.utils.ParameterFromRequest;
import tinder.com.utils.StringValidator;
import tinder.com.utils.Validator;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class EntryFormatFilter implements Filter {

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
        Validator loginValidator = new StringValidator(EntryFormats.LOGIN.getFormat(), EntryFormats.LOGIN.getMessage());
        Validator passValidator = new StringValidator(EntryFormats.PASSWORD.getFormat(), EntryFormats.PASSWORD.getMessage());
        Validator nameValidator = new StringValidator(EntryFormats.NAME.getFormat(), EntryFormats.NAME.getMessage());
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        PrintWriter writer = response.getWriter();
        String name = pfr.getString("name").trim();
        String login = pfr.getString("login").trim();
        String pass = pfr.getString("password").trim();

        try {
            nameValidator.isValid(name);
            loginValidator.isValid(login);
            passValidator.isValid(pass);
            chain.doFilter(request, response);
        } catch (IncorrectEntryException e) {
            writer.printf("<html> <a href=\"/registration\"> %s </a></html>", e.getMessage());
        }

    }

    @Override
    public void destroy() {

    }
}

