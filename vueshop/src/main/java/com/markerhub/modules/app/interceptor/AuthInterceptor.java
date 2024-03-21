package com.markerhub.modules.app.interceptor;

import com.markerhub.common.exception.HubException;
import com.markerhub.common.lang.Const;
import com.markerhub.modules.app.annotation.Login;
import com.markerhub.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Resource
	JwtUtils jwtUtils;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 判断是否有@Login注解，没有就放行
		Login annotaion;
		if (handler instanceof HandlerMethod) {
			annotaion = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
		} else {
			return true;
		}

		if (annotaion == null) {
			return true;
		}

		// 获取用户凭证token
		String token = request.getHeader("token");

		// 判断token是否合法
		if (!StringUtils.hasText(token)) {
			throw new UnauthenticatedException("请先登录");
		}

		Claims claim = jwtUtils.getClaimByToken(token, "APP");
		if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
			throw new UnauthenticatedException("请先登录");
		}

		// 把用户信息存到session
		request.getSession().setAttribute(Const.USER_KEY, Long.parseLong(claim.getSubject()));
		return true;
	}
}
