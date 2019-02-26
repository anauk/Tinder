package tinder.com.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AssertServlet extends HttpServlet {
    private final String ASSERT_ROOT = "./src/main/resources/assert/css";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get(ASSERT_ROOT, req.getPathInfo()), resp.getOutputStream());

    }
}
