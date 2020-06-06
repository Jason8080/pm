package com.gm.pm.entity;

import lombok.Data;

import java.util.Arrays;

/**
 * @author Jason
 */
@Data
public class ProjectCondition {
    private Boolean overdue;
    private String likes;

    public void likeEndless() {
        if(this.likes!=null){
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
        if(this.likes!=null){
            this.likes = this.likes.replaceAll("%", "");
        }
        return this;
    }
}
