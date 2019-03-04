package com.tinder.filters;


import com.tinder.exceptions.IncorrectEntryException;
import com.tinder.utils.EntryFormats;
import com.tinder.utils.ParameterFromRequest;
import com.tinder.utils.StringValidator;
import com.tinder.utils.Validator;
import org.eclipse.jetty.http.HttpMethod;


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

