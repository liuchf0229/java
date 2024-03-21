package com.markerhub.modules.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.SysRole;
import com.markerhub.entity.SysRoleMenu;
import com.markerhub.modules.app.controller.BaseController;
import com.markerhub.service.SysRoleMenuService;
import com.markerhub.service.SysRoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

	@Resource
	SysRoleService sysRoleService;

	@Resource
	SysRoleMenuService sysRoleMenuService;

	@GetMapping("/list")
	@RequiresPermissions("sys:role:list")
	public Result list(SysRole sysRole) {
		Page pageData = sysRoleService.page(getPage(), new QueryWrapper<SysRole>()
				.like(StrUtil.isNotBlank(sysRole.getName()), "name", sysRole.getName())
				.like(StrUtil.isNotBlank(sysRole.getCode()), "code", sysRole.getCode())
				.orderByDesc("created"));

		return Result.success(pageData);
	}

	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:role:list")
	public Result info(@PathVariable Long id) {
		SysRole sysRole = sysRoleService.getById(id);

		List<Long> menuIds = sysRoleMenuService.listObjs(new QueryWrapper<SysRoleMenu>()
				.eq("role_id", id).select("menu_id"),
				o -> Long.parseLong(o.toString()));

		sysRole.setMenuIds(menuIds);
		return Result.success(sysRole);
	}

	@PostMapping("/save")
	@RequiresPermissions(value = {"sys:role:save", "sys:role:update"}, logical = Logical.OR)
	public Result save(@Validated @RequestBody SysRole sysRole) {
		if (sysRole.getId() == null) {
			sysRole.setCreated(LocalDateTime.now());
		} else {
			sysRole.setUpdated(LocalDateTime.now());
		}

		sysRoleService.saveOrUpdate(sysRole);
		return Result.success(sysRole);
	}

	@PostMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public Result delete(@RequestBody Long[] ids) {
		sysRoleService.deleteBath(ids);
		return Result.success();
	}

	@PostMapping("/perm/{roleId}")
	@RequiresPermissions("sys:role:perm")
	public Result perm(@PathVariable Long roleId, @RequestBody Long[] menuIds) {
		sysRoleService.perm(roleId, menuIds);
		return Result.success();
	}

}
