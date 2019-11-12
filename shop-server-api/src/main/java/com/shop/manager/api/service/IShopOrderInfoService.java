package com.shop.manager.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.manager.api.param.OrderParam;
import com.shop.manager.entity.ShopOrderInfo;
import com.shop.manager.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
public interface IShopOrderInfoService extends IService<ShopOrderInfo> {

    R getPage(OrderParam orderParam);

    R addOrder(ShopOrderInfo shopOrderInfo);

    R editOrder(ShopOrderInfo shopOrderInfo);
}
