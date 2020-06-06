package com.gm.pm.kit;

import com.gm.pm.entity.Project;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 */
public class StageKit {
    public static final Map<String, String> stageMap = new HashMap();

    static {
        stageMap.put("分配", "planTime");
        stageMap.put("调研", "surveyTime");
        stageMap.put("编码", "executeTime");
        stageMap.put("测试", "verifyTime");
        stageMap.put("上线", "onlineTime");
        stageMap.put("验收", "acceptTime");
    }

    /**
     * 根据对象获取映射字段与当前时间的时间差 (单位: 天)
     */
    public static Long getResidualDay(Project project) {
        Date time = project.getCurrentTime();
        long target = time.getTime();
        long current = System.currentTimeMillis();
        long diffDays = (target - current) / (1000 * 3600 * 24);
//        return diffDays == 0 ? (target - current < 0 ? -1 : diffDays) : diffDays;
        return diffDays;
    }

    public static void setCurrentTime(Project project) {
        try {
            String currentStage = project.getCurrentStage();
            String field = stageMap.get(currentStage);
            Class<? extends Project> aClass = project.getClass();
            Field f = aClass.getDeclaredField(field);
            f.setAccessible(true);
            Date currentTime = (Date) f.get(project);
            project.setCurrentTime(currentTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
