package com.gm.pm.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author Jason
 */
@Data
public class ProjectCondition {
    private Integer choose;
    private String likes;

    public void likeEndless() {
        if(!StringUtils.isEmpty(this.likes)){
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
    public ProjectCondition likeRecover() {
        if(!StringUtils.isEmpty(this.likes)){
            this.likes = this.likes.replaceAll("%", "");
        }
        return this;
    }
}
