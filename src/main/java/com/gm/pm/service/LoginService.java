package com.gm.pm.service;

import com.gm.pm.entity.Login;
import com.gm.pm.kit.TokenKit;
import com.gm.pm.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;


    public String login(Login login) {
        Login dbLogin = loginMapper.name2pass(login.getName(), login.getPass());
        login.setRoles(dbLogin.getRoles());
        return dbLogin!=null?TokenKit.generateToken(login):null;
    }
}
