package com.gm.pm.service;

import com.gm.pm.config.JwtAuthenticationToken;
import com.gm.pm.entity.Login;
import com.gm.pm.mapper.LoginMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;


    public Login login(Login login) {
        Subject subject = SecurityUtils.getSubject();
        Login db = loginMapper.name2pass(login.getName(), login.getPass());
        AuthenticationToken auth = new JwtAuthenticationToken(db, db.getPass());
        SecurityUtils.getSecurityManager().login(subject, auth);
        return db;
    }

    public String register(Login login) {
        Login dbLogin = loginMapper.select2name(login.getName());
        if(dbLogin==null){
            loginMapper.insert(login);
            return "TokenKit.generateToken(login)";
        }
        return null;
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        SecurityUtils.getSecurityManager().logout(subject);
    }
}
