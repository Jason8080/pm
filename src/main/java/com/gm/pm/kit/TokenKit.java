package com.gm.pm.kit;

import com.gm.pm.entity.Login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Jason
 */
public class TokenKit {
    /**
     * 获取请求头中的Token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        return getCookie(request, "token");
    }

    /**
     * 获取指定名称的cookie
     *
     * @param request
     * @param key
     * @return
     */
    private static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                if (name.equalsIgnoreCase(key)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    /**
     * 生成token
     *
     * @param login
     * @return
     */
    public static void generateToken(Login login) {
        String token = UUID.randomUUID().toString();
        login.setToken(token);
    }

    public static Login getLogin(String token) {
        return new Login();
    }
}
