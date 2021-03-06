package com.gm.pm.base.controller;

import com.gm.pm.entity.Toa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jason
 */
@Controller
@RequestMapping("error")
public class ErrorController extends BaseController {

    @RequestMapping(value = "{code}")
    public String four(Model model, Toa toa,
                       @PathVariable String code
    ) {
        model.addAttribute("toa", toa);
        return code;
    }
}
