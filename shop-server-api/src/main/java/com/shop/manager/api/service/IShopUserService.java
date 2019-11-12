package com.shop.manager.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.manager.api.param.UserParam;
import com.shop.manager.entity.ShopUser;
import com.shop.manager.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
public interface IShopUserService extends IService<ShopUser> {

    R getPage(UserParam userParam);

    R addUser(ShopUser shopUser);

    R editUser(ShopUser shopUser);
}
