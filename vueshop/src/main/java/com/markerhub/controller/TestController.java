package com.markerhub.controller;

import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppUser;
import com.markerhub.service.AppUploadService;
import com.markerhub.service.AppUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class TestController {

	@Resource
	AppUserService appUserService;

	@Resource
	AppUploadService appUploadService;

	@GetMapping("/test")
	public Object test() {
		return appUserService.list();
	}

	@PostMapping("/testSave")
	public Object testSave(@Validated @RequestBody AppUser appUser) {
		return Result.success(appUser);
	}

	@PostMapping("/post")
	public Object post(MultipartFile[] pics) throws IOException {
		List<String> picPaths = appUploadService.upload(pics, 0.5F, 0.2);
		return picPaths;
	}
}
