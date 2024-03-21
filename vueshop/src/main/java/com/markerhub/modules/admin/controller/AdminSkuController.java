package com.markerhub.modules.admin.controller;

import com.markerhub.common.dto.AppSkuDto;
import com.markerhub.common.lang.Result;
import com.markerhub.modules.app.controller.BaseController;
import com.markerhub.service.AppSkuStockService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sku")
public class AdminSkuController extends BaseController {

	@Resource
	AppSkuStockService appSkuStockService;

	@GetMapping("/product/{productId}")
	public Result sku(@PathVariable Long productId) {
		return Result.success(appSkuStockService.getSkuByProductId(productId));
	}

	@PostMapping("/save")
	public Result save(@Validated @RequestBody AppSkuDto skuDto) {

		appSkuStockService.saveSku(skuDto);
		return Result.success();
	}

}
