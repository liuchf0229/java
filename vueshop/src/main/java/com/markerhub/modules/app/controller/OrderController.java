package com.markerhub.modules.app.controller;

import cn.hutool.core.map.MapUtil;
import com.markerhub.common.dto.OrderDto;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppOrder;
import com.markerhub.modules.app.annotation.Login;
import com.markerhub.modules.app.annotation.NoRepeatSubmit;
import com.markerhub.service.AppOrderService;
import com.markerhub.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/order")
public class OrderController extends BaseController {

	@Resource
	AppUserService appUserService;

	@Resource
	AppOrderService appOrderService;

	@Login
	@PostMapping("/preview")
	public Result preview(@RequestBody OrderDto orderDto) {
		return Result.success(appOrderService.preview(orderDto));
	}

	@Login
	@NoRepeatSubmit
	@PostMapping("/create")
	public Result create(@RequestBody OrderDto orderDto) {
		return Result.success(appOrderService.create(orderDto));
	}

	@Login
	@GetMapping("/count")
	public Result count() {
		return Result.success(appOrderService.getOrderCount());
	}

	@Login
	@GetMapping("/list")
	public Result list(Integer status) {
		long userId = appUserService.getCurrentUserId();
		return Result.success(appOrderService.getPage(getPage(), userId, status));
	}

	@Login
	@GetMapping("/detail/{id}")
	public Result detail(@PathVariable Long id) {
		return Result.success(appOrderService.detail(id));
	}

	@Login
	@PostMapping("/cancel")
	public Result cancel(Long id) {
		appOrderService.cancel(id);
		return Result.success();
	}

	@Login
	@PostMapping("/delete")
	public Result delete(Long id) {
		appOrderService.delete(id);
		return Result.success();
	}

	@Login
	@GetMapping("/deliveryInfo")
	public Result deliveryInfo(Long id) throws Exception {
		AppOrder appOrder = appOrderService.getOwnById(id);
		return Result.success(
				MapUtil.builder()
						.put("deliveryInfo", appOrderService.getDeliveryInfo(appOrder))
						.put("deliveryCompany", appOrder.getDeliveryCompany())
						.put("deliverySn", appOrder.getDeliverySn())
						.build()
		);
	}

	@Login
	@PostMapping("/confirm")
	public Result confirm(Long id) {
		appOrderService.confirm(id);
		return Result.success();
	}
}
