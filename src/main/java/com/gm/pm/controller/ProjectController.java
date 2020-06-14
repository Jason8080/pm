package com.gm.pm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.pm.base.controller.PermissionController;
import com.gm.pm.entity.Inventory;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import com.gm.pm.entity.Toa;
import com.gm.pm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Jason
 */
@Controller
@RequestMapping("project")
public class ProjectController extends PermissionController {


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
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "redirect:/project/list";
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

    @GetMapping(value = "o2o")
    public String o2o(Model model, ProjectCondition pc, Inventory in
    ) throws Exception {
        Project project = projectService.in(pc, in);
        if(project!=null){
            model.addAttribute("project", project);
            model.addAttribute("in", in);
            model.addAttribute("pc", pc.likeRecover());
            return "project/o2o";
        }else {
            pc.likeRecover();
            String msg = URLEncoder.encode("盘点完成!", "UTF-8");
            String likes = URLEncoder.encode(pc.getLikes(), "UTF-8");
            return "redirect:/project/list?choose="+pc.getChoose()+"&likes="+likes+"&msg="+msg;
        }
    }

    @PostMapping(value = "o2o")
    public String o2o(RedirectAttributes model, Project project, ProjectCondition pc,
                      Inventory in
    ) {
        projectService.update(project);
        int current = in.getCurrent();
        in.setPrev(current - 1);
        in.setNext(current + 1);
        model.addAttribute("current", in.getNext());
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        model.addAttribute("msg", "保存成功!");
        return "redirect:/project/o2o";
    }

    @PostMapping(value = "update")
    public String update(RedirectAttributes model, Project project, ProjectCondition pc,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) throws Exception {
        projectService.update(project);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/project/list";
    }

    @GetMapping(value = "state/{id}")
    public String state(RedirectAttributes model, @PathVariable Long id,
                        String status, ProjectCondition pc,
                        @RequestParam(defaultValue = "1") Integer start,
                        @RequestParam(defaultValue = "5") Integer size
    ) throws Exception {
        Project pro = new Project();
        pro.setId(id);
        pro.setStatus(status);
        projectService.state(pro);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/project/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(RedirectAttributes model, @PathVariable Long id,
                      ProjectCondition pc,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.del(id);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/project/list";
    }

    @RequestMapping(value = "urge/{id}")
    public String urge(RedirectAttributes model, @PathVariable Long id,
                       ProjectCondition pc,
                       @RequestParam(defaultValue = "1") Integer start,
                       @RequestParam(defaultValue = "5") Integer size
    ) {
        projectService.urge(id);
        model.addAttribute("msg","邮件已发送!");
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/project/list";
    }
}
