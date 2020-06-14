package com.gm.pm.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jason
 */
@Data
public class Login implements Serializable {
    private String name;
    private String pass;
    private String roles;
    private String remember = "off";
    private String ip;
    private Date exp;
    private Integer status = 0;


    public Login() {
    }

    public Login(Login login) {
        if(login!=null){
            this.name = login.name;
            this.pass = login.pass;
            this.remember = login.remember;
            this.roles = login.roles;
            this.ip = login.ip;
            this.exp = login.exp;
            this.status = login.status;
        }
    }

    public Login(String name, String roles, String ip) {
        this.name = name;
        this.roles = roles;
        this.ip = ip;
    }

    public Login(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}
