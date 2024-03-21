package com.markerhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.AppProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface AppProductService extends IService<AppProduct> {

	Object pageByCategoryId(Page page, Long categoryId, Integer sort);

	AppProduct getWithAttrsById(Long productId);

	Page<AppProduct> pageWithCategory(Page page, String name, Long categoryId);

	void create(AppProduct appProduct);
}
