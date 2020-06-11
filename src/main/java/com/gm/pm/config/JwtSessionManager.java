package com.gm.pm.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

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
    protected void onStart(Session session, SessionContext context) {
//        super.onStart(session, context);
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
