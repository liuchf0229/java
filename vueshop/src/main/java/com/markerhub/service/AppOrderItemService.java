package com.markerhub.service;

import com.markerhub.entity.AppOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface AppOrderItemService extends IService<AppOrderItem> {

	List<AppOrderItem> listByOrderId(Long orderId);
}
