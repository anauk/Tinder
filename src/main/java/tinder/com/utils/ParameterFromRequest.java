package tinder.com.utils;

import javax.servlet.http.HttpServletRequest;

public class ParameterFromRequest {
    private final HttpServletRequest req;

    public ParameterFromRequest(HttpServletRequest req) {
        this.req = req;
    }

    public int getInt(String input) {
        String number = req.getParameter(input);
        if (number == null || number.equals("")) {
            throw new IllegalStateException(String.format("Missing parameter %s", input));
        }
        number = number.replaceAll("[\\s|\\u00A0]+", "");
        number = number.replaceAll(",", "");
        return Integer.parseInt(number);
    }

    public String getStr(String input) {
        return req.getParameter(input);
    }
}
