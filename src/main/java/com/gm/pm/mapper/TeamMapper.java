package com.gm.pm.mapper;

import com.github.pagehelper.Page;
import com.gm.pm.entity.Team;
import com.gm.pm.entity.TeamCondition;
import org.apache.ibatis.annotations.Param;

/**
 * The interface team mapper.
 *
 * @author Jason
 */
public interface TeamMapper {

    /**
     * Insert integer.
     *
     * @param team the team
     * @return the integer
     */
    Integer insert(Team team);

    /**
     * Select by page.
     *
     * @param pc      the pc
     * @return the page
     */
    Page selectBy(
            @Param("pc") TeamCondition pc
    );

    /**
     * Select by id team.
     *
     * @param id the id
     * @return the team
     */
    Team selectById(Long id);

    /**
     * Update integer.
     *
     * @param team the team
     * @return the integer
     */
    Integer update(Team team);

    /**
     * Delete by id integer.
     *
     * @param id the id
     * @return the integer
     */
    Integer deleteById(Long id);
}
