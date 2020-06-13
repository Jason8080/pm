package com.gm.pm.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author Jason
 */
public class JwtSessionManager extends DefaultWebSessionManager {

    public JwtSessionManager() {
        super();
    }

    @Override
    public Cookie getSessionIdCookie() {
        Cookie cookie = new SimpleCookie("token");
        cookie.setHttpOnly(true);
        return cookie;
    }
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            // 是否将sid保存到cookie，浏览器模式下使用此参数。
            if (WebUtils.isTrue(request, "token")) {
                HttpServletRequest rq = (HttpServletRequest) request;
                HttpServletResponse rs = (HttpServletResponse) response;
                Cookie template = getSessionIdCookie();
                Cookie cookie = new SimpleCookie(template);
                cookie.setValue(token);
                cookie.saveTo(rq, rs);
            }
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            return super.getSessionId(request, response);
        }
    }

}
