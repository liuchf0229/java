package com.markerhub.mapper;

import com.markerhub.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.markerhub.entity.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<Long> getMenuIdsByUserId(Long userId);
}




