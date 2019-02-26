package tinder.com.utils;

import javax.servlet.http.HttpServletRequest;

public class ParameterFromRequest {
    private final HttpServletRequest req;

    public ParameterFromRequest(HttpServletRequest req) {
        this.req = req;
    }
    public int getInt(String input){
        return Integer.parseInt(req.getParameter(input));
    }
    public String getStr(String input){
        return req.getParameter(input);
    }

}
