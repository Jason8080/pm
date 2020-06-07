package com.gm.pm.base.controller;

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
public class GmController {

    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;
    public String token;


    @ModelAttribute
    public void pre(
            @CookieValue(value="token", name = "token") String token,
            HttpServletRequest request, HttpServletResponse response, HttpSession session
    ){
        this.token = token;
        this.request = request;
        this.response = response;
        this.session = session;
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
