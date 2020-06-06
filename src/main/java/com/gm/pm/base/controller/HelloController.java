package com.gm.pm.base.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.entity.Toa;
import com.gm.pm.kit.TokenKit;
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
public class HelloController {

    @GetMapping(value = "/")
    public ModelAndView index(Model model) {
        return new ModelAndView("index");
    }
    @GetMapping(value = "/lock")
    public String lockscreen() {
        return "lockscreen";
    }
    @GetMapping(value = "/login")
    public String login(Model model, Toa toa) {
        model.addAttribute("toa", toa);
        return "login";
    }
    @PostMapping(value = "/login")
    public String login(RedirectAttributes model, Login login, HttpServletResponse response) {
        String token = TokenKit.generateToken(login);
        model.addAttribute("token", token);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
