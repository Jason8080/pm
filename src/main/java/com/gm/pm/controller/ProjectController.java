package com.gm.pm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.pm.ProjectService;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jason
 */
@Controller
@RequestMapping("project")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "list")
    public String list(Model model, Project project, ProjectCondition pc,
                       @RequestParam(defaultValue = "1") Integer start,
                       @RequestParam(defaultValue = "5") Integer size
    ) {
        PageInfo page = projectService.findAll(project, pc, start, size);
        model.addAttribute("page", page);
        model.addAttribute("pc", pc);
        return "project/list";
    }

    @GetMapping(value = "add")
    public String add() {
        return "project/add";
    }

    @PostMapping(value = "add")
    public String add(Project project) {
        projectService.add(project);
        return "redirect:/project/list";
    }
}
