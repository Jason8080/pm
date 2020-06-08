package com.gm.pm.base.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.entity.Toa;
import com.gm.pm.kit.TokenKit;
import com.gm.pm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jason
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/login")
    public String login(Model model, Toa toa) {
        model.addAttribute("toa", toa);
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model, Toa toa) {
        model.addAttribute("toa", toa);
        return "register";
    }

    @GetMapping(value = "/logout")
    public String logout(RedirectAttributes model, HttpServletResponse res) {
        model.addAttribute("msg", "登出成功!");
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "redirect:/login";
    }

    @PostMapping(value = "/login")
    public String login(RedirectAttributes model, Login login,
                        HttpServletRequest request, HttpServletResponse res
    ) {
        String ip = getIP(request);
        login.setLastIp(ip);
        String token = loginService.login(login);
        if (!StringUtils.isEmpty(token)) {
            saveSession(request.getSession(), login);
            model.addAttribute("token", token);
            model.addAttribute("msg", "登入成功!");
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(TokenKit.exp / 1000);
            res.addCookie(cookie);
            return "redirect:/";
        } else {
            model.addAttribute("type", "error");
            model.addAttribute("msg", "用户名或密码错误!");
            return "redirect:/login";
        }
    }


    @PostMapping(value = "/register")
    public String register(RedirectAttributes model, Login login,
                           HttpServletRequest request, HttpServletResponse res) {
        String ip = getIP(request);
        login.setLastIp(ip);
        String token = loginService.register(login);
        if (!StringUtils.isEmpty(token)) {
            saveSession(request.getSession(), login);
            model.addAttribute("token", token);
            model.addAttribute("title", "注册成功");
            model.addAttribute("msg", "已经自动为您登入!");
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(TokenKit.exp / 1000);
            res.addCookie(cookie);
            return "redirect:/";
        } else {
            model.addAttribute("type", "error");
            model.addAttribute("title", "注册失败");
            model.addAttribute("msg", "用户名已经存在!");
            return "redirect:/register";
        }
    }

    private void saveSession(HttpSession session, Login login) {
        session.setAttribute("login", login);
    }
}