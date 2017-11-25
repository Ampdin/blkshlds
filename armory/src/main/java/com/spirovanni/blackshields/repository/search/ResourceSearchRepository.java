package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.Resource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Resource entity.
 */
public interface ResourceSearchRepository extends ElasticsearchRepository<Resource, Long> {
}
