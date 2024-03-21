package com.markerhub.modules.system.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.markerhub.entity.SysMenu;
import com.markerhub.entity.SysRole;
import com.markerhub.entity.SysUser;
import com.markerhub.service.SysMenuService;
import com.markerhub.service.SysRoleService;
import com.markerhub.service.SysUserService;
import com.markerhub.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AccountRealm extends AuthorizingRealm {

	@Resource
	JwtUtils jwtUtils;

	@Resource
	SysUserService sysUserService;

	@Resource
	SysRoleService sysRoleService;

	@Resource
	SysMenuService sysMenuService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 获取权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		AccountProfile profile = (AccountProfile)principal.getPrimaryPrincipal();
		Long userId = profile.getId();


		// 获取角色
		List<SysRole> roles = sysRoleService.listRolesByUserId(userId);

		// 获取菜单
		List<SysMenu> menus = sysMenuService.listMenusByUserId(userId);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles.stream().map(SysRole::getCode).collect(Collectors.toSet()));
		info.setStringPermissions(menus.stream().map(SysMenu::getPerms).collect(Collectors.toSet()));

		return info;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		JwtToken jwtToken = (JwtToken) authenticationToken;

		Claims claim = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal(), "ADMIN");
		if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
			throw new UnauthenticatedException("请重新登录");
		}

		String userId = claim.getSubject();

		SysUser sysUser = sysUserService.getById(userId);
		if (sysUser == null) {
			throw new UnknownAccountException("账户不存在");
		}
		if (sysUser.getStatus() == 1) {
			throw new LockedAccountException("账户已被锁定");
		}

		AccountProfile profile = new AccountProfile();
		BeanUtil.copyProperties(sysUser, profile);

		return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
	}
}
