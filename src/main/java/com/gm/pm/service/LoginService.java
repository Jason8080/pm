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
        if(dbLogin!=null){
            login.setRoles(dbLogin.getRoles());
            return TokenKit.generateToken(login);
        }
        return null;
    }

    public String register(Login login) {
        Login dbLogin = loginMapper.select2name(login.getName());
        if(dbLogin==null){
            loginMapper.insert(login);
            return TokenKit.generateToken(login);
        }
        return null;
    }
}