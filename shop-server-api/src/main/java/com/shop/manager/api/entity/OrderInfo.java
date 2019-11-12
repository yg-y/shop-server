package com.shop.manager.api.entity;

import com.shop.manager.entity.ShopOrderInfo;
import lombok.Data;

@Data
public class OrderInfo extends ShopOrderInfo {
    private String shopName;
}
