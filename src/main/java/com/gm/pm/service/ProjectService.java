package com.gm.pm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.pm.entity.Inventory;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import com.gm.pm.kit.MailKit;
import com.gm.pm.kit.StageKit;
import com.gm.pm.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason
 */
@Service
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public PageInfo findAll(Project project, ProjectCondition pc, Integer start, Integer size) {
        PageHelper.startPage(start, size);
        pc.likeEndless();
        Page page = projectMapper.selectBy(pc);
        return new PageInfo(page);
    }

    public void add(Project project) {
        StageKit.setCurrentTime(project);
        projectMapper.insert(project);
    }

    public Project findById(Long id) {
        return projectMapper.selectById(id);
    }

    public void update(Project project) {
        StageKit.setCurrentTime(project);
        projectMapper.update(project);
    }

    public void state(Project project) {
        projectMapper.update(project);
    }

    public void del(Long id) {
        projectMapper.deleteById(id);
    }

    public void urge(Long id) {
        Project project = projectMapper.selectById(id);
        if (project != null) {
            // MailKit.send("你的项目有人着急了", "", "");
        }
    }

    public static void main(String[] args) {
        MailKit.send("你的项目有人着急了", "你真的是", "xiaoku13141@163.com");
    }

    public Project in(ProjectCondition pc, Inventory in) {
        pc.likeEndless();
        List<Project> list = projectMapper.selectBy(pc);
        int current = in.getCurrent();
        in.setPrev(current - 1);
        in.setNext(current + 1);
        if (current >= 0 && current < list.size()) {
            return list.get(current);
        }
        return null;
    }
}
