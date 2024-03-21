package com.markerhub.modules.app.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppAddress;
import com.markerhub.modules.app.annotation.Login;
import com.markerhub.service.AppAddressService;
import com.markerhub.service.AppUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/address")
public class AddressController {

	@Resource
	AppAddressService appAddressService;

	@Resource
	AppUserService appUserService;

	@Login
	@GetMapping("/info/{id}")
	public Result info(@PathVariable long id) {

		long userId = appUserService.getCurrentUserId();

		AppAddress appAddress = appAddressService.getById(id);
		Assert.isTrue(appAddress != null && appAddress.getUserId() == userId, "收货地址不存在");
		return Result.success(appAddress);
	}

	@Login
	@GetMapping("/list")
	public Result list() {
		long userId = appUserService.getCurrentUserId();
		return Result.success(appAddressService.list(new QueryWrapper<AppAddress>()
				.eq("user_id", userId)
				.orderByDesc("created")
				));
	}

	@Login
	@PostMapping("/save")
	public Result save(@Validated @RequestBody AppAddress appAddress) {
		appAddressService.saveAndDefault(appAddress);
		return Result.success();
	}

	@Login
	@PostMapping("/delete")
	public Result delete(long id) {
		long userId = appUserService.getCurrentUserId();

		appAddressService.remove(new QueryWrapper<AppAddress>()
				.eq("id", id)
				.eq("user_id", userId));
		return Result.success();
	}

}
