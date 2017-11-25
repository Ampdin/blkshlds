package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.SocMajor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SocMajor entity.
 */
public interface SocMajorSearchRepository extends ElasticsearchRepository<SocMajor, Long> {
}
