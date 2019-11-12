package com.shop.manager.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.manager.api.param.UserParam;
import com.shop.manager.entity.SysUser;
import com.shop.manager.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
public interface ISysUserService extends IService<SysUser> {

    R addUser(SysUser sysUser);

    R getUserPage(UserParam userParam);

    R editUser(SysUser sysUser);
}
