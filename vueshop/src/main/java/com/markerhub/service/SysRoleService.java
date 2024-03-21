package com.markerhub.service;

import com.markerhub.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface SysRoleService extends IService<SysRole> {

	List<SysRole> listRolesByUserId(Long userId);

	void deleteBath(Long... ids);

	void perm(Long roleId, Long... menuIds);
}
