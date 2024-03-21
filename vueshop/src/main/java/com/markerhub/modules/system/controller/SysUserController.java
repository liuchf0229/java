package com.markerhub.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.SysUser;
import com.markerhub.modules.app.controller.BaseController;
import com.markerhub.service.SysRoleService;
import com.markerhub.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Resource
	SysUserService sysUserService;

	@Resource
	SysRoleService sysRoleService;

	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public Result list(SysUser sysUser) {
		return Result.success(sysUserService.getPage(getPage(), sysUser));
	}

	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:user:list")
	public Result info(@PathVariable Long id) {
		SysUser sysUser = sysUserService.getById(id);
		Assert.notNull(sysUser, "该管理员不存在");

		sysUser.setRoles(sysRoleService.listRolesByUserId(id));
		return Result.success(sysUser);
	}

	@PostMapping("/save")
	@RequiresPermissions(value = {"sys:user:save", "sys:user:update"}, logical = Logical.OR)
	public Result save(@Validated @RequestBody SysUser sysUser) {
		if (sysUser.getId() == null) {
			sysUser.setCreated(LocalDateTime.now());
			sysUser.setAvatar("https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/zhuawaba/5a9f48118166308daba8b6da7e466aab.jpg");
		} else {
			sysUser.setUpdated(LocalDateTime.now());

			SysUser temp = sysUserService.getById(sysUser.getId());
			BeanUtil.copyProperties(sysUser, temp, "password", "created", "updated");

			// 密码不为空时候更新密码
			if (StrUtil.isNotBlank(sysUser.getPassword())) {
				temp.setPassword(SecureUtil.md5(sysUser.getPassword()));
			}
		}

		sysUserService.saveOrUpdate(sysUser);
		return Result.success(sysUser);
	}

	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public Result delete(Long[] ids) {
		sysUserService.deleteBath(ids);
		return Result.success();
	}

	@PostMapping("/role/{userId}")
	@RequiresPermissions("sys:user:role")
	public Result role(@PathVariable Long userId, @RequestBody Long[] roleIds) {
		sysUserService.role(userId, roleIds);
		return Result.success();
	}

	@PostMapping("/resetPwd")
	@RequiresPermissions("sys:user:resetPwd")
	public Result resetPwd(Long userId) {
		SysUser sysUser = sysUserService.getById(userId);
		sysUser.setPassword(SecureUtil.md5("888888"));
		sysUser.setUpdated(LocalDateTime.now());
		sysUserService.updateById(sysUser);
		return Result.success();
	}


}
