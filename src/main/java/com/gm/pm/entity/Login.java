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
    private String remember = "off";
    private String lastIp;
    private Date lastTime;
}
