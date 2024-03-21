package com.markerhub.modules.search.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.lang.Result;
import com.markerhub.modules.search.document.EsProductDoc;
import com.markerhub.modules.search.service.AppSearchService;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class SearchController {

	@Resource
	AppSearchService appSearchService;

	@GetMapping("/search")
	public Result search(String kw, HttpServletRequest req) {

		int current = ServletRequestUtils.getIntParameter(req, "current", 1);
		int size = ServletRequestUtils.getIntParameter(req, "size", 10);

		Page<EsProductDoc> pageData = appSearchService.search(kw, current, size);
		return Result.success(pageData);
	}
}
