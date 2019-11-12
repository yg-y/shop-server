package com.shop.manager.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.manager.api.param.Param;
import com.shop.manager.entity.ShopInfo;
import com.shop.manager.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
public interface IShopInfoService extends IService<ShopInfo> {

    R getPage(Param param);

    R addShopInfo(ShopInfo shopInfo);

    R editShopInfo(ShopInfo shopInfo);

    R getShopSearch(String search);
}
