package com.gm.pm.entity;

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
    private String currentStage;
    private Integer currentSlider;
    private String demand;
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
     * 上线时间
     */
    private Date acceptTime;
}