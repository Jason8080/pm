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
    private String roles;
    private String remember = "off";
    private String ip;
    private Date exp;
    private Integer status = 0;


    public Login() {
    }

    public Login(String name, String roles, String ip) {
        this.name = name;
        this.roles = roles;
        this.ip = ip;
    }
}
