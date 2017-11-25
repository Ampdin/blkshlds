package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.SocSpecific;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SocSpecific entity.
 */
public interface SocSpecificSearchRepository extends ElasticsearchRepository<SocSpecific, Long> {
}
