package com.tinder.servlets;

import com.tinder.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AssetsServlet extends HttpServlet {
//    private final String ASSETS_ROOT = "./src/main/resources/static";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Files.copy(Paths.get(ASSETS_ROOT, req.getPathInfo()), resp.getOutputStream());
        Freemarker f = new Freemarker("static");
        HashMap<String, Object> data = new HashMap<>();
        f.render(req.getPathInfo(),data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
