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
        JwtKit.Head head = new JwtKit.Head();
        JwtKit.Body body = new JwtKit.Body();
        body.setExp((72 * 3600 * 1000L));
        body.setName(login.getName());
        body.setIp(login.getLastIp());
        body.setRoles(login.getRoles());
        String token = JwtKit.sign(head, body);
        login.setToken(token);
    }

    public static Login parseToken(String token) {
        JwtKit.Body body = JwtKit.sign(token);
        if (body != null) {
            return new Login(token, body.getName(), body.getRoles(), body.getIp());
        }
        return null;
    }


    public static void main(String[] args) {
        Login login = new Login("Jason", "admin", "127.0.0.1");
        generateToken(login);
        System.out.println(login.getToken());
        Login l = parseToken(login.getToken());
        System.out.println(l);
    }
}
