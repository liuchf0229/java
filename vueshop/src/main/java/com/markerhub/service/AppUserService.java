package com.markerhub.service;

import com.markerhub.common.dto.LoginDto;
import com.markerhub.entity.AppUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface AppUserService extends IService<AppUser> {

	AppUser login(LoginDto loginDto);

	AppUser getCurrentUser();

	long getCurrentUserId();
}
