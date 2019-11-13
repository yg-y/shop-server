package com.shop.manager.api.entity;

import com.shop.manager.entity.ShopOrderInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfo extends ShopOrderInfo {
    private String shopName;
    private String titlePhoto;
    private BigDecimal price;
}
