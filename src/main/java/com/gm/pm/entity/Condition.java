package com.gm.pm.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 无尽搜索模式
 *
 * @author Jason
 */
@Data
public class Condition {

    private Integer choose = 0;
    private String likes = "";

    public void likeEndless() {
        if (!StringUtils.isEmpty(this.likes)) {
            char[] chars = this.likes.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                sb.append("%");
                sb.append(chars[i]);
            }
            sb.append("%");
            this.likes = sb.toString();
        }
    }

    public Condition likeRecover() {
        if (!StringUtils.isEmpty(this.likes)) {
            this.likes = this.likes.replaceAll("%", "");
        }
        return this;
    }
}
