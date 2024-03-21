package com.markerhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {

	Page<SysUser> getPage(Page page, SysUser sysUser);

	void deleteBath(Long[] ids);

	void role(Long userId, Long... roleIds);
}
