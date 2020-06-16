package com.gm.pm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.pm.entity.Team;
import com.gm.pm.entity.TeamCondition;
import com.gm.pm.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Jason
 */
@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;

    public PageInfo findAll(Team team, TeamCondition pc, Integer start, Integer size) {
        PageHelper.startPage(start, size);
        pc.likeEndless();
        Page page = teamMapper.selectBy(pc);
        return new PageInfo(page);
    }

    public void add(Team team) {
        teamMapper.insert(team);
    }

    public Team findById(Long id) {
        return teamMapper.selectById(id);
    }

    public void update(Team team) {
        teamMapper.update(team);
    }

    public void state(Team team) {
        String status = team.getStatus();
        if ("off".equalsIgnoreCase(status)) {
            team.setOfftime(new Date());
        } else if ("on".equalsIgnoreCase(status)) {
//            team.setOfftime(null);
        }
        teamMapper.update(team);
    }

    public void del(Long id) {
        teamMapper.deleteById(id);
    }
}
