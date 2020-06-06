package com.gm.pm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.pm.ProjectService;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import com.gm.pm.entity.Toa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;

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
                       Toa toa,
                       @RequestParam(defaultValue = "1") Integer start,
                       @RequestParam(defaultValue = "5") Integer size
    ) {
        PageInfo page = projectService.findAll(project, pc, start, size);
        model.addAttribute("page", page);
        model.addAttribute("pc", pc.likeRecover());
        model.addAttribute("toa", toa);
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
    public String add(RedirectAttributes model, Project project,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.add(project);
        model.addAttribute("msg", "添加成功");
        return "redirect:/project/list?start=" + start + "&size=" + size;
    }

    @GetMapping(value = "update/{id}")
    public String update(Model model, @PathVariable Long id, ProjectCondition pc,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("pc", pc);
        return "project/update";
    }

    @PostMapping(value = "update")
    public String update(RedirectAttributes model, Project project, ProjectCondition pc,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) throws Exception {
        projectService.update(project);
        model.addAttribute("msg", "修改成功");
        String chooseParam = pc.getChoose() != null ? ("&choose=" + pc.getChoose()) : "";
        String encode = URLEncoder.encode(pc.getLikes(), "UTF-8");
        String likesParam = !StringUtils.isEmpty(pc.getLikes()) ? ("&likes=" + encode) : "";
        return "redirect:/project/list?start=" + start + "&size=" + size + chooseParam + likesParam;
    }


    @GetMapping(value = "del/{id}")
    public String del(RedirectAttributes model, @PathVariable Long id,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.del(id);
        model.addAttribute("msg", "删除成功");
        return "redirect:/project/list?start=" + start + "&size=" + size;
    }
}
