package com.markerhub.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.AppProduct;
import com.markerhub.service.AppProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class SearchController extends BaseController{

	@Resource
	AppProductService appProductService;

	@GetMapping("/search")
	public Result search(String kw, HttpServletRequest req) {
		return Result.success(appProductService.page(getPage(), new QueryWrapper<AppProduct>()
				.eq("is_on_sale", true)
				.and(i -> i.like("name", kw).or().like("keywords", kw))
				.orderByDesc("created")
		));
	}
}
