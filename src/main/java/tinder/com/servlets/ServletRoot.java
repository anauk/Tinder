package tinder.com.servlets;

import tinder.com.Interface.Logger;
import tinder.com.utils.TinderLogger;

import javax.servlet.http.HttpServlet;

public class ServletRoot extends HttpServlet {
    protected Logger logger = new TinderLogger();
}
