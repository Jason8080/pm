package com.gm.pm.mapper;

import com.github.pagehelper.Page;
import com.gm.pm.entity.Project;
import com.gm.pm.entity.ProjectCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    Integer insert(Project project);

    /**
     * Select by page.
     *
     * @param pc the pc
     * @return the page
     */
    Page selectBy(
            @Param("pc") ProjectCondition pc
    );

    /**
     * Select by id project.
     *
     * @param id the id
     * @return the project
     */
    Project selectById(Long id);

    /**
     * Update integer.
     *
     * @param project the project
     * @return the integer
     */
    Integer update(Project project);

    /**
     * Delete by id integer.
     *
     * @param id the id
     * @return the integer
     */
    Integer deleteById(Long id);

    /**
     * On count integer.
     *
     * @param pmName the pm name
     * @return the integer
     */
    Integer onCount(String pmName);
}
