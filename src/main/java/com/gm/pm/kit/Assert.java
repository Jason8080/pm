package com.gm.pm.kit;

import com.gm.pm.entity.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.subject.Subject;

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

    public static Login onLine() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Object login = subject.getPrincipal();
            if(login==null){
                throw new AccountException("登入超时或未登录!");
            }
            return (Login) login;
        } catch (AccountException e) {
            throw new AccountException("登入超时或未登录!");
        }
    }
}
