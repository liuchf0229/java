package com.markerhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.AppRefund;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 */
public interface AppRefundService extends IService<AppRefund> {

	Object getOrderData(long orderId);

	void apply(AppRefund appRefund, MultipartFile[] pics);

	Object pageAdmin(Page page, AppRefund appRefund);

	void auditAdmin(AppRefund appRefund);
}
