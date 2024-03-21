package com.markerhub.modules.system.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.dto.LoginDto;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.SysUser;
import com.markerhub.modules.system.shiro.AccountProfile;
import com.markerhub.service.SysMenuService;
import com.markerhub.service.SysUserService;
import com.markerhub.utils.JwtUtils;
import com.markerhub.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys")
public class SysLoginController {

	@Resource
	SysUserService sysUserService;

	@Resource
	JwtUtils jwtUtils;

	@Resource
	SysMenuService sysMenuService;

	@PostMapping("/login")
	public Result login(@Validated @RequestBody LoginDto loginDto) {

		// 校验用户名密码
		SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", loginDto.getUsername()));
		Assert.notNull(sysUser, "用户名或密码错误");

		if (!sysUser.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
			return Result.fail("用户名或密码错误");
		}

		// 生成token
		String token = jwtUtils.generateToken(sysUser.getId(), "ADMIN");
		return Result.success(MapUtil.builder()
				.put("token", token)
				.build()
		);
	}

	@GetMapping("/userInfo")
	public Result userInfo() {
		AccountProfile profile = ShiroUtil.getProfile();

		return Result.success(MapUtil.builder()
				.put("userInfo", profile)
				.put("menus", sysMenuService.getCurrentUserNav())
				.put("auths", sysMenuService.getUserPerms(profile.getId()))
				.build()
		);

	}

}
