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
        TokenKit.generateToken(login);
        Integer num = loginMapper.login(login);
        return num>0?"ok":"用户名或密码错误!";
    }
}
