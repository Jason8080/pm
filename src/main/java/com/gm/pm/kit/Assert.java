package com.gm.pm.kit;

/**
 * @author Jason
 */
public class Assert {
    public static <T> T notEmpty(T t) {
        if (t == null || (t instanceof String && ((String) t).isEmpty())) {
            throw new NullPointerException("非空断言失败");
        }
        return t;
    }
}
