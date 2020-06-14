package com.gm.pm.kit;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebKit {


    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equalsIgnoreCase(name)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
