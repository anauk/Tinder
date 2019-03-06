package com.tinder.utils;

import javax.servlet.http.HttpServletRequest;

public class ParameterFromRequest {
    private HttpServletRequest req;

    public ParameterFromRequest(HttpServletRequest req) {
        this.req = req;
    }

    public int getInt (String paramName){
        String number = req.getParameter(paramName);
        if(number == null || number.equals("")){
            throw new IllegalStateException(String.format("Missing parameter %s", paramName));
        }

        return Integer.parseInt(number.replaceAll("[\\s|\\u00A0|,]+",""));
    }

    public String getString (String paramName) {
            return req.getParameter(paramName);
    }
}
