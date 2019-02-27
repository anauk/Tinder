package tinder.com.utils;

import javax.servlet.http.HttpServletRequest;

public class ParameterFromRequest {
    private HttpServletRequest req;

    public ParameterFromRequest(HttpServletRequest req) {
        this.req = req;
    }

    public int getInt (String paramName){
        if(req.getParameter(paramName) == null || req.getParameter(paramName).equals("")){
            throw new IllegalStateException(String.format("Missing parameter %s", paramName));
        }
        return Integer.parseInt(req.getParameter(paramName));
    }

    public String getString (String paramName) {
            return req.getParameter(paramName);
    }
}
