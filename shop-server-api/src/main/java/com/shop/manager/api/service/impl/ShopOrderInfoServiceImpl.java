package com.shop.manager.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manager.api.mapper.ShopOrderInfoMapper;
import com.shop.manager.api.param.OrderParam;
import com.shop.manager.api.service.IShopInfoService;
import com.shop.manager.api.service.IShopOrderInfoService;
import com.shop.manager.entity.ShopInfo;
import com.shop.manager.entity.ShopOrderInfo;
import com.shop.manager.entity.ShopUser;
import com.shop.manager.util.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    HttpSession httpSession;

    @Autowired
    ShopOrderInfoMapper shopOrderInfoMapper;

    @Autowired
    IShopInfoService iShopInfoService;

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
        if (shopOrderInfo.getNumber() == null || shopOrderInfo.getShopInfoIds() == null
        ) {
            return R.ERROR("缺少订单信息");
        }
        ShopUser shopUser = (ShopUser) httpSession.getAttribute("shop_user_" + httpSession.getId());
        if (shopUser == null) {
            return R.ERROR("not login");
        }
        List<String> shopIds = Arrays.asList(shopOrderInfo.getShopInfoIds().split(","));
        List<ShopOrderInfo> addShopOrderInfos = new ArrayList<>();
        for (String shopId : shopIds) {
            ShopInfo shopInfo = iShopInfoService.getById(shopId);
            shopOrderInfo = new ShopOrderInfo();
            shopOrderInfo.setShopInfoId(shopInfo.getId());
            shopOrderInfo.setPaymentPrice(shopInfo.getPrice());
            shopOrderInfo.setUserId(shopUser.getId());
            shopOrderInfo.setOrderNumber(UUID.randomUUID().toString().replace("-", ""));
            shopOrderInfo.setCreateTime(System.currentTimeMillis());
            shopOrderInfo.setUpdateTime(shopOrderInfo.getCreateTime());
            addShopOrderInfos.add(shopOrderInfo);
        }
        return this.saveBatch(addShopOrderInfos) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R editOrder(ShopOrderInfo shopOrderInfo) {
        if (shopOrderInfo.getIds() == null) {
            return R.ERROR("请选择一个订单");
        }
        List<String> orderIds = Arrays.asList(shopOrderInfo.getIds().split(","));
        List<ShopOrderInfo> shopOrderInfos = new ArrayList<>();
        for (String orderId : orderIds) {
            ShopOrderInfo shopOrderInfoData = this.getById(orderId);
            ShopInfo shopInfo = iShopInfoService.getById(shopOrderInfoData.getShopInfoId());
            shopOrderInfo = new ShopOrderInfo();
            shopOrderInfo.setId(shopOrderInfoData.getId());
            shopOrderInfo.setPaymentPrice(shopInfo.getPrice());
            shopOrderInfo.setStatus(1);
            shopOrderInfo.setUpdateTime(System.currentTimeMillis());
            shopOrderInfos.add(shopOrderInfo);
        }
        return this.updateBatchById(shopOrderInfos) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R getOrderStatus(String status) {
        QueryWrapper<ShopOrderInfo> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.lambda().eq(ShopOrderInfo::getStatus, status);
        }
        ShopUser shopUser = (ShopUser) httpSession.getAttribute("shop_user_" + httpSession.getId());
        if (shopUser == null) {
            return R.ERROR("not login");
        }
        queryWrapper.lambda().eq(ShopOrderInfo::getUserId, shopUser.getId());
        return R.SUCCESS(shopOrderInfoMapper.page(queryWrapper));
    }

}
