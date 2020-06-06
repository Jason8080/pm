package com.gm.pm.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.entity.Toa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jason
 */
@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
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
    public String login(Login login) {
        return "redirect:/";
    }
}
