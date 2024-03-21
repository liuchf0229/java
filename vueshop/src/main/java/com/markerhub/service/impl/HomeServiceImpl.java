package com.markerhub.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.entity.AppAd;
import com.markerhub.entity.AppCategory;
import com.markerhub.entity.AppProduct;
import com.markerhub.service.AppAdService;
import com.markerhub.service.AppCategoryService;
import com.markerhub.service.AppProductService;
import com.markerhub.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

	@Resource
	AppAdService appAdService;

	@Resource
	AppCategoryService appCategoryService;

	@Resource
	AppProductService appProductService;

	@Override
	public Object getContents() {

		// 首页轮播图
		List<AppAd> carousels = appAdService.list(new QueryWrapper<AppAd>()
				.eq("position", "carousel")
				.eq("is_show", true)
				.orderByDesc("sort_order")
		);

		// 分类
		List<AppCategory> categories = appCategoryService.list(new QueryWrapper<AppCategory>()
				.eq("is_show", true)
				.orderByDesc("sort_order")
		);

		// 置顶或热销产品
		List<AppProduct> products = appProductService.list(new QueryWrapper<AppProduct>()
				.eq("is_on_sale", true)
				.and(i -> i.eq("is_hot", true).or().eq("is_top", true))
				.orderByDesc("sort_order")
				.last("limit 10")
		);


		return MapUtil.builder()
				.put("carousels", carousels)
				.put("categories", categories)
				.put("products", products)
				.build();
	}
}
