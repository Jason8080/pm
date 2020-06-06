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
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "project/list";
    }

    @GetMapping(value = "add")
    public String add(Model model,
            @RequestParam(defaultValue = "1") Integer start,
            @RequestParam(defaultValue = "5") Integer size) {
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "project/add";
    }

    @PostMapping(value = "add")
    public String add(Project project,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.add(project);
        return "redirect:/project/list?start=" + start + "&size=" + size;
    }

    @GetMapping(value = "update/{id}")
    public String update(Model model, @PathVariable Long id,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "project/update";
    }

    @PostMapping(value = "update")
    public String update(Project project,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.update(project);
        return "redirect:/project/list?start=" + start + "&size=" + size;
    }


    @GetMapping(value = "del/{id}")
    public String del(Model model, @PathVariable Long id,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.del(id);
        return "redirect:/project/list?start=" + start + "&size=" + size;
    }
}
