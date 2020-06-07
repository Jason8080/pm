package com.gm.pm.base.controller;

import com.gm.pm.entity.Login;
import com.gm.pm.entity.Toa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jason
 */
@Controller
public class HomeController extends PermissionController {

    @GetMapping(value = "/")
    public ModelAndView index(Model model, Toa toa, Login login) {
        model.addAttribute("login", login);
        model.addAttribute("toa", toa);
        return new ModelAndView("index");
    }

    @GetMapping(value = "/lock")
    public String lockscreen() {
        return "lockscreen";
    }
}
