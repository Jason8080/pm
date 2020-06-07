package com.gm.pm.base.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.kit.TokenKit;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jason
 */
public class PermissionController extends BaseController {


    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;
    public String token;


    @ModelAttribute
    public void pre(
            @CookieValue(value="token", name = "token") String token,
            HttpServletRequest request, HttpServletResponse response, HttpSession session
    ){
        TokenKit.assertToken(token);
        this.token = token;
        this.request = request;
        this.response = response;
        this.session = session;
    }

    /**
     * 获取用户信息
     * @return
     */
    public Login getCurrentUser(){
        return TokenKit.parseToken(token);
    }
}
