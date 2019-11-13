package com.shop.manager.api.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.manager.api.entity.OrderInfo;
import com.shop.manager.entity.ShopOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
public interface ShopOrderInfoMapper extends BaseMapper<ShopOrderInfo> {

    Page<OrderInfo> page(Page<ShopOrderInfo> page, @Param("ew") QueryWrapper<ShopOrderInfo> queryWrapper);
    List<OrderInfo> page(@Param("ew") QueryWrapper<ShopOrderInfo> queryWrapper);
}
