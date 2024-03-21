package com.markerhub.service;

import com.markerhub.common.dto.AppSkuDto;
import com.markerhub.entity.AppOrder;
import com.markerhub.entity.AppOrderItem;
import com.markerhub.entity.AppSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface AppSkuStockService extends IService<AppSkuStock> {

	List<AppSkuStock> listByProductId(Long productId);

	AppSkuStock getSku(Long skuId, Long productId);

	void reduceStock(List<AppOrderItem> orderItems);

	void releaseStock(AppOrder appOrder);

	Object getSkuByProductId(Long productId);

	void saveSku(AppSkuDto skuDto);
}
