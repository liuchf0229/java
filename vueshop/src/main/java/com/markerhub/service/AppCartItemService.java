package com.markerhub.service;

import com.markerhub.entity.AppCartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public interface AppCartItemService extends IService<AppCartItem> {

	List<AppCartItem> getCurrent();

	long getCount();

	Object getTotal(ArrayList<Long> ids);

	void updateQuantity(Long cartItemId, Integer quantity);

	void add(AppCartItem appCartItem);

	AppCartItem getCombinedCartItem(long userId, Long productId, Long skuId, Integer quantity);
}
