package com.gm.pm.entity;

import lombok.Data;

/**
 * @author Jason
 */
@Data
public class Inventory {
    private Integer prev;
    private Integer current = 0;
    private Integer next;
}
