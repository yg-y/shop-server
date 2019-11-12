package com.shop.manager.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manager.api.mapper.ShopOrderInfoMapper;
import com.shop.manager.api.param.OrderParam;
import com.shop.manager.api.service.IShopOrderInfoService;
import com.shop.manager.entity.ShopOrderInfo;
import com.shop.manager.util.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
@Service
public class ShopOrderInfoServiceImpl extends ServiceImpl<ShopOrderInfoMapper, ShopOrderInfo> implements IShopOrderInfoService {

    @Autowired
    ShopOrderInfoMapper shopOrderInfoMapper;

    @Override
    public R getPage(OrderParam orderParam) {
        QueryWrapper<ShopOrderInfo> queryWrapper = new QueryWrapper<ShopOrderInfo>();
        if (!StringUtils.isEmpty(orderParam.getOrderNumber())) {
            queryWrapper.lambda().like(ShopOrderInfo::getOrderNumber, orderParam.getOrderNumber());
        }
        return R.SUCCESS(shopOrderInfoMapper.page(new Page<>(orderParam.getCurrent(), orderParam.getSize()), queryWrapper));
    }

    @Override
    public R addOrder(ShopOrderInfo shopOrderInfo) {
        if (shopOrderInfo.getNumber() == null || shopOrderInfo.getShopInfoId() == null ||
                shopOrderInfo.getShopInfoId() < 0
        ) {
            return R.ERROR("缺少订单信息");
        }
        shopOrderInfo.setOrderNumber(UUID.randomUUID().toString().replace("-", ""));
        shopOrderInfo.setCreateTime(System.currentTimeMillis());
        shopOrderInfo.setUpdateTime(shopOrderInfo.getCreateTime());
        return this.save(shopOrderInfo) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R editOrder(ShopOrderInfo shopOrderInfo) {
        if (shopOrderInfo.getId() == null || shopOrderInfo.getId() < 0) {
            return R.ERROR("请选择一个订单");
        }
        if (this.getOne(new QueryWrapper<ShopOrderInfo>().lambda().notIn(ShopOrderInfo::getId,shopOrderInfo.getId()).eq(ShopOrderInfo::getOrderNumber, shopOrderInfo.getOrderNumber())) != null) {
            return R.ERROR("订单号重复");
        }
        shopOrderInfo.setUpdateTime(System.currentTimeMillis());

        return this.updateById(shopOrderInfo) ? R.SUCCESS() : R.ERROR();
    }

}
