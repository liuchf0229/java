package com.markerhub.modules.system.controller;

import com.markerhub.common.lang.Result;
import com.markerhub.entity.SysMenu;
import com.markerhub.service.SysMenuService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

	@Resource
	SysMenuService sysMenuService;

	@GetMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public Result list() {
		return Result.success(sysMenuService.tree());
	}

	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:menu:list")
	public Result info(@PathVariable Long id) {
		return Result.success(sysMenuService.getById(id));
	}

	@PostMapping("/save")
	@RequiresPermissions(value = {"sys:menu:save", "sys:menu:update"}, logical = Logical.OR)
	public Result save(@Validated @RequestBody SysMenu sysMenu) {
		if (sysMenu.getId() == null) {
			sysMenu.setCreated(LocalDateTime.now());
		} else {
			sysMenu.setUpdated(LocalDateTime.now());
		}

		sysMenuService.saveOrUpdate(sysMenu);
		return Result.success(sysMenu);
	}

	@PostMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public Result delete(Long id) {
		sysMenuService.delete(id);
		return Result.success();
	}

}
