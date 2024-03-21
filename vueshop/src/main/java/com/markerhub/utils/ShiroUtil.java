package com.markerhub.utils;

import com.markerhub.modules.system.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

	public static AccountProfile getProfile() {
		return  (AccountProfile) SecurityUtils.getSubject().getPrincipal();
	}
}
