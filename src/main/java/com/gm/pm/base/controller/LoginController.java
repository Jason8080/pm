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
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jason
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/login")
    public String login(Model model, Toa toa) {
        model.addAttribute("toa", toa);
        return "login";
    }
    @PostMapping(value = "/login")
    public String login(RedirectAttributes model, Login login, HttpServletResponse res) {
        String msg = loginService.login(login);
        switch (msg){
            case "ok": {
                model.addAttribute("token", login.getToken());
                model.addAttribute("msg", "登陆成功!");
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
