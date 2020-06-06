package com.gm.pm.controller;

import com.gm.pm.entity.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jason
 */
@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
    @GetMapping(value = "/404")
    public String four() {
        return "404";
    }
    @GetMapping(value = "/500")
    public String five() {
        return "500";
    }
    @GetMapping(value = "/lock")
    public String lockscreen() {
        return "lockscreen";
    }
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
    @PostMapping(value = "/login")
    public String login(Login login) {
        return "redirect:/";
    }
}
