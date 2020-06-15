package com.gm.pm.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author Jason
 */
@Data
public class Team {
    private Long id;
    private String name;
    private String pmName;
    private String predecessor;
    private Integer concurrency;
    private Integer acceptCount;
    private String tags;
    private String summary;
    private Date beginTime;
    private Integer idleDays;

    private String status;
}
