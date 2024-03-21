package com.markerhub.modules.search.repository;

import com.markerhub.modules.search.document.EsProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsProductDocRepository extends ElasticsearchRepository<EsProductDoc, Long> {
}
