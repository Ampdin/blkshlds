package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.SocMinor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SocMinor entity.
 */
public interface SocMinorSearchRepository extends ElasticsearchRepository<SocMinor, Long> {
}
