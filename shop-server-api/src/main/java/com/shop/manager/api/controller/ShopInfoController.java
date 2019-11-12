package com.shop.manager.api.controller;


import com.shop.manager.api.param.Param;
import com.shop.manager.api.service.IShopInfoService;
import com.shop.manager.entity.ShopInfo;
import com.shop.manager.util.R;
import netscape.javascript.JSObject;
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
@RequestMapping("/shop_api/shop-info")
public class ShopInfoController {

    @Autowired
    IShopInfoService iShopInfoService;

    @GetMapping("/page")
    public R list(@ModelAttribute Param param) {
        return iShopInfoService.getPage(param);
    }

    @PostMapping("/add/shopInfo")
    public R addShopInfo(@RequestBody ShopInfo shopInfo) {
        return iShopInfoService.addShopInfo(shopInfo);
    }

    @PostMapping("/edit/shopInfo")
    public R editShopInfo(@RequestBody ShopInfo shopInfo) {
        return iShopInfoService.editShopInfo(shopInfo);
    }

    @GetMapping("/byId/{search}")
    public R getShopSearch(@PathVariable String search){
        return iShopInfoService.getShopSearch(search);
    }
}
