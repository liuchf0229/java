package com.markerhub.service;

import com.markerhub.entity.AppSpecificationValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface AppSpecificationValueService extends IService<AppSpecificationValue> {

	List<AppSpecificationValue> listByProductId(Long productId);
}
