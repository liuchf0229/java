package com.markerhub.common.exception;

import com.markerhub.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HubException.class)
	public Result handler(HubException e) {
		log.error("自定义异常 ---- {}", e.getMessage());
		return Result.fail(e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public Result handler(RuntimeException e) {
		log.error("运行时异常 ---- {}", e.getMessage());
		e.printStackTrace();
		return Result.fail(e.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public Result handler(NoHandlerFoundException e) {
		log.error("404异常 ---- {}", e.getMessage());
		return Result.fail(e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BindException.class)
	public Result handler(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

		log.error("实体校验异常 ---- {}", objectError.getDefaultMessage());
		return Result.fail(objectError.getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = UnauthenticatedException.class)
	public Result handler(UnauthenticatedException e) {
		log.error("未登录异常：----------------{}", e.getMessage());
		return Result.fail(e.getMessage());
	}
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = ShiroException.class)
	public Result handler(ShiroException e) {
		log.error("shiro异常：----------------{}", e.getMessage());
		return Result.fail(e.getMessage());
	}
}
