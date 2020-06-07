package com.gm.pm.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 */
@Data
public class Login {
    private String name;
    private String pass;
    private String token;
    private String roles;
    private String remember = "off";
    private String lastIp;
    private Date lastTime;

    public Login() {
    }

    public Login(String token, String name, String roles, Long exp, String ip, String jti) {
        this.token = token;
        this.name = name;
        this.roles = roles;
        this.lastIp = ip;
    }
}
