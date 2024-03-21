package com.markerhub.modules.search.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.modules.search.document.EsProductDoc;

public interface AppSearchService {
	int initEsData();

	Page<EsProductDoc> search(String kw, int current, int size);
}
