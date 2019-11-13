package com.shop.manager.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manager.api.mapper.ShopUserMapper;
import com.shop.manager.api.param.UserParam;
import com.shop.manager.api.service.IShopUserService;
import com.shop.manager.entity.ShopUser;
import com.shop.manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

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

    @Autowired
    HttpSession httpSession;

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

        if (StringUtils.isEmpty(shopUser.getPhone()) || StringUtils.isEmpty(shopUser.getPassword())) {
            return R.ERROR("账号或密码必填");
        }
        ShopUser databaseUser = this.getOne(new QueryWrapper<ShopUser>().lambda().eq(ShopUser::getPhone, shopUser.getPhone()));
        if (databaseUser != null) {
            return R.ERROR("用户名称重复");
        }
        shopUser.setName(shopUser.getPhone());
        shopUser.setCreateTime(System.currentTimeMillis());
        shopUser.setUpdateTime(shopUser.getCreateTime());
        return this.save(shopUser) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R editUser(ShopUser shopUser) {
        if (shopUser.getId() < 0) {
            return R.ERROR("请选择一个用户");
        }
        if (this.getOne(new QueryWrapper<ShopUser>().lambda().notIn(ShopUser::getId, shopUser.getId()).eq(ShopUser::getName, shopUser.getName())) != null) {
            return R.ERROR("用户名重复");
        }
        shopUser.setUpdateTime(System.currentTimeMillis());
        return this.updateById(shopUser) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R loginByClient(ShopUser shopUser) {
        if (StringUtils.isEmpty(shopUser.getPhone()) && StringUtils.isEmpty(shopUser.getPassword())) {
            return R.ERROR("账号或密码必填");
        }
        ShopUser dataShopUser = this.getOne(new QueryWrapper<ShopUser>().lambda().eq(ShopUser::getPhone, shopUser.getPhone()));
        if (dataShopUser == null) {
            return R.ERROR("not login");
        }
        if (dataShopUser.getPassword().equals(shopUser.getPassword())) {
            dataShopUser.setPassword(null);
            httpSession.setAttribute("shop_user_" + httpSession.getId(), dataShopUser);
            httpSession.setMaxInactiveInterval(180 * 24 * 60 * 60);
            return R.SUCCESS(dataShopUser);
        }
        return R.ERROR("not login");
    }
}
