package com.gm.pm.base.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.kit.TokenKit;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jason
 */
public class BaseController {

    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;
    public String token;

    /**
     * 生成Token
     *
     * @param login 登陆用户
     * @return
     */
    public String generateToken(Login login){
        return TokenKit.generateToken(login);
    }

    /**
     * 获取用户信息
     * @return
     */
    public Login getUser(){
        return new Login();
    }

    @ModelAttribute
    public void pre(
            @CookieValue(value="token") String token,
            HttpServletRequest request, HttpServletResponse response, HttpSession session
    ){
        this.token = token;
        this.request = request;
        this.response = response;
        this.session = session;
    }

//
//    protected RSAToken getRSAToken(int type){
//        // 生成一个token
//        String token = UUID.randomUUID().toString();
//        if(type == 1){
//            session.setAttribute(Constants.KEY_SESSION_TOKEN_LOGIN, token);
//        }else if(type == 2){
//            session.setAttribute(Constants.KEY_SESSION_TOKEN_REGISTER, token);
//        }else if(type == 3){
//            session.setAttribute(Constants.KEY_SESSION_TOKEN_FIND_PWD, token);
//        }
//
//        // 生成公钥信息
//        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
//        String modulus = new String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
//        String exponent = new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));
//
//        RSAToken rsaToken = new RSAToken();
//        rsaToken.setToken(token);
//        rsaToken.setModulus(modulus);
//        rsaToken.setExponent(exponent);
//
//        return rsaToken;
//    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 直接写数据以页面的方式呈现
     * @param msg
     */
    public void printOutMsg(String msg){
        try {
            this.response.setCharacterEncoding("UTF-8");
            this.response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            out = this.response.getWriter();
            out.print(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接写数据以页面的方式呈现
     * @param response
     * @param msg
     */
    public void printOutMsg(HttpServletResponse response, String msg){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.print(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取完整的Url路径
     * @param request
     * @return
     */
    public String getUrl(HttpServletRequest request){
        String url = request.getRequestURI();
        String params = "";
        if(request.getQueryString()!=null){
            params = request.getQueryString().toString();
        }
        if(!"".equals(params)){
            url = url+"?"+params;
        }
        return url;
    }


}
