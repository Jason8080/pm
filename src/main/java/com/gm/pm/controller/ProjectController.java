package com.gm.pm.controller;

import com.gm.pm.vo.ProjectVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jason
 */
@Controller
@RequestMapping("project")
public class ProjectController {

    @GetMapping(value = "list")
    public String list() {
        return "project/list";
    }

    @GetMapping(value = "add")
    public String add() {
        return "project/add";
    }

    @PostMapping(value = "add")
    public String add(ProjectVo pv) {
        System.out.println(pv);
        return "redirect:/project/list";
    }
}
