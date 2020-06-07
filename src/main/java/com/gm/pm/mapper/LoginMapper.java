package com.gm.pm.mapper;

import com.gm.pm.entity.Login;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Login mapper.
 *
 * @author Jason
 */
public interface LoginMapper {
    /**
     * Login user.
     *
     * @param login the login
     * @return the user
     */
    Integer login(Login login);

    /**
     * Login login.
     *
     * @param name the name
     * @param pass the pass
     * @return the login
     */
    Login name2pass(@Param("name") String name, @Param("pass") String pass);
}
