package com.shop.manager.api.param;

import lombok.Data;

/**
 * 分页
 */
@Data
public class PageParent {
    private long current = 1;
    private long size = 10;
}
