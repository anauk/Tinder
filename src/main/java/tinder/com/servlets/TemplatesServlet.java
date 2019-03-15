package tinder.com.servlets;

import tinder.com.utils.Freemarker;
import tinder.com.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class TemplatesServlet extends HttpServlet {
    private Freemarker f = new Freemarker();
    HashMap<String,Object> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        f.render(req.getPathInfo(), data, resp);
        
    }
}
