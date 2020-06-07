package com.gm.pm.base.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.entity.Toa;
import com.gm.pm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String msg = loginService.login(login);
        switch (msg){
            case "ok": {
                model.addAttribute("token", login.getToken());
                model.addAttribute("msg", "登入成功!");
                Cookie cookie = new Cookie("token", login.getToken());
                res.addCookie(cookie);
                return "redirect:/";
            }
            default: {
                model.addAttribute("type", "error");
                model.addAttribute("msg", msg);
                return "redirect:/login";
            }
        }
    }
}
