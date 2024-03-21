package com.markerhub.modules.app.controller;

import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppRefund;
import com.markerhub.modules.app.annotation.Login;
import com.markerhub.service.AppRefundService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/app/refund")
public class RefundController {

	@Resource
	AppRefundService appRefundService;

	@Login
	@GetMapping("/order")
	public Result getOrderData(long orderId) {
		return Result.success(appRefundService.getOrderData(orderId));
	}

	@Login
	@PostMapping("/apply")
	public Result apply(MultipartFile[] pics, @Validated AppRefund appRefund) {
		appRefundService.apply(appRefund, pics);
		return Result.success();
	}


}
