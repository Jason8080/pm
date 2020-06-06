package com.gm.pm.base.controller;

import com.gm.pm.entity.Toa;
import com.gm.pm.kit.TokenKit;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 控制器切面
 *
 * @author Jason
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MissingRequestCookieException.class})
    public ModelAndView cookieExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        String token = TokenKit.getToken(request);
        ModelAndView mv = new ModelAndView();
        if(StringUtils.isEmpty(token)) {
            String msg = URLEncoder.encode("登陆超时或未登录!", "UTF-8");
            mv.setViewName("redirect:/login?type=warning&msg="+msg);
        }else {
            mv.setViewName("forward:/error/"+400+"?type=error&msg=Cookies遇到问题, 请检查!");
        }
        return mv;
    }
}