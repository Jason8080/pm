package com.gm.pm.entity;

import com.gm.pm.kit.DayKit;
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
    private Date offtime;
    private Long offDay;
    private Integer saturability;

    public Long getOffDay() {
        return DayKit.getDiffDays(System.currentTimeMillis(), this.offtime);
    }

    private String status;
    private String remarks;
}
