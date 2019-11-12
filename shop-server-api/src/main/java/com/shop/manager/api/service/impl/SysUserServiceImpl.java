package com.shop.manager.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manager.api.mapper.SysUserMapper;
import com.shop.manager.api.param.UserParam;
import com.shop.manager.api.service.ISysUserService;
import com.shop.manager.entity.SysUser;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public R addUser(SysUser sysUser) {

        if (StringUtils.isEmpty(sysUser.getName()) || StringUtils.isEmpty(sysUser.getPassword())) {
            return R.ERROR("账号或密码必填");
        }
        SysUser databaseUser = this.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, sysUser.getName()));
        if (databaseUser != null) {
            return R.ERROR("用户名称重复");
        }
        sysUser.setCreateTime(System.currentTimeMillis());
        sysUser.setUpdateTime(sysUser.getCreateTime());
        return this.save(sysUser) ? R.SUCCESS() : R.ERROR();
    }

    @Override
    public R getUserPage(UserParam userParam) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        if (!StringUtils.isEmpty(userParam.getName())) {
            queryWrapper.lambda().like(SysUser::getName, userParam.getName());
        }
        return R.SUCCESS(this.page(new Page<>(userParam.getCurrent(), userParam.getSize()), queryWrapper));
    }

    @Override
    public R editUser(SysUser sysUser) {
        if (sysUser.getId() < 0) {
            return R.ERROR("请选择一个用户");
        }
        if (this.getOne(new QueryWrapper<SysUser>().lambda().notIn(SysUser::getId,sysUser.getId()).eq(SysUser::getName, sysUser.getName())) != null) {
            return R.ERROR("用户名重复");
        }
        sysUser.setUpdateTime(System.currentTimeMillis());
        return this.updateById(sysUser) ? R.SUCCESS() : R.ERROR();
    }
}
