package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.SocBroad;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SocBroad entity.
 */
public interface SocBroadSearchRepository extends ElasticsearchRepository<SocBroad, Long> {
}
