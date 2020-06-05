package com.gm.pm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jason
 */
@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
