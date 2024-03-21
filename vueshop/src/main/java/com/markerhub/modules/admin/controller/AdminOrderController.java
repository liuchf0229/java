package com.markerhub.modules.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppOrder;
import com.markerhub.modules.app.controller.BaseController;
import com.markerhub.service.AppOrderService;
import com.markerhub.utils.OrderStatusUtil;
import com.markerhub.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {

	@Resource
	AppOrderService appOrderService;

	@GetMapping("/list")
	public Result list(AppOrder appOrder) {
		return Result.success(MapUtil.builder()
				.put("orderStatusList", OrderStatusUtil.getStatusList())
				.put("pageData", appOrderService.pageAdmin(getPage(), appOrder))
				.build()
		);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable Long id) {
		return Result.success(appOrderService.getOrderInfo(id));
	}

	@PostMapping("/ship")
	public Result ship(@RequestBody AppOrder appOrder) {
		appOrderService.shipAdmin(appOrder);
		return Result.success();
	}

	@GetMapping("/deliveryInfo")
	public Result deliveryInfo(Long id) throws Exception {
		AppOrder order = appOrderService.getById(id);
		return Result.success(appOrderService.getDeliveryInfo(order));
	}

	@PostMapping("/close")
	public Result close(@RequestBody AppOrder appOrder) {
		Long adminId = ShiroUtil.getProfile().getId();
		appOrderService.closeAdmin(appOrder.getId(), appOrder.getAdminNote(), adminId);
		return Result.success();
	}

}
