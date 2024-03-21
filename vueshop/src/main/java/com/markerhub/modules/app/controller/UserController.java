package com.markerhub.modules.app.controller;

import com.markerhub.common.lang.Result;
import com.markerhub.modules.app.annotation.Login;
import com.markerhub.service.AppUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app")
public class UserController {

	@Resource
	AppUserService appUserService;

	@Login
	@GetMapping("/userInfo")
	public Result getUserInfo()  {
		return Result.success(appUserService.getCurrentUser());
	}

	@GetMapping("/userInfo1")
	public Result getUserInfo1()  {
		return Result.success();
	}
}
