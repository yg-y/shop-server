package com.shop.manager.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manager.api.mapper.ShopInfoMapper;
import com.shop.manager.api.param.Param;
import com.shop.manager.api.service.IShopInfoService;
import com.shop.manager.entity.ShopInfo;
import com.shop.manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
@Service
public class ShopInfoServiceImpl extends ServiceImpl<ShopInfoMapper, ShopInfo> implements IShopInfoService {

    @Autowired
    ShopInfoMapper shopInfoMapper;

    @Override
    public R getPage(Param param) {
        QueryWrapper<ShopInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(param.getName())) {
            queryWrapper.lambda().like(ShopInfo::getName, param.getName());
        }
        return R.SUCCESS(this.page(new Page<>(param.getCurrent(), param.getSize()), queryWrapper));
    }

    @Override
    public R addShopInfo(ShopInfo shopInfo) {
        if (StringUtils.isEmpty(shopInfo.getName()) || StringUtils.isEmpty(shopInfo.getPrice())
                || StringUtils.isEmpty(shopInfo.getTitlePhoto()) || StringUtils.isEmpty(shopInfo.getBrand())) {
            return R.ERROR("请完善商品信息");
        }
        shopInfo.setCreateTime(System.currentTimeMillis());
        shopInfo.setUpdateTime(shopInfo.getCreateTime());
        shopInfo.setTypeId(shopInfo.getTypeId() == null || shopInfo.getTypeId() < 0 ? 0 : shopInfo.getTypeId());
        return this.save(shopInfo) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R editShopInfo(ShopInfo shopInfo) {
        if (shopInfo.getId() == null || shopInfo.getId() < 0) {
            return R.ERROR("请选择商品");
        }
        ShopInfo databaseShopInfo = this.getOne(new QueryWrapper<ShopInfo>().lambda().notIn(ShopInfo::getId, shopInfo.getId()).eq(ShopInfo::getName, shopInfo.getName()));
        if (databaseShopInfo != null) {
            return R.ERROR("商品重复");
        }
        shopInfo.setUpdateTime(System.currentTimeMillis());
        return this.updateById(shopInfo) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R getShopSearch(String search) {
        if (StringUtils.isEmpty(search)) {
            return R.SUCCESS();
        }
        return R.SUCCESS(this.getOne(new QueryWrapper<ShopInfo>().lambda().eq(ShopInfo::getId, search)));
    }
}
