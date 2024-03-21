package com.markerhub.service;

import com.markerhub.entity.AppAddress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface AppAddressService extends IService<AppAddress> {

	AppAddress getDefault(long userId);

	void saveAndDefault(AppAddress appAddress);
}
