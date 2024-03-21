package com.markerhub.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.common.exception.HubException;
import com.markerhub.entity.AppComment;
import com.markerhub.entity.AppOrder;
import com.markerhub.entity.AppOrderItem;
import com.markerhub.entity.AppProduct;
import com.markerhub.mapper.AppCommentMapper;
import com.markerhub.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@Service
public class AppCommentServiceImpl extends ServiceImpl<AppCommentMapper, AppComment>
    implements AppCommentService{

	@Resource
	AppCommentMapper appCommentMapper;

	@Resource
	AppOrderService appOrderService;

	@Resource
	AppUserService appUserService;

	@Resource
	AppOrderItemService appOrderItemService;

	@Resource
	AppUploadService appUploadService;

	@Resource
	AppProductService appProductService;

	@Override
	public Object pageByProductId(Page page, long productId) {
		return appCommentMapper.pageByProductId(page, productId);
	}

	/**
	 *
	 * @param page
	 * @param status 0 未评论，1 已评论
	 * @return
	 */
	@Override
	public Object getPage(Page page, Integer status) {

		long userId = appUserService.getCurrentUserId();

		// 1、已完成的订单列表的id集合
		List<Object> orderIds = appOrderService.listObjs(new QueryWrapper<AppOrder>()
				.eq("user_id", userId)
				.eq("order_status", 3)
				.select("id")
		);

		// 2、再获取已完成订单列表的所有的待评论的商品
		Page<AppOrderItem> orderItems = page;

		if (orderIds.size() > 0) {
			orderItems = appOrderItemService.page(page, new QueryWrapper<AppOrderItem>()
					.in("order_id", orderIds)
					.isNull(status == 0, "comment_id")
					.isNotNull(status == 1, "comment_id")
					.orderByAsc("created")
			);
		}
		return orderItems;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public long post(MultipartFile[] pics, AppComment appComment) {

		// 1、条件判断
		AppOrderItem appOrderItem = appOrderItemService.getById(appComment.getOrderItemId());
		Assert.isNull(appOrderItem.getCommentId(), "请勿重复评论");

		AppOrder appOrder = appOrderService.getOwnById(appOrderItem.getOrderId());
		Assert.isTrue(appOrder.getOrderStatus() == 3, "该订单无法评论");

		// 图片处理
		if (pics != null && pics.length > 0) {
			try {
				List<String> picUrls = appUploadService.upload(pics, 0.5f, 0.2);
				appComment.setImages(StrUtil.join(";", picUrls));

			} catch (IOException e) {
				e.printStackTrace();
				throw new HubException("图片上传失败");
			}
		}

		appComment.setOrderId(appOrder.getId());
		appComment.setProductId(appOrderItem.getProductId());
		appComment.setUserId(appOrder.getUserId());
		appComment.setReply(null);
		appComment.setCreated(LocalDateTime.now());

		this.save(appComment);

		appOrderItem.setCommentId(appComment.getId());
		appOrderItem.setCommentTime(LocalDateTime.now());
		appOrderItemService.updateById(appOrderItem);

		// 商品评论数量加一
		appProductService.update(new UpdateWrapper<AppProduct>()
				.eq("id", appOrderItem.getProductId())
				.setSql("comments = comments + 1")
		);
		return appComment.getId();
	}
}




