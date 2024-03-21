package com.markerhub.service;

public interface AppPayService {
	Object pay(String orderSn);

	Object payCheck(String orderSn);

	void refund(Long refundId);
}
