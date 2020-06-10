package com.gm.pm.config;

import com.gm.pm.entity.Login;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author Jason
 */
public class JwtAuthenticationToken extends UsernamePasswordToken {
    private Login login;
    private String token;

    public JwtAuthenticationToken(Login login, String token) {
        this.login = login;
        this.token = token;
    }

    @Override
    public Login getPrincipal() {
        return login;
    }

    @Override
    public String getCredentials() {
        return token;
    }
}
