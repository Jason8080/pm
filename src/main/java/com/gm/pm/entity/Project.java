package com.gm.pm.entity;

import com.gm.pm.kit.StageKit;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author Jason
 */
@Data
public class Project {
    private Long id;
    private String name;
    private String pmName;
    private Integer days;
    private String currentStage;
    private Date currentTime;
    private Integer currentSlider;
    private String demand;
    private String completed;
    private String nextPlan;
    private String risk;
    private String remarks;
    private Long residualDay;

    public Long getResidualDay() {
        return this.currentStage!=null?StageKit.getResidualDay(this):this.residualDay;
    }

    /**
     * 计划时间
     */
    private Date planTime;
    /**
     * 调研时间
     */
    private Date surveyTime;
    /**
     * 执行时间: 编码
     */
    private Date executeTime;
    /**
     * 验证时间: 测试
     */
    private Date verifyTime;
    /**
     * 上线时间
     */
    private Date onlineTime;
    /**
     * 验收时间
     */
    private Date acceptTime;
}
