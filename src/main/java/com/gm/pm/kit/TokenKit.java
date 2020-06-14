package com.gm.pm.kit;

import com.gm.pm.entity.Login;
import com.gm.pm.ex.TokenException;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jason
 */
public class TokenKit {

    public static int exp = 2 * 3600 * 1000;

    /**
     * 获取请求头中的Token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                token = getCookie(request, "token");
            }
        }
        return token;
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
    public static String generateToken(Login login) {
        JwtKit.Head head = new JwtKit.Head();
        JwtKit.Body body = new JwtKit.Body();
        body.setExp(System.currentTimeMillis() + exp);
        body.setName(login.getName());
        body.setIp(login.getIp());
        body.setRoles(login.getRoles());
        String token = JwtKit.sign(head, body);
        return token;
    }

    public static Login parseToken(String token) {
        JwtKit.Body body = JwtKit.sign(token);
        if (body != null) {
            Long exp = body.getExp();
            long current = System.currentTimeMillis();
            if (exp != null && current < exp) {
                return new Login(body.getName(), body.getRoles(), body.getIp());
            }
        }
        return null;
    }

    public static Login assertToken(String token) {
        Login login = parseToken(token);
        try {
            return Assert.notEmpty(login);
        } catch (Exception e) {
            throw new TokenException();
        }
    }


    public static void main(String[] args) {
        Login login = new Login("Jason", "admin", "127.0.0.1");
        String token = generateToken(login);
        System.out.println(token);
        Login l = parseToken(token);
        System.out.println(l);
    }
}
