package com.gm.pm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import com.gm.pm.entity.Team;
import com.gm.pm.entity.TeamCondition;
import com.gm.pm.kit.ExKit;
import com.gm.pm.mapper.ProjectMapper;
import com.gm.pm.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author Jason
 */
@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    ProjectMapper projectMapper;

    public PageInfo findAll(Team team, TeamCondition pc, Integer start, Integer size) {
        PageHelper.startPage(start, size);
        pc.likeEndless();
        Page page = teamMapper.selectBy(pc);
        return new PageInfo(page);
    }

    public void add(Team team) {
        ExKit.run(() -> autoData(team, true, true));
        teamMapper.insert(team);

    }

    public Team findById(Long id) {
        return teamMapper.selectById(id);
    }

    public void update(Team team) {
        ExKit.run(() -> autoData(team, true, true));
        teamMapper.update(team);
    }

    public void state(Team team) {
        String status = team.getStatus();
        if ("off".equalsIgnoreCase(status)) {
            team.setOfftime(new Date());
        } else if ("on".equalsIgnoreCase(status)) {
//            team.setOfftime(null);
        }
        ExKit.run(() -> autoData(team, true, false));
        teamMapper.update(team);
    }

    public void del(Long id) {
        teamMapper.deleteById(id);
    }




    private void autoData(Team team, boolean... bs) {
        String pmName = team.getPmName();
        if(StringUtils.isEmpty(pmName)){
            Team db = teamMapper.selectById(team.getId());
            pmName = db.getPmName();
        }
        if(bs.length>0 && bs[0]){
            /* 统计项目数 */
            Integer onCount = projectMapper.onCount(pmName);
            team.setOnCount(onCount);
        }
        if(bs.length>1 && bs[1]){
            /* 追加项目纪要 */
            if(StringUtils.isEmpty(team.getSummary())) {
                ProjectCondition pc = new ProjectCondition();
                pc.setLikes(pmName);
                Page<Project> page = projectMapper.selectBy(pc);
                StringBuilder sb = new StringBuilder();
                page.forEach(x -> {
                    String format = String.format("%s (%s: %s%)", x.getClient(), x.getCurrentStage(), x.getCurrentSlider());
                    sb.append(format);
                });
                team.setSummary(sb.toString());
            }
        }
    }
}
