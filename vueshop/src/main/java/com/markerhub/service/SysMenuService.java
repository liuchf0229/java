package com.markerhub.service;

import com.markerhub.common.dto.SysMenuDto;
import com.markerhub.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface SysMenuService extends IService<SysMenu> {

	List<SysMenu> listMenusByUserId(Long userId);

	List<SysMenuDto> getCurrentUserNav();

	String getUserPerms(Long userId);

	List<SysMenu> tree();

	void delete(Long id);
}
