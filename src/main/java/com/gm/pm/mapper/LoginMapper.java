package com.gm.pm.mapper;

import com.gm.pm.entity.Login;
import com.gm.pm.entity.User;

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
}
