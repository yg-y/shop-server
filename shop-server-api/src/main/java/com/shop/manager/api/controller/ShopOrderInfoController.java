package com.shop.manager.api.controller;


import com.shop.manager.api.param.OrderParam;
import com.shop.manager.api.service.IShopOrderInfoService;
import com.shop.manager.entity.ShopOrderInfo;
import com.shop.manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
@RestController
@RequestMapping("/shop_api/shop-order-info")
public class ShopOrderInfoController {

    @Autowired
    IShopOrderInfoService iShopOrderInfoService;

    @GetMapping("/page")
    public R getPage(@ModelAttribute OrderParam orderParam) {
        return iShopOrderInfoService.getPage(orderParam);
    }

    @PostMapping("/add/order")
    public R addOrder(@RequestBody ShopOrderInfo shopOrderInfo) {
        return iShopOrderInfoService.addOrder(shopOrderInfo);
    }

    @PostMapping("/edit/order")
    public R editOrder(@RequestBody ShopOrderInfo shopOrderInfo) {
        return iShopOrderInfoService.editOrder(shopOrderInfo);
    }
}
