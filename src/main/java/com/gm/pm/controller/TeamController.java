package com.gm.pm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.pm.base.controller.PermissionController;
import com.gm.pm.entity.Team;
import com.gm.pm.entity.TeamCondition;
import com.gm.pm.entity.Toa;
import com.gm.pm.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Jason
 */
@Controller
@RequestMapping("team")
public class TeamController extends PermissionController {


    @Autowired
    TeamService teamService;

    @RequestMapping(value = "list")
    public String list(Model model, Team team, TeamCondition pc,
                       Toa toa,
                       @RequestParam(defaultValue = "1") Integer start,
                       @RequestParam(defaultValue = "5") Integer size
    ) {
        PageInfo page = teamService.findAll(team, pc, start, size);
        model.addAttribute("page", page);
        model.addAttribute("pc", pc.likeRecover());
        model.addAttribute("toa", toa);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "team/list";
    }

    @GetMapping(value = "add")
    public String add(Model model,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size) {
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "team/add";
    }

    @PostMapping(value = "add")
    public String add(RedirectAttributes model, Team team,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        teamService.add(team);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        return "redirect:/team/list";
    }

    @GetMapping(value = "update/{id}")
    public String update(Model model, @PathVariable Long id, TeamCondition pc,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) {
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("pc", pc);
        return "team/update";
    }

    @PostMapping(value = "update")
    public String update(RedirectAttributes model, Team team, TeamCondition pc,
                         @RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "5") Integer size
    ) throws Exception {
        teamService.update(team);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/team/list";
    }

    @GetMapping(value = "state/{id}")
    public String state(RedirectAttributes model, @PathVariable Long id,
                        String status, TeamCondition pc,
                        @RequestParam(defaultValue = "1") Integer start,
                        @RequestParam(defaultValue = "5") Integer size
    ) throws Exception {
        Team team = new Team();
        team.setId(id);
        team.setStatus(status);
        teamService.state(team);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/team/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(RedirectAttributes model, @PathVariable Long id,
                      TeamCondition pc,
                      @RequestParam(defaultValue = "1") Integer start,
                      @RequestParam(defaultValue = "5") Integer size
    ) {
        teamService.del(id);
        model.addAttribute("start", start);
        model.addAttribute("size", size);
        model.addAttribute("choose", pc.getChoose());
        model.addAttribute("likes", pc.getLikes());
        return "redirect:/team/list";
    }

}
