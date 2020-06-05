package com.gm.pm;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.pm.entity.ProjectCondition;
import com.gm.pm.kit.StageKit;
import com.gm.pm.mapper.ProjectMapper;
import com.gm.pm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 *
 * @author Jason
 */
@Service
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public PageInfo findAll(Project project, ProjectCondition pc, Integer start, Integer size) {
        PageHelper.startPage(start, size);
        Page page = projectMapper.selectBy(project, pc);
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
}
