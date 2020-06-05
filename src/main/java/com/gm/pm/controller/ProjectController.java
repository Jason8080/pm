package com.gm.pm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.pm.ProjectService;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "update/{id}")
    public String update(Model model, @PathVariable Long id) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project/update";
    }

    @PostMapping(value = "update")
    public String update(Project project) {
        projectService.update(project);
        return "redirect:/project/list";
    }
}
