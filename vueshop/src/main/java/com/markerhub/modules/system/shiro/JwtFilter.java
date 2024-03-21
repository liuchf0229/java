package com.markerhub.modules.system.shiro;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.markerhub.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends AuthenticatingFilter {

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {

		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String jwt = httpServletRequest.getHeader("Authorization");
		if (StrUtil.isBlank(jwt)) {
			return null;
		}

		return new JwtToken(jwt);
	}

	/**
	 * 是否是拒绝登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String jwt = httpServletRequest.getHeader("Authorization");
		if (StrUtil.isBlank(jwt)) {
			output(response, "请先登录");
			return false;
		}

		return executeLogin(request, response);
	}

	@Override
	public boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
		// 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		Throwable throwable = e.getCause() == null ? e : e.getCause();
		output(response, throwable.getMessage());
		return false;
	}

	private void output(ServletResponse response, String msg) {
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		servletResponse.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("application/json;charset=utf-8");
		try {
			servletResponse.getWriter().print(JSONUtil.toJsonStr(Result.fail(msg)));
		} catch (IOException e) {
			log.error("登录异常 -- {}", e.getMessage());
		}
	}
}
