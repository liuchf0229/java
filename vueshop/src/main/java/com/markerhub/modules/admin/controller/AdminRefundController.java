package com.markerhub.modules.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppOrder;
import com.markerhub.entity.AppRefund;
import com.markerhub.modules.app.controller.BaseController;
import com.markerhub.service.AppOrderService;
import com.markerhub.service.AppRefundService;
import com.markerhub.utils.OrderStatusUtil;
import com.markerhub.utils.RefundStatusUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/refund")
public class AdminRefundController extends BaseController {

	@Resource
	AppRefundService appRefundService;

	@Resource
	AppOrderService appOrderService;

	@GetMapping("/list")
	public Result list(AppRefund appRefund) {
		return Result.success(MapUtil.builder()
				.put("refundStatusList", RefundStatusUtil.getStatusList())
				.put("pageData", appRefundService.pageAdmin(getPage(), appRefund))
				.build()
		);
	}

	@GetMapping("/info/{id}")
	public Result info(@PathVariable Long id) {
		AppRefund refund = appRefundService.getById(id);
		return Result.success(MapUtil.builder()
				.put("refund", refund)
				.put("order", appOrderService.getOrderInfo(refund.getOrderId()))
				.build()
		);
	}

	@PostMapping("/audit")
	public Result audit(@RequestBody AppRefund appRefund) {
		appRefundService.auditAdmin(appRefund);
		return Result.success();
	}
}
