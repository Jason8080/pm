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
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = WebUtils.toHttp(request);
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return super.getSessionId(request, response);
        }
        return token;
    }

    @Override
    public Cookie getSessionIdCookie() {
        Cookie cookie = new SimpleCookie("token");
        cookie.setMaxAge(2*3600*1000);
        cookie.setHttpOnly(true);
        return cookie;
    }

    @Override
    protected void onStart(Session session, SessionContext context) {
        Serializable currentId = session.getId();
        Cookie template = this.getSessionIdCookie();
        Cookie cookie = new SimpleCookie(template);
        String idString = currentId.toString();
        cookie.setValue(idString);
        HttpServletRequest request = WebUtils.getHttpRequest(context);
        HttpServletResponse response = WebUtils.getHttpResponse(context);
        cookie.saveTo(request, response);
    }
}
