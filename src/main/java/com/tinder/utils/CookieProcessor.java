package com.tinder.utils;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import com.tinder.exceptions.ElementNotFoundInDbException;
import com.tinder.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieProcessor {
    private final String cookieName;
    private final UserService userService;

    public CookieProcessor(String cookieName, UserService userService) {
        this.cookieName = cookieName;
        this.userService = userService;
    }

    public String getValue(HttpServletRequest req) throws CookieUnavailableException {
        Cookie[] cookies = req.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw new CookieUnavailableException();
        }
        String value = Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(cookieName))
                .findFirst().get().getValue();
        return value;
    }

    public boolean cookieMatch(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        final boolean[] result = {false};
        if (cookies == null || cookies.length == 0) {
            return false;
        }
        Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(cookieName))
                .findFirst()
                .ifPresent(c -> {
                    try {
                        userService.getUser(Integer.parseInt(c.getValue()));
                        result[0] = true;
                    } catch (ElementNotFoundInDbException | NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                });
        return result[0];
    }

    public void deleteCookie (HttpServletResponse resp){
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
