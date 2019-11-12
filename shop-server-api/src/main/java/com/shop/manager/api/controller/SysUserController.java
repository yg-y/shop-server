package com.shop.manager.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.manager.api.param.UserParam;
import com.shop.manager.api.service.ISysUserService;
import com.shop.manager.entity.SysUser;
import com.shop.manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-09
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    HttpSession session;

    @PostMapping("/login")
    public R login(@RequestBody SysUser user) {
        SysUser sysUser = iSysUserService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, user.getName()));
        if (sysUser == null) {
            return R.ERROR();
        }
        if (!sysUser.getPassword().equals(user.getPassword())) {
            return R.ERROR("账号或密码错误，请重试");
        }
        session.setAttribute(session.getId(), sysUser);
        session.setMaxInactiveInterval(12 * 60 * 60);
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return R.SUCCESS(map);
    }

    @GetMapping("/info")
    public R info() {
        Map<String, Object> map = new HashMap<>();
        List<String> role = new ArrayList<>();
        SysUser sysUser = (SysUser) session.getAttribute(session.getId());
        if (sysUser == null) {
            return R.ERROR("not login");
        }
        role.add("admin");
        map.put("roles", role);
        map.put("name", sysUser.getName());
        return R.SUCCESS(map);
    }

    @PostMapping("/logout")
    public R logout() {
        return R.SUCCESS();
    }

    @GetMapping("/page")
    public R getUserPage(@ModelAttribute UserParam userParam) {
        return iSysUserService.getUserPage(userParam);
    }

    @PostMapping("/add/sysUser")
    public R addUser(@RequestBody SysUser sysUser) {
        return iSysUserService.addUser(sysUser);
    }

    @PostMapping("/edit/sysUser")
    public R editUser(@RequestBody SysUser sysUser) {
        return iSysUserService.editUser(sysUser);
    }

}
