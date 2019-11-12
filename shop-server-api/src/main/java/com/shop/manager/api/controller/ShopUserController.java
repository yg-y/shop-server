package com.shop.manager.api.controller;


import com.shop.manager.api.param.UserParam;
import com.shop.manager.api.service.IShopUserService;
import com.shop.manager.entity.ShopUser;
import com.shop.manager.entity.SysUser;
import com.shop.manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
@RestController
@RequestMapping("/shop_api/shop-user")
public class ShopUserController {

    @Autowired
    IShopUserService iShopUserService;

    @GetMapping("/page")
    public R getPage(@ModelAttribute UserParam userParam){
        return iShopUserService.getPage(userParam);
    }
    @PostMapping("/add/shopUser")
    public R addUser(@RequestBody ShopUser shopUser) {
        return iShopUserService.addUser(shopUser);
    }

    @PostMapping("/edit/shopUser")
    public R editUser(@RequestBody ShopUser shopUser){
        return iShopUserService.editUser(shopUser);
    }

}
