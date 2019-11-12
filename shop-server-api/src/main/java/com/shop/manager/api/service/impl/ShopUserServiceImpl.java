package com.shop.manager.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manager.api.mapper.ShopUserMapper;
import com.shop.manager.api.param.UserParam;
import com.shop.manager.api.service.IShopUserService;
import com.shop.manager.entity.ShopUser;
import com.shop.manager.util.R;
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
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUser> implements IShopUserService {

    @Override
    public R getPage(UserParam userParam) {
        QueryWrapper<ShopUser> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userParam.getName())) {
            queryWrapper.lambda().like(ShopUser::getName, userParam.getName());
        }
        return R.SUCCESS(this.page(new Page<>(userParam.getCurrent(), userParam.getSize()), queryWrapper));
    }

    @Override
    public R addUser(ShopUser shopUser) {

        if (StringUtils.isEmpty(shopUser.getName()) || StringUtils.isEmpty(shopUser.getPassword())) {
            return R.ERROR("账号或密码必填");
        }
        ShopUser databaseUser = this.getOne(new QueryWrapper<ShopUser>().lambda().eq(ShopUser::getName, shopUser.getName()));
        if (databaseUser != null) {
            return R.ERROR("用户名称重复");
        }
        shopUser.setCreateTime(System.currentTimeMillis());
        shopUser.setUpdateTime(shopUser.getCreateTime());
        return this.save(shopUser) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R editUser(ShopUser shopUser) {
        if (shopUser.getId() < 0) {
            return R.ERROR("请选择一个用户");
        }
        if (this.getOne(new QueryWrapper<ShopUser>().lambda().notIn(ShopUser::getId,shopUser.getId()).eq(ShopUser::getName, shopUser.getName())) != null) {
            return R.ERROR("用户名重复");
        }
        shopUser.setUpdateTime(System.currentTimeMillis());
        return this.updateById(shopUser) ? R.SUCCESS() : R.ERROR();
    }
}
