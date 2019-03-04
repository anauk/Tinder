package com.tinder.servlets;

import com.tinder.Interface.Logger;
import com.tinder.utils.TinderLogger;

import javax.servlet.http.HttpServlet;

public class ServletRoot extends HttpServlet {
    protected Logger logger = new TinderLogger();
}
