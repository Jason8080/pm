package com.gm.pm.mapper;

import com.github.pagehelper.Page;
import com.gm.pm.entity.Project;

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
}
