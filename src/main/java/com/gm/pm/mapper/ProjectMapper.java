package com.gm.pm.mapper;

import com.github.pagehelper.Page;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Project mapper.
 *
 * @author Jason
 */
public interface ProjectMapper {
    /**
     * Insert integer.
     *
     * @param project the project
     * @return the integer
     */
    Page selectList(Project project);

    /**
     * Insert integer.
     *
     * @param project the project
     * @return the integer
     */
    Integer insert(Project project);

    /**
     * Select by page.
     *
     * @param project the project
     * @param pc      the pc
     * @return the page
     */
    Page selectBy(
            @Param("project") Project project,
            @Param("pc") ProjectCondition pc
    );
}
