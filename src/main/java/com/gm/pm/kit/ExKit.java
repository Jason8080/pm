package com.gm.pm.kit;

/**
 * @author Jason
 */
public class ExKit {

    /**
     * 不抛异常, 不打印异常的工具类
     * @param run
     */
    public static void run(Runnable run){
        try {
            run.run();
        } catch (Exception e) {
        }
    }

}
