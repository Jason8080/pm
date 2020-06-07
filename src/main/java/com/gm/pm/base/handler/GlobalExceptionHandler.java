package com.gm.pm.base.handler;

import com.gm.pm.ex.TokenException;
import com.gm.pm.kit.TokenKit;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateEngineException;
import org.thymeleaf.exceptions.TemplateProcessingException;

import javax.servlet.http.HttpServletRequest;
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
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MissingRequestCookieException.class})
    public ModelAndView cookieExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        String token = TokenKit.getToken(request);
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty(token)) {
            String msg = URLEncoder.encode("登入超时或未登入!", "UTF-8");
            mv.setViewName("redirect:/login");
        } else {
            mv.setViewName("forward:/error/" + 400 + "?type=error&msg=Cookies遇到问题, 请检查!");
        }
        return mv;
    }

    @ExceptionHandler(value = {TokenException.class})
    public ModelAndView tokenExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        String msg = URLEncoder.encode("登入超时或未登入!", "UTF-8");
        mv.setViewName("redirect:/login?type=warning&msg=" + msg);
        return mv;
    }

    @ExceptionHandler(value = {TemplateEngineException.class, SpelEvaluationException.class})
    public ModelAndView thymeleafExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        String msg = URLEncoder.encode("Thymeleaf template exception!", "UTF-8");
        mv.setViewName("redirect:/error/500?type=warning&msg=" + msg);
        return mv;
    }

    @ExceptionHandler(value = {Throwable.class})
    public ModelAndView otherHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        String msg = URLEncoder.encode("请联系管理员!", "UTF-8");
        mv.setViewName("redirect:/error/500?type=warning&msg=" + msg);
        e.printStackTrace();
        return mv;
    }
}