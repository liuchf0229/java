package com.markerhub.modules.admin.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.service.AppUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUploadController {

	@Resource
	AppUploadService appUploadService;

	@PostMapping("/upload")
	public Result upload(MultipartFile[] pics) throws IOException {
		List<String> picPaths = appUploadService.upload(pics, 0.5F, 0.2);
//		List<String> picPaths = Arrays.asList("https://shop-1253594807.cos.ap-nanjing.myqcloud.com/image/9ebb59b8-22fa-4092-95ac-981e0dffbe57.jpg");

		return Result.success(picPaths);
	}

}
