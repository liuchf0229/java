package com.markerhub.modules.search.mq;

import cn.hutool.core.bean.BeanUtil;
import com.markerhub.entity.AppProduct;
import com.markerhub.modules.search.config.SearchRabbitConfig;
import com.markerhub.modules.search.document.EsProductDoc;
import com.markerhub.modules.search.repository.EsProductDocRepository;
import com.markerhub.service.AppProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class SearchConsumer {

	@Resource
	AppProductService appProductService;

	@Resource
	EsProductDocRepository esProductDocRepository;

	@RabbitListener(queues = SearchRabbitConfig.PRODUCT_SEARCH_CREATE_QUEUE)
	public void createListener(Long productId) {
		AppProduct appProduct = appProductService.getById(productId);

		if (appProduct == null || !appProduct.getIsOnSale()) {
			esProductDocRepository.deleteById(productId);
		}

		EsProductDoc productDoc = BeanUtil.copyProperties(appProduct, EsProductDoc.class);

		esProductDocRepository.save(productDoc);
		log.info("正在更新商品{}数据", productId);
	}

	@RabbitListener(queues = SearchRabbitConfig.PRODUCT_SEARCH_DELETE_QUEUE)
	public void deleteListener(List<Long> productIds) {
		esProductDocRepository.deleteAllById(productIds);
	}

}
